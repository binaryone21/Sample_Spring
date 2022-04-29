package seunghee.board.vo_default;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BvdDao {

    @Autowired
    private SqlSession sqlSession;

    // 게시판 목록조회 개수
    public int selectBVD_PK_Count(BvdSearchVO searchVO) {
        return sqlSession.selectOne("bvdMap.selectBVD_PK_Count", searchVO);
    }

    // 게시판 목록조회
    public List<BvdDTO> arraysBVD(BvdSearchVO searchVO) {
        return sqlSession.selectList("bvdMap.arraysBVD", searchVO);
    }

}
