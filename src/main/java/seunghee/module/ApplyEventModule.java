package seunghee.module;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import seunghee.common.DateTool;
import seunghee.event.apply.ApplyEventRepository;
import seunghee.event.apply.entity.*;
import seunghee.event.apply.vo.ApplyEventGoodsSearch;
import seunghee.event.apply.vo.ApplyEventJoinSearch;

import java.util.LinkedList;
import java.util.List;

@Component
public class ApplyEventModule {
	@Autowired
	private ApplyEventRepository repository;

	@Autowired
	private CouponModule couponModule;

	/** requirement 체크 */
	public ResultVO requirements(List<Requirement> requirements, LoginVO login) throws Exception {
		ResultVO result = new ResultVO();

		for(Requirement requirement : requirements) {
			switch(requirement.getType()) {
				case "login"	: result = isLogin(requirement, login); break;
				case "roles"	: result = isRoles(requirement, login); break;
				default			: result = noRequireType();	// Require Type 없음
			}
			if(!result.isSuccess()) return result;
		}

		return result;
	}



	/** duplication 체크 */
	public ResultVO duplication(Duplications duplications, long applyEventSeq, long memberSeq) throws Exception {
		ResultVO result;

		ApplyEventJoinSearch applyEventJoinSearch = ApplyEventJoinSearch.builder()
				.applyEventSeq(applyEventSeq)
				.memberSeq(memberSeq)
				.build();
		applyEventJoinSearch.setParams(duplications);

		// Period, reIssued 를 염두한 응모횟수
		long joinLimit = repository.selectApplyEventJoinLimit(applyEventJoinSearch);
		result = isJoinLimit(duplications, joinLimit);
		if(!result.isSuccess()) return result;

		// 순수 응모 횟수
		long totalLimit = repository.selectApplyEventJoinCount(applyEventJoinSearch);
		result = isTotalLimit(duplications, totalLimit);
		if(!result.isSuccess()) return result;

		return result;
	}

	/** 응모하기 */
	public ResultVO draw(DrawInfo drawInfo, long applyEventSeq, long goodsSeq, long memberSeq) throws Exception {
		ResultVO result;
		String drawType = drawInfo.getDrawType();
		switch(drawType) {
			case "apply"	: result = drawApply	(goodsSeq); break; // 프론트에서 사용자가 선택한 goodsSeq 응모
			case "loop"		: result = drawLoop		(drawInfo, applyEventSeq); break; // goods 에 설정된 반복에 따라 goodsSeq 응모
			case "random"	: result = drawRandom	(drawInfo, applyEventSeq); break; // goods 에 설정된 확률에 따라 goodsSeq 응모
			case "count"	: result = drawCount	(drawInfo, applyEventSeq, memberSeq); break; // goods 에 설정된 횟수에 따라 goodsSeq 응모
			default			: result = noDrawType(); break; // Draw Type(응모 타입) 없음
		}
		if(!result.isSuccess()) return result;

		goodsSeq = (long) result.getData().get("goodsSeq");
		ApplyEventGoodsSearch applyEventGoodsSearch = ApplyEventGoodsSearch.builder()
				.applyEventSeq(applyEventSeq)
				.goodsSeq(goodsSeq)
				.build();
		List<ApplyEventGoods> applyEventGoodsList = repository.selectApplyEventGoods(applyEventGoodsSearch);
		result = isExistApplyEventGoodsList(applyEventGoodsList);
		if(!result.isSuccess()) return result;

		ApplyEventGoods applyEventGoods = applyEventGoodsList.get(0);
		result = isGoodsMaxLimit(applyEventGoods);
		if(!result.isSuccess()) return result;
		repository.updateApplyEventGoodsCurrentCount(applyEventGoods);

		ApplyEventJoin applyEventJoin = ApplyEventJoin.builder()
				.applyEventSeq(applyEventSeq)
				.goodsSeq(goodsSeq)
				.memberSeq(memberSeq)
				.build();
		repository.insertApplyEventJoin(applyEventJoin);

		GoodsInfo goodsInfo = drawInfo.getGoodsInfos().get((int) (goodsSeq-1));
		result.put("goodsInfo", goodsInfo);
		return result;
	}

	/** 응모하기 - apply */
	public ResultVO drawApply(long goodsSeq) throws Exception {
		ResultVO result = new ResultVO();

		goodsSeq = (0L != goodsSeq) ? goodsSeq : 1L;
		result.put("goodsSeq", goodsSeq);

		return result;
	}

