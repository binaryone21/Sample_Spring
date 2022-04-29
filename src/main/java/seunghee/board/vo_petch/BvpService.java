package seunghee.board.vo_petch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BvpService {

    @Autowired
    private BvpDao bvpDao;

    // 게시판 검색조건 설정
    public BvpSearchVO searchBVP(BvpSearchVO searchVO) {
        searchVO.compute(bvpDao.selectBVP_PK_Count(searchVO));
        return searchVO;
    }

    // 게시판 목록조회
    public List<BvpDTO> arraysBVP(BvpSearchVO searchVO) {
        return bvpDao.arraysBVP(searchVO);
    }
}
