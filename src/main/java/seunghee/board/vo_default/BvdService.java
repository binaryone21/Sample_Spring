package seunghee.board.vo_default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BvdService {

    @Autowired
    private BvdDao bvDao;

    // 게시판 검색조건 설정
    public BvdSearchVO searchBVD(BvdSearchVO searchVO) {
        searchVO.compute(bvDao.selectBVD_PK_Count(searchVO));
        return searchVO;
    }

    // 게시판 목록조회
    public List<BvdDTO> arraysBVD(BvdSearchVO searchVO) {
        return bvDao.arraysBVD(searchVO);
    }
}
