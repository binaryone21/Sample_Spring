package seunghee.ajax.map.board;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
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

    @Autowired private AMBoardService service;

    // 게시판 목록 화면
    @RequestMapping("/list")
    public String boardListPage() {
        return "/ajax/map/board/boardList";
    }

    // 게시판 목록 개수
    @ResponseBody
    @RequestMapping("/selectBoardCount.ajax")
    public Map<String, Object> selectBoardCount(@RequestBody Map<String, Object> paramMap) {
        return service.selectBoardCount(paramMap);
    }

    // 게시판 목록
    @ResponseBody
    @PostMapping("/arraysBoard.ajax")
    public Map<String, Object> arraysBoard(@RequestBody Map<String, Object> paramMap) {
        return service.arraysBoard(paramMap);
    }

    // 게시판 상세
    @ResponseBody
    @PostMapping("/selectBoard.ajax")
    public Map<String, Object> selectBoard(@RequestBody Map<String, Object> paramMap) {
        return service.selectBoard(paramMap);
    }

    // 게시판 등록
    @ResponseBody
    @PostMapping("/insertBoard.ajax")
    public Map<String, Object> insertBoard(@RequestBody Map<String, Object> paramMap) {
        return service.insertBoard(paramMap);
    }

    // 게시판 수정
    @ResponseBody
    @PostMapping("/updateBoard.ajax")
    public Map<String, Object> updateBoard(@RequestBody Map<String, Object> paramMap) {
        return service.updateBoard(paramMap);
    }

    // 게시판 삭제
    @ResponseBody
    @PostMapping("/deleteBoard.ajax")
    public Map<String, Object> deleteBoard(@RequestBody Map<String, Object> paramMap) {
        return service.deleteBoard(paramMap);
    }
}
