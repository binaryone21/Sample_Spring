package kakaoChatbot;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class KakaoDAO {

    @Autowired
    private SqlSession sqlSession;

    /* 해당하는 조건의 실업급여 FAQ List 조회 */
    public UebFaqVO select() {
        return sqlSession.selectOne("KkMap.select");
    }
}
