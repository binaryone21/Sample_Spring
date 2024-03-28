package seunghee.event.apply.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawInfo {
	private String drawType;
	private List<GoodsInfo> goodsInfos;
}
