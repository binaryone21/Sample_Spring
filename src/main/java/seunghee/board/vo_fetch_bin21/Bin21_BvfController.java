package seunghee.board.vo_fetch_bin21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Bin21_BvfController {

    @Autowired
    private Bin21_BvfService bvfSvc;

    // Board 화면조회
    @RequestMapping("/board/vo_fetch_bin21/list")
    public String BVFList(Bin21_BvfSearchVO searchVO, Model model) {
        model.addAttribute("search", searchVO);
        return "board/vo_fetch_bin21/bin21_bvfList";
    }

    // Board 목록 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch_bin21/list/fetch")
    public Map<String, Object> BVFListFetch(@RequestBody Bin21_BvfSearchVO searchVO) {
        Map<String, Object> map = new HashMap<>();
        searchVO = bvfSvc.searchBVF(searchVO);
        map.put("search", searchVO);
        map.put("list", bvfSvc.arraysBVF(searchVO));
        return map;
    }

    // Board 상세 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch_bin21/view/fetch")
    public Bin21_BvfDTO BVFViewFetch(@RequestBody String tp_pk) {
        return bvfSvc.selectBVF(tp_pk);
    }

    // Board 등록 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch_bin21/insert/fetch")
    public String BVFInsertFetch(@RequestBody Bin21_BvfDTO view) {
        System.out.println(view.toString());
        return bvfSvc.insertBVF(view);
    }

    // Board 수정 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch_bin21/update/fetch")
    public String BVFUpdateFetch(@RequestBody Bin21_BvfDTO view) {
        System.out.println(view.toString());
        return bvfSvc.updateBVF(view);
    }

    // Board 수정 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch_bin21/delete/fetch")
    public String BVFDeleteFetch(@RequestBody String tp_pk) {
        System.out.println(tp_pk);
        return bvfSvc.deleteBVF(tp_pk);
    }

}
