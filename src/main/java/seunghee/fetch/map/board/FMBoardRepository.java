package seunghee.fetch.map.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class FMBoardRepository {

    @Autowired private SqlSession sqlSession;

    // 게시판 목록 개수
    public int selectBoardCount(Map<String, Object> paramMap) {
        return sqlSession.selectOne("FMBoardMapper.selectBoardCount", paramMap);
    }

    // 게시판 목록
    public List<Map<String, Object>> arraysBoard(Map<String, Object> paramMap) {
        return sqlSession.selectList("FMBoardMapper.arraysBoard", paramMap);
    }

    // 게시판 상세
    public Map<String, Object> selectBoard(Map<String, Object> paramMap) {
        return sqlSession.selectOne("FMBoardMapper.selectBoard", paramMap);
    }

    // 게시판 등록
    public int insertBoard(Map<String, Object> paramMap) {
        return sqlSession.insert("FMBoardMapper.insertBoard", paramMap);
    }

    // 게시판 수정
    public int updateBoard(Map<String, Object> paramMap) {
        return sqlSession.update("FMBoardMapper.updateBoard", paramMap);
    }

    // 게시판 삭제
    public int deleteBoard(Map<String, Object> paramMap) {
        return sqlSession.update("FMBoardMapper.deleteBoard", paramMap);
    }
}
