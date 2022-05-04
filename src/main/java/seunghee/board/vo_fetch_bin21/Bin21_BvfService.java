package seunghee.board.vo_fetch_bin21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Bin21_BvfService {

    @Autowired
    private Bin21_BvfDao bvfDao;

    // 게시판 검색조건 설정
    public Bin21_BvfSearchVO searchBVF(Bin21_BvfSearchVO searchVO) {
        searchVO.compute(bvfDao.selectBVF_PK_Count(searchVO));
        return searchVO;
    }

    // 게시판 목록조회
    public List<Bin21_BvfDTO> arraysBVF(Bin21_BvfSearchVO searchVO) {
        return bvfDao.arraysBVF(searchVO);
    }
}
