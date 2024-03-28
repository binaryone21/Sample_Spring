package seunghee.event.apply;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seunghee.event.apply.entity.ApplyEventGoods;
import seunghee.event.apply.entity.ApplyEventJoin;
import seunghee.event.apply.entity.ApplyEventManage;
import seunghee.event.apply.vo.ApplyEventGoodsSearch;
import seunghee.event.apply.vo.ApplyEventJoinSearch;
import seunghee.event.apply.vo.ApplyEventManageSearch;

import java.util.List;

@Repository
public class ApplyEventRepository {

	@Autowired
	private SqlSession sqlSession;

	/** Apply Event Manage */
	public List<ApplyEventManage> selectApplyEventManage(ApplyEventManageSearch applyEventManageSearch) {
		return sqlSession.selectList("ApplyEventMapper.selectApplyEventManage", applyEventManageSearch);
	}

	/** Apply Event Goods */
	public List<ApplyEventGoods> selectApplyEventGoods(ApplyEventGoodsSearch applyEventGoodsSearch) {
		return sqlSession.selectList("ApplyEventMapper.selectApplyEventGoods", applyEventGoodsSearch);
	}
	public long updateApplyEventGoodsCurrentCount(ApplyEventGoods applyEventGoods) {
		return sqlSession.update("ApplyEventMapper.updateApplyEventGoodsCurrentCount", applyEventGoods);
	}

	/** Apply Event Join */
	public List<ApplyEventJoin> selectApplyEventJoin(ApplyEventJoinSearch applyEventJoinSearch) {
		return sqlSession.selectList("ApplyEventMapper.selectApplyEventJoin", applyEventJoinSearch);
	}
	public long selectApplyEventJoinCount(ApplyEventJoinSearch applyEventJoinSearch) {
		return sqlSession.selectOne("ApplyEventMapper.selectApplyEventJoinCount", applyEventJoinSearch);
	}
	public long selectApplyEventJoinLimit(ApplyEventJoinSearch applyEventJoinSearch) {
		return sqlSession.selectOne("ApplyEventMapper.selectApplyEventJoinLimit", applyEventJoinSearch);
	}
	public long insertApplyEventJoin(ApplyEventJoin applyEventJoin) {
		return sqlSession.insert("ApplyEventMapper.insertApplyEventJoin", applyEventJoin);
	}
}
