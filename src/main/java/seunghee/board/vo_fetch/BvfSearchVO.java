package seunghee.board.vo_fetch;

import lombok.Data;

@Data
public class BvfSearchVO {
    private int pagePer;	    				// 화면당 보여줄 게시물의 수
    private int naviNo;			    			// 하단 네비게이션의 수
    private int pageTarget;						// 조회하려는 페이지 번호
    private String startDate = "2022-01-01";	// 조회 시작 날짜
    private String startTime = "00:00";			// 조회 시작 시간
    private String endDate = "2022-12-31";		// 조회 끝 날짜
    private String endTime = "23:59";			// 조회 끝 시간
    private String searchType;					// 검색 조건
    private String searchText;					// 검색 내용
    private int startNo;						// 조회 시작 번호
    private int totalNo;						// 총 게시물의 수
    private int pageTotal;						// 총 페이지의 수
    private int pageNo;							// 현재 페이지의 번호
    private int pageStart;						// 하단 네비게이션 시작 페이지 번호
    private int pageEnd;						// 하단 네비게이션 끝 페이지 번호

    // 기본 값 설정
    BvfSearchVO() {
        pagePer = 20;
        naviNo = 5;
        if(startDate == null) { startDate = "2022-04-01"; }
        if(startTime == null) { startTime = "00:00"; }
        if(endDate == null) { endDate = "2022-12-30"; }
        if(endTime == null) { endTime = "23:59"; }
        if(pageTarget == 0) { pageNo = 1; }
    }

    // 페이지 네비게이션 설정
    public void compute(int total) {
        totalNo = total;
        startNo = (pageNo-1)*pagePer;
        pageTotal = (int) Math.ceil((double)totalNo/pagePer);
        pageStart = (((pageNo-1)/naviNo)*naviNo)+1;
        pageEnd = pageStart+naviNo -1;
        System.out.println("===" + this.toString());
    }
}
