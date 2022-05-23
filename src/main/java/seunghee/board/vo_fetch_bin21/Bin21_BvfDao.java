package seunghee.board.vo_fetch_bin21;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Bin21_BvfDao {

    @Autowired
    private SqlSession sqlSession;

    // 게시판 목록 개수
    public int selectBVF_PK_Count(Bin21_BvfSearchVO searchVO) {
        return sqlSession.selectOne("bin21_bvfMap.selectBVF_PK_Count", searchVO);
    }

    // 게시판 목록
    public List<Bin21_BvfDTO> arraysBVF(Bin21_BvfSearchVO searchVO) {
        return sqlSession.selectList("bin21_bvfMap.arraysBVF", searchVO);
    }

    // 게시판 상세
    public Bin21_BvfDTO selectBVF(String tp_pk) {
        return sqlSession.selectOne("bin21_bvfMap.selectBVF", tp_pk);
    }

    // 게시판 등록
    public int insertBVF(Bin21_BvfDTO view) {
        return sqlSession.insert("bin21_bvfMap.insertBVF", view);
    }

    // 게시판 수정
    public int updateBVF(Bin21_BvfDTO view) {
        return sqlSession.update("bin21_bvfMap.updateBVF", view);
    }

    // 게시판 삭제
    public int deleteBVF(String tp_pk) {
        return sqlSession.update("bin21_bvfMap.deleteBVF", tp_pk);
    }
}
