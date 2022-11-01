package seunghee.image.ajax_map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ImageRepository_am {

    @Autowired
    private SqlSession sqlSession;

    public int insertImagePath(Map<String, Object> params) {
        return sqlSession.insert("imageMapper_am.insertImagePath", params);
    }
}
