package seunghee.basic.map.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/basic/map/board")
@Controller
public class BMBoardController {

    @Autowired
    private BMBoardService service;

    @RequestMapping("/list")
    public String BoardList(HttpServletRequest req, Model model) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("searchMap", req.getParameter("searchMap"));

        model.addAttribute("result", service.boardList(paramMap));
        return "/basic/map/board/boardList";
    }

}
