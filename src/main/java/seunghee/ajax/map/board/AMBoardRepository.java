package seunghee.ajax.map.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AMBoardRepository {

    @Autowired
    private SqlSession sqlSession;

    // 게시판 목록 개수
    public int selectBoardCount(Map<String, Object> paramMap) {
        return sqlSession.selectOne("AMBoardMapper.selectBoardCount", paramMap);
    }

    // 게시판 목록
    public List<Map<String, Object>> arrayBoard(Map<String, Object> parmaMap) {
        return sqlSession.selectList("AMBoardMapper.arraysBoard", parmaMap);
    }

    // 게시판 상세
    public Map<String, Object> selectBoard(Map<String, Object> paramMap) {
        return sqlSession.selectOne("AMBoardMapper.selectBoard", paramMap);
    }

    // 게시판 등록
    public int insertBoard(Map<String, Object> paramMap) {
        return sqlSession.insert("AMBoardMapper.insertBoard", paramMap);
    }

    // 게시판 수정
    public int updateBoard(Map<String, Object> paramMap) {
        return sqlSession.update("AMBoardMapper.updateBoard", paramMap);
    }

    // 게시판 삭제
    public int deleteBoard(Map<String, Object> paramMap) {
        return sqlSession.update("AMBoardMapper.deleteBoard", paramMap);
    }
}
