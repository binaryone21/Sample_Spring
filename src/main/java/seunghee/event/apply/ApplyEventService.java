package seunghee.event.apply;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seunghee.event.apply.entity.ApplyEventManage;
import seunghee.event.apply.vo.ApplyEventManageSearch;
import seunghee.module.ResultVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ApplyEventService {

	@Autowired
	private ApplyEventRepository repository;

	public ResultVO applyEvent(HttpServletRequest req) {
		ResultVO result = new ResultVO();
		long applyEventSeq = NumberUtils.toLong(req.getParameter("applyEventSeq"), 0);
		long goodsSeq = NumberUtils.toLong(req.getParameter("goodsSeq"), 0);
		// long memberSeq =

		ApplyEventManageSearch applyEventManageSearch = ApplyEventManageSearch.builder()
				.applyEventSeq(applyEventSeq)
				.build();
		List<ApplyEventManage> applyEventManageList = repository.selectApplyEventManage(applyEventManageSearch);





		return result;
	}
}
