package seunghee.board.vo_fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BvfController {

    @Autowired
    private BvfService bvfSvc;

    @RequestMapping("/board/vo_fetch/list")
    public String BVFList(BvfSearchVO searchVO, Model model) {
        model.addAttribute("search", searchVO);
        return "board/vo_fetch/bvfList";
    }

    @ResponseBody
    @RequestMapping("/board/vo_fetch/list/fetch")
    public List<BvfDTO> BVFListPetch(@RequestBody BvfSearchVO searchVO) {
        searchVO = bvfSvc.searchBVF(searchVO);
        return bvfSvc.arraysBVF(searchVO);
    }
}
