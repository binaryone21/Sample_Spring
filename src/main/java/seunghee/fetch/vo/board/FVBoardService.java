package seunghee.fetch.vo.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seunghee.module.ResultVO;

@Service
public class FVBoardService {

    @Autowired private FVBoardRepository repository;

    // 게시판 목록 개수
    public ResultVO boardListCountFetch(FVBoardDTO boardDTO) {
        ResultVO resultVO = new ResultVO();
        resultVO.put("total", repository.selectBoardCOunt(boardDTO));
        return resultVO;
    }
}
