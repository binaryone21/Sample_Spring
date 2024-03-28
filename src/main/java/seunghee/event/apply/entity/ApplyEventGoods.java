package seunghee.event.apply.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyEventGoods {
	private long applyEventSeq;
	private long goodsSeq;
	private String goodsValue;
	private String goodsName;
	private String goodsContents;
	private long maxLimit;
	private long minLimit;
	private long currentCount;
	private String probabilityOption;
	private String couponKind;
	private long couponPrice;
	private long couponMinUsePrice;
	private long regMemberSeq;
	private String regDate;
	private long modMemberSeq;
	private String modDate;
}