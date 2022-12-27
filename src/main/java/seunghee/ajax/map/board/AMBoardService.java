package seunghee.ajax.map.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AMBoardService {

    @Autowired private AMBoardRepository repository;

    // 게시판 목록 개수
    public Map<String, Object> selectBoardCount(Map<String, Object> paramMap) {
        Map<String, Object> searchMap = (Map<String, Object>) paramMap.get("searchMap");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", repository.selectBoardCount(searchMap));
        return resultMap;
    }

    // 게시판 목록
    public Map<String, Object> arraysBoard(Map<String, Object> paramMap) {
        Map<String, Object> searchMap = (Map<String, Object>) paramMap.get("searchMap");
        Map<String, Object> pageMap = (Map<String, Object>) paramMap.get("pageMap");
        searchMap.put("pagePer", pageMap.get("pagePer"));
        searchMap.put("startNo", pageMap.get("startNo"));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("boardList",  repository.arraysBoard(searchMap));
        return resultMap;
    }

    // 게시판 상세
    public Map<String, Object> selectBoard(Map<String, Object> paramMap) {
        Map<String, Object> boardMap = (Map<String, Object>) paramMap.get("boardMap");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("boardView", repository.selectBoard(boardMap));
        return resultMap;
    }

    // 게시판 등록
    public Map<String, Object> insertBoard(Map<String, Object> paramMap) {
        Map<String, Object> boardMap = (Map<String, Object>) paramMap.get("boardMap");
        String message = (repository.insertBoard(boardMap) > 0)
                ? "정보 등록 성공"
                : "정보 등록 실패";

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", message);
        return resultMap;
    }

    // 게시판 수정
    public Map<String, Object> updateBoard(Map<String, Object> paramMap) {
        Map<String, Object> boardMap = (Map<String, Object>) paramMap.get("boardMap");
        String message = (repository.updateBoard(boardMap) > 0)
                ? "정보 수정 성공"
                : "정보 수정 실패";

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", message);
        return resultMap;
    }

    // 게시판 삭제
    public Map<String, Object> deleteBoard(Map<String, Object> paramMap) {
        Map<String, Object> boardMap = (Map<String, Object>) paramMap.get("boardMap");
        String message = (repository.deleteBoard(boardMap) > 0)
                ? "정보 삭제 성공"
                : "정보 삭제 실패";

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", message);
        return resultMap;
    }
}

