package seunghee.board.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardVoService {

    @Autowired
    private BoardVoDao bvDao;

    // 게시판 검색조건 설정
    public SearchVO searchBoardVo(SearchVO searchVO) {
        searchVO.compute(bvDao.selectBoardVo_PK_Count(searchVO));
        return searchVO;
    }

    // 게시판 목록조회
    public List<BoardVoDTO> arraysBoardVo(SearchVO searchVO) {
        return bvDao.arraysBoardVo(searchVO);
    }
}
