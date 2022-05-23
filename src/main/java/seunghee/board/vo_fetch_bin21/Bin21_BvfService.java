package seunghee.board.vo_fetch_bin21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seunghee.common.DataUtil;

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

    // 게시판 목록
    public List<Bin21_BvfDTO> arraysBVF(Bin21_BvfSearchVO searchVO) {
        return bvfDao.arraysBVF(searchVO);
    }

    // 게시판 상세
    public Bin21_BvfDTO selectBVF(String tp_pk) {
        return bvfDao.selectBVF(tp_pk);
    }

    // 게시판 등록
    public String insertBVF(Bin21_BvfDTO view) {
        return (bvfDao.insertBVF(view) > 0)
                ? DataUtil.json("등록 성공")
                : DataUtil.json("등록 실패");
    }

    // 게시판 수정
    public String updateBVF(Bin21_BvfDTO view) {
        return (bvfDao.updateBVF(view) > 0)
                ? DataUtil.json("수정 성공")
                : DataUtil.json("수정 실패");
    }

    // 게시판 삭제
    public String deleteBVF(String tp_pk) {
        return (bvfDao.deleteBVF(tp_pk) > 0)
                ? DataUtil.json("삭제 성공")
                : DataUtil.json("삭제 실패");
    }
}
