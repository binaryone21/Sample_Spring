package seunghee.ajax.map.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seunghee.tools.SearchTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AMboardService {

    @Autowired
    private AMBoardRepository boardRepository;

    public Map<String, Object> boardListCountAjax(Map<String, Object> paramMap) {
        Map<String, Object> searchMap = (Map<String, Object>) paramMap.get("searchMap");

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", boardRepository.selectBoardCount(searchMap));
        return resultMap;
    }
    public Map<String, Object> boardListAjax(Map<String, Object> paramMap) {
        Map<String, Object> searchMap = (Map<String, Object>) paramMap.get("searchMap");
        Map<String, Object> pageMap = (Map<String, Object>) paramMap.get("pageMap");
        searchMap.put("pagePer", pageMap.get("pagePer"));
        searchMap.put("startNo", pageMap.get("startNo"));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("boardList",  boardRepository.arrayBoard(searchMap));
        return resultMap;
    }
}

// SearchTool.compute(searchMap, totalNo);
// List<Map<String, Object>> boardList = boardRepository.arrayBoard(searchMap);

// resultMap.put("searchMap", searchMap);

