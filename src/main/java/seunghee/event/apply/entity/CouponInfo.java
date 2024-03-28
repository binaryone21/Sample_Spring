package seunghee.event.apply.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponInfo {
	private String couponMasterNo;
	private long count;
}
