package seunghee.board.vo_fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BvfService {

    @Autowired
    private BvfDao bvfDao;

    // 게시판 검색조건 설정
    public BvfSearchVO searchBVF(BvfSearchVO searchVO) {
        searchVO.compute(bvfDao.selectBVF_PK_Count(searchVO));
        return searchVO;
    }

    // 게시판 목록조회
    public List<BvfDTO> arraysBVF(BvfSearchVO searchVO) {
        return bvfDao.arraysBVF(searchVO);
    }
}
