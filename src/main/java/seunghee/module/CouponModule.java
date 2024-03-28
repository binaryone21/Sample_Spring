package seunghee.module;

import org.springframework.stereotype.Component;
import seunghee.event.apply.entity.GoodsInfo;

@Component
public class CouponModule {
	public ResultVO issueCoupon(GoodsInfo goodsInfo, LoginVO login) {
		return new ResultVO();
	}
}
