package seunghee.board.vo_default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BvdController {

    @Autowired
    private BvdService bvdSvc;

    @RequestMapping("/board/vo_default/list")
    public String BVDList(BvdSearchVO searchVO, Model model) {
        searchVO = bvdSvc.searchBVD(searchVO);
        model.addAttribute("TP_List", bvdSvc.arraysBVD(searchVO));
        model.addAttribute("search", searchVO);
        return "board/vo_default/bvdList";
    }
}
