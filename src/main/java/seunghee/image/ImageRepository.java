package seunghee.image;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ImageRepository {
	@Autowired
	private SqlSession sqlSession;

	public int insertImagePath(Map<String, Object> paramMap) {
		return sqlSession.insert("ImageMapper.insertImagePath", paramMap);
	}
}
