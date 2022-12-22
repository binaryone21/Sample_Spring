package seunghee.basic.map.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BMBoardRepository {

    @Autowired
    private SqlSession sqlSession;

    // 게시판 목록조회 개수
    public int selectBoard_PK_count(Map<String, Object> params) {
        return sqlSession.selectOne("BMBoardMapper.arraysBoard", params);
    }

    // 게시판 목록조회
    public List<Map<String, Object>> arraysBoard(Map<String, Object> params) {
        return sqlSession.selectList("BMBoardMapper.arraysBoard", params);
    }
}
