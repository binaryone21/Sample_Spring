package seunghee.tools;

import java.util.HashMap;
import java.util.Map;

public class SearchTool {

    public static void compute(Map<String, Object> map, int totalNo) {
        int pagePer     = Integer.parseInt((String) map.get("pagePer"));
        int pageNavi    = Integer.parseInt((String) map.get("pageNavi"));
        int pageNo      = Integer.parseInt((String) map.get("pageNo"));
        int startNo     = (pageNo-1) * pagePer;
        int pageTotal   = (int)Math.ceil((double)totalNo / pagePer);
        int pageStart   = (((pageNo-1) / pageNavi) * pageNavi) + 1;
        int pageEnd     = pageStart + pageNavi - 1;

        map.put("pagePer",      pagePer);
        map.put("pageNavi",     pageNavi);
        map.put("pageNo",       pageNo);
        map.put("total",        totalNo);
        map.put("startNo",      startNo);
        map.put("pageTotal",    pageTotal);
        map.put("pageStart",    pageStart);
        map.put("pageEnd",      pageEnd);
    }
}