	/** 응모하기 - loop */
	public ResultVO drawLoop(DrawInfo drawInfo, long applyEventSeq) throws Exception {
		ResultVO result;

		List<GoodsInfo> originGoodsInfoList = drawInfo.getGoodsInfos();
		LinkedList<GoodsInfo> newGoodsInfoList = new LinkedList<>();
		long totalCount = 0;

		for(GoodsInfo goodsInfo : originGoodsInfoList) {
			long goodsSeq = goodsInfo.getGoodsSeq();
			ApplyEventGoodsSearch applyEventGoodsSearch = ApplyEventGoodsSearch.builder()
					.applyEventSeq(applyEventSeq)
					.goodsSeq(goodsSeq)
					.build();
			ApplyEventGoods applyEventGoods = repository.selectApplyEventGoods(applyEventGoodsSearch).get(0);
			totalCount += applyEventGoods.getCurrentCount();
			result = isGoodsMaxLimit(applyEventGoods);
			if(!result.isSuccess()) continue;

			newGoodsInfoList.add(goodsInfo);
		}
		result = isExistGoodsInfoList(newGoodsInfoList);
		if(!result.isSuccess()) return result;

		long goodsSeq = 0L;
		for(GoodsInfo goodsInfo : newGoodsInfoList) {
			if((totalCount % Long.parseLong(goodsInfo.getValue())) == 0) {
				goodsSeq = goodsInfo.getGoodsSeq();
				break;
			}
		}
		result = isExistGoodsSeq(goodsSeq);
		if(!result.isSuccess()) return result;

		result.put("goodsSeq", goodsSeq);
		return result;
	}

	/** 응모하기 - random */
	public ResultVO drawRandom(DrawInfo drawInfo, long applyEventSeq) throws Exception {
		ResultVO result;

		List<GoodsInfo> originGoodsInfoList = drawInfo.getGoodsInfos();
		LinkedList<GoodsInfo> newGoodsInfoList = new LinkedList<>();
		double maxBoundary = 0;

		for(GoodsInfo goodsInfo : originGoodsInfoList) {
			long goodsSeq = goodsInfo.getGoodsSeq();
			ApplyEventGoodsSearch applyEventGoodsSearch = ApplyEventGoodsSearch.builder()
					.applyEventSeq(applyEventSeq)
					.goodsSeq(goodsSeq)
					.build();
			ApplyEventGoods applyEventGoods = repository.selectApplyEventGoods(applyEventGoodsSearch).get(0);
			result = isGoodsMaxLimit(applyEventGoods);
			if(!result.isSuccess()) continue;

			maxBoundary += Double.parseDouble(goodsInfo.getValue());
			newGoodsInfoList.add(goodsInfo);
		}
		result = isExistGoodsInfoList(newGoodsInfoList);
		if(!result.isSuccess()) return result;

		long goodsSeq = 0L;
		double random = (Math.random() * maxBoundary);
		double boundary = 0;
		for(GoodsInfo goodsInfo : newGoodsInfoList) {
			boundary += Double.parseDouble(goodsInfo.getValue());

			if(random <= boundary) {
				goodsSeq = goodsInfo.getGoodsSeq();
				break;
			}
		}
		result = isExistGoodsSeq(goodsSeq);
		if(!result.isSuccess()) return result;

		result.put("goodsSeq", goodsSeq);
		return result;
	}

	/** 응모하기 - count */
	public ResultVO drawCount(DrawInfo drawInfo, long applyEventSeq, long memberSeq) throws Exception {
		ResultVO result;

		List<GoodsInfo> originGoodsInfoList = drawInfo.getGoodsInfos();
		long goodsSeq = 0L;

		for(GoodsInfo goodsInfo : originGoodsInfoList) {
			goodsSeq = goodsInfo.getGoodsSeq();
			ApplyEventGoodsSearch applyEventGoodsSearch = ApplyEventGoodsSearch.builder()
					.applyEventSeq(applyEventSeq)
					.goodsSeq(goodsSeq)
					.build();
			ApplyEventGoods applyEventGoods = repository.selectApplyEventGoods(applyEventGoodsSearch).get(0);
			result = isGoodsMaxLimit(applyEventGoods);
			if(!result.isSuccess()) continue;

			ApplyEventJoinSearch applyEventJoinSearch = ApplyEventJoinSearch.builder()
					.applyEventSeq(applyEventSeq)
					.memberSeq(memberSeq)
					.build();
			long count = repository.selectApplyEventJoinCount(applyEventJoinSearch);
			if(count == Long.parseLong(goodsInfo.getValue())) {
				break;
			}
		}
		result = isExistGoodsSeq(goodsSeq);
		if(!result.isSuccess()) return result;

		result.put("goodsSeq", goodsSeq);
		return result;
	}

	/** 발행 */
	public ResultVO issue(GoodsInfo goodsInfo, LoginVO login) throws Exception {
		ResultVO result = new ResultVO();

		String goodsType = goodsInfo.getGoodsType();
		switch(goodsType) {
			case "join"		: return result;
			case "coupon"	: return couponModule.issueCoupon	(goodsInfo, login);
			default 		: return noGoodsType();	// Goods Type(상품 타입) 없음
		}
	}







	/** Apply Event Error Text
	 *  TODO: EC000 -> Apply Event Manage
	 *  TODO: EC100 -> Requirements
	 *  TODO: EC200 -> Duplications
	 *  TODO: EC300 -> Apply Event Goods
	 *  TODO: EC400 -> Apply Event Join
	 */

