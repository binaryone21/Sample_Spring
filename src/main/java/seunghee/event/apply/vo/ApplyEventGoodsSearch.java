package seunghee.event.apply.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApplyEventGoodsSearch {
	private long applyEventSeq;
	private long goodsSeq;
}
