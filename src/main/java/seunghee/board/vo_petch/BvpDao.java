package seunghee.board.vo_petch;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BvpDao {

    @Autowired
    private SqlSession sqlSession;

    // 게시판 목록조회 개수
    public int selectBVP_PK_Count(BvpSearchVO searchVO) {
        return sqlSession.selectOne("bvpMap.selectBVP_PK_Count", searchVO);
    }

    // 게시판 목록조회
    public List<BvpDTO> arraysBVP(BvpSearchVO searchVO) {
        return sqlSession.selectList("bvpMap.arraysBVP", searchVO);
    }

}
