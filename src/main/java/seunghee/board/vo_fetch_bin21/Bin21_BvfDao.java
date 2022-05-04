package seunghee.board.vo_fetch_bin21;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Bin21_BvfDao {

    @Autowired
    private SqlSession sqlSession;

    // 게시판 목록조회 개수
    public int selectBVF_PK_Count(Bin21_BvfSearchVO searchVO) {
        return sqlSession.selectOne("bin21_bvfMap.selectBVF_PK_Count", searchVO);
    }

    // 게시판 목록조회
    public List<Bin21_BvfDTO> arraysBVF(Bin21_BvfSearchVO searchVO) {
        return sqlSession.selectList("bin21_bvfMap.arraysBVF", searchVO);
    }

}
