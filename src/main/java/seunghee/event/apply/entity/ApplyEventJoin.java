package seunghee.event.apply.entity;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplyEventJoin {
	private long eventJoinSeq;
	private long applyEventSeq;
	private long goodsSeq;
	private String goodsValue;
	private long memberSeq;
	private String applyDt;
	private String userUuid;
	private String winningYn;
	private long regMemberSeq;
	private String regDate;
}