package seunghee.board.vo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardVoDao {

    @Autowired
    private SqlSession sqlSession;

    // 게시판 목록조회 개수
    public int selectBoardVo_PK_Count(SearchVO searchVO) {
        return sqlSession.selectOne("bvMap.selectBoardVo_PK_Count", searchVO);
    }

    // 게시판 목록조회
    public List<BoardVoDTO> arraysBoardVo(SearchVO searchVO) {
        return sqlSession.selectList("bvMap.arraysBoardVo", searchVO);
    }

}
