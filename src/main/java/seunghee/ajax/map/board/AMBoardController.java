package seunghee.ajax.map.board;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/ajax/map/board")
@Controller
public class AMBoardController {

    @Autowired private AMboardService service;

    @RequestMapping("list")
    public String boardList() {
        return "/ajax/map/board/boardList";
    }

    @ResponseBody
    @RequestMapping("/listCount.ajax")
    public Map<String, Object> boardListCountAjax(@RequestBody Map<String, Object> paramMap) {
        return service.boardListCountAjax(paramMap);
    }

    @ResponseBody
    @PostMapping("/list.ajax")
    public Map<String, Object> boardListAjax(@RequestBody Map<String, Object> paramMap) {
        return service.boardListAjax(paramMap);
    }

    @RequestMapping("/boardList/table.pageAjax")
    public String boardListTablePageAjax(HttpServletRequest req, Model model) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pagingMap", JSONObject.fromObject(req.getParameter("pagingMap")));

        // model.addAttribute("result", JSONObject.fromObject(service.boardListTablePageAjax(paramMap)));
        return "/ajax/map/board/boardListTable";
    }
}
