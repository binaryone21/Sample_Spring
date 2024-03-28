package seunghee.event.apply.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {
	private long goodsSeq;
	private String value;
	private String goodsType;
	private List<CouponInfo> couponInfos;
	private long price;
}
