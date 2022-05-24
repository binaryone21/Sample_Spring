package seunghee.board.vo_fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seunghee.common.DataUtil;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class BvfService {

    @Autowired
    private BvfDao bvfDao;

    // 게시판 검색조건 설정
    public BvfSearchVO searchBVF(BvfSearchVO searchVO) {
        return new BvfSearchVO(bvfDao.selectBVF_PK_Count(searchVO));
    }

    // 게시판 목록
    public List<BvfDTO> arraysBVF(BvfSearchVO searchVO) {
        return bvfDao.arraysBVF(searchVO);
    }

    // 게시판 상세
    public BvfDTO selectBVF(String tp_pk) {
        return bvfDao.selectBVF(tp_pk);
    }
    
    // 게시판 등록
    public String insertBVF(BvfDTO view) {
        return (bvfDao.insertBVF(view) > 0)
                ? DataUtil.json("등록 성공")
                : DataUtil.json("등록 실패");
    }

    // 게시판 수정
    public String updateBVF(BvfDTO view) {
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
