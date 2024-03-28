package seunghee.event.apply.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import seunghee.common.CalendarTool;
import seunghee.common.DateTool;
import seunghee.event.apply.entity.Duplications;

import java.util.Calendar;
import java.util.Locale;

@Getter
@Setter
@Builder
public class ApplyEventJoinSearch {
	private long applyEventSeq;
	private long goodsSeq;
	private long memberSeq;
	private String userUuid;
	private String day;
	private String startDay;
	private String endDay;
	private String reIssue;

	public void setParams(Duplications duplications) {
		String period = duplications.getPeriod();
		if("DAY".equals(period)) {
			this.setDay(DateTool.getCurrent(DateTool.FMT_DATE));
		} else if("WEEK".equals(period)) {
			Calendar today = Calendar.getInstance(Locale.KOREAN);
			today.add(Calendar.DATE, -today.get(Calendar.DAY_OF_WEEK)+2);
			this.setStartDay(CalendarTool.getCurrent(today, CalendarTool.FMT_DATE));
			today.add(Calendar.DATE, 6);
			this.setEndDay(CalendarTool.getCurrent(today, CalendarTool.FMT_DATE));
		}

		// 쿠폰 사용시 재발급 여부 설정
		String reIssued = duplications.getReIssued();
		if("USED".equals(reIssued)) {
			this.setReIssue("used");
		}
	}
}
