package seunghee.board.vo_fetch;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BvfDao {

    @Autowired
    private SqlSession sqlSession;

    // 게시판 목록 개수
    public int selectBVF_PK_Count(BvfSearchVO searchVO) {
        return sqlSession.selectOne("bvfMap.selectBVF_PK_Count", searchVO);
    }

    // 게시판 목록
    public List<BvfDTO> arraysBVF(BvfSearchVO searchVO) {
        return sqlSession.selectList("bvfMap.arraysBVF", searchVO);
    }

    // 게시판 상세
    public BvfDTO selectBVF(String tp_pk) {
        return sqlSession.selectOne("bvfMap.selectBVF", tp_pk);
    }

    // 게시판 등록
    public int insertBVF(BvfDTO view) {
        return sqlSession.insert("bvfMap.insertBVF", view);
    }

    // 게시판 수정
    public int updateBVF(BvfDTO view) {
        return sqlSession.update("bvfMap.updateBVF", view);
    }

    // 게시판 삭제
    public int deleteBVF(String tp_pk) {
        return sqlSession.update("bvfMap.deleteBVF", tp_pk);
    }

}