	/** Error Code - EC000 : Apply Event Manage 없음 */
	public ResultVO isExistApplyEventManage(List<ApplyEventManage> applyEventManageList) throws Exception {
		ResultVO result = new ResultVO();

		if(applyEventManageList.size() == 0) {
			result.set(false, "Apply Event Manage 없음", 000);
		}

		return result;
	}

	/** Error Code - EC001 : 응모 참여 가능한지 체크 */
	public ResultVO isPossibleDate(ApplyEventManage applyEventManage) throws Exception {
		ResultVO result = new ResultVO();

		String startDate = applyEventManage.getStartDate();
		String endDate = applyEventManage.getEndDate();
		// 응모기간 체크
		if(!DateTool.isBetween(startDate, endDate, DateTool.FMT_DATE_TIME)) {
			String message = StringUtils.defaultString("", "종료된 응모 입니다.");
			result.set(false, message, 001);
		}

		return result;
	}

	/** Error Code - EC100 : Require Type 체크 */
	public ResultVO noRequireType() throws Exception {
		ResultVO result = new ResultVO();

		result.set(false, "Require Type 없음", 100);

		return result;
	}

	/** Error Code - EC101 : login 여부를 체크 */
	public ResultVO isLogin(Requirement requirement, LoginVO login) throws Exception {
		ResultVO result = new ResultVO();

		if(!login.isLogined()) {
			String message = StringUtils.defaultString(requirement.getErrorMessage(), "로그인시 참여가 가능합니다.");
			result.set(false, message, 101);
		}

		return result;
	}

	/** Error Code - EC102 : roles 여부를 체크 */
	public ResultVO isRoles(Requirement requirement, LoginVO login) throws Exception {
		ResultVO result = new ResultVO();

		if(!login.isLogined() || !requirement.getRoles().contains(login.getRole())) {
			String message = StringUtils.defaultString(requirement.getErrorMessage(), "본 이벤트에 적합하지 않은 회원입니다.");
			result.set(false, message, 102);
		}

		return result;
	}

	/** Error Code - EC201 : 조건내 응모횟수 체크 */
	public ResultVO isJoinLimit(Duplications duplications, long joinLimit) throws Exception {
		ResultVO result = new ResultVO();

		if(joinLimit >= duplications.getJoinLimit()) {
			result.set(false, duplications.getErrorMessage(), 201);
		}

		return result;
	}

	/** Error Code - EC202 : 전체 응모횟수 체크*/
	public ResultVO isTotalLimit(Duplications duplications, long totalLimit) throws Exception {
		ResultVO result = new ResultVO();

		if(totalLimit >= duplications.getTotalLimit()) {
			result.set(false, duplications.getErrorMessage(), 202);
		}

		return result;
	}

	/** Error Code - EC301 : 응모 유형 체크 */
	public ResultVO noDrawType() throws Exception {
		ResultVO result = new ResultVO();

		result.set(false, "Draw Type(응모유형) 없음", 301);

		return result;
	}

	/** Error Code - EC302 : 해당 Goods 가 응모 가능한 상태인지 체크 */
	public ResultVO isGoodsMaxLimit(ApplyEventGoods applyEventGoods) throws Exception {
		ResultVO result = new ResultVO();

		if(0 != applyEventGoods.getMaxLimit() && applyEventGoods.getCurrentCount() >= applyEventGoods.getMaxLimit()) {
			result.set(false, "해당 응모상품의 최대 발급수 초과", 302);
		}

		return result;
	}

	/** Error Code - EC303 : 해당 응모내 응모 가능한 Goods 가 있는지 체크 */
	public ResultVO isExistGoodsInfoList(List<GoodsInfo> GoodsInfoList) throws Exception {
		ResultVO result = new ResultVO();

		if(GoodsInfoList.size() == 0) {
			result.set(false, "응모가능 Goods Info 가 없음", 303);
		}

		return result;
	}

	/** Error Code - EC304 : 해당 응모내 응모 조건에 해당하는 Goods 가 있는지 체크 */
	public ResultVO isExistGoodsSeq(long goodsSeq) throws Exception {
		ResultVO result = new ResultVO();

		if(goodsSeq == 0L) {
			result.set(false, "응모가능 상품이 없음", 304);
		}

		return result;
	}

	/** Error Code - EC305 : 조건에 해당하는 응모 상품이 있는지 체크 */
	public ResultVO isExistApplyEventGoodsList(List<ApplyEventGoods> applyEventGoodsList) throws Exception {
		ResultVO result = new ResultVO();

		if(applyEventGoodsList.size() == 0) {
			result.set(false, "조건에 해당하는 응모 상품이 없음", 305);
		}

		return result;
	}

	/** Error Code - EC306 : 상품 유형 체크 */
	public ResultVO noGoodsType() throws Exception {
		ResultVO result = new ResultVO();

		result.set(false, "Goods Type(상품유형) 없음", 306);

		return result;
	}
}
