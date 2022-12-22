package seunghee.basic.map.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seunghee.tools.SearchTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BMBoardService {

    @Autowired
    private BMBoardRepository boardRepository;

    // 게시판 검색조건 설정
    public Map<String, Object> boardList(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> searchMap = (Map<String, Object>) paramMap.get("searchMap");

        if(searchMap == null) searchMap = new HashMap<>();
        // SearchTool.setDefault(searchMap);
        int totalNo = boardRepository.selectBoard_PK_count(searchMap);
        SearchTool.compute(searchMap, totalNo);
        List<Map<String, Object>> boardList = boardRepository.arraysBoard(searchMap);

        resultMap.put("searchMap", searchMap);
        resultMap.put("boardList", boardList);
        return resultMap;
    }
}
