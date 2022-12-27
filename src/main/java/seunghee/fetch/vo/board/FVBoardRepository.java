package seunghee.fetch.vo.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FVBoardRepository {

    @Autowired private SqlSession sqlSession;

    // 게시판 목록 개수
    public int selectBoardCOunt(FVBoardDTO boardDTO) {
        return sqlSession.selectOne("FVBoardMapper.selectBoardCount", boardDTO);
    }

    // 게시판 목록
    public List<FVBoardEntity> arrayBoard(FVBoardDTO boardDTO) {
        return sqlSession.selectList("FVBoardMapper.arraysBoard", boardDTO);
    }

    // 게시판 상세
    public FVBoardEntity selectBoard(FVBoardEntity personEntity) {
        return sqlSession.selectOne("FVBoardMapper.selectBoard", personEntity);
    }

    // 게시판 등록
    public int insertBoard(FVBoardEntity personEntity) {
        return sqlSession.selectOne("FVBoardMapper.insertBoard", personEntity);
    }

    // 게시판 수정
    public int updateBoard(FVBoardEntity personEntity) {
        return sqlSession.update("FVBoardMapper.updateBoard", personEntity);
    }

    // 게시판 삭제
    public int deleteBoard(FVBoardEntity personEntity) {
        return sqlSession.update("FVBoardMapper.deleteBoard", personEntity);
    }
}
