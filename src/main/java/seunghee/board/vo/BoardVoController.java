package seunghee.board.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardVoController {

    @Autowired
    private BoardVoService bvSvc;

    @RequestMapping("/board/vo/voList")
    public String boardVoList(SearchVO searchVO, Model model) {
        searchVO = bvSvc.searchBoardVo(searchVO);
        model.addAttribute("TP_List", bvSvc.arraysBoardVo(searchVO));
        model.addAttribute("TP_Search", searchVO);
        return "board/vo/voList";
    }
}
