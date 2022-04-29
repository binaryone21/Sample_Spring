package seunghee.board.vo_petch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BvpController {

    @Autowired
    private BvpService bvpSvc;

    @RequestMapping("/board/vo_petch/list")
    public String BVPList(BvpSearchVO searchVO, Model model) {
        model.addAttribute("search", searchVO);
        return "board/vo_petch/bvpList";
    }

    @ResponseBody
    @RequestMapping("/board/vo_petch/list/petch")
    public List<BvpDTO> BVPListPetch(@RequestBody BvpSearchVO searchVO) {
        searchVO = bvpSvc.searchBVP(searchVO);
        return bvpSvc.arraysBVP(searchVO);
    }
}
