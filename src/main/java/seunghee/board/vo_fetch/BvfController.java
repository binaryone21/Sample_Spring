package seunghee.board.vo_fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BvfController {

    @Autowired
    private BvfService bvfSvc;

    // Board 화면
    @RequestMapping("/board/vo_fetch/list")
    public String pageBVF(BvfSearchVO searchVO, Model model) {
        model.addAttribute("search", searchVO);
        return "board/vo_fetch/bvfList";
    }

    // Board 목록 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch/list/fetch")
    public Map<String, Object> arraysBVF(@RequestBody BvfSearchVO searchVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("search", bvfSvc.searchBVF(searchVO));
        map.put("list", bvfSvc.arraysBVF(searchVO));
        return map;
    }

    // Board 상세 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch/view/fetch")
    public BvfDTO selectBVF(@RequestBody String tp_pk) {
        return bvfSvc.selectBVF(tp_pk);
    }

    // Board 등록 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch/insert/fetch")
    public String insertBVF(@RequestBody BvfDTO view) {
        return bvfSvc.insertBVF(view);
    }

    // Board 수정 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch/update/fetch")
    public String updateBVF(@RequestBody BvfDTO view) {
        return bvfSvc.updateBVF(view);
    }

    // Board 삭제 fetch
    @ResponseBody
    @RequestMapping("/board/vo_fetch/delete/fetch")
    public String deleteBVF(@RequestBody String tp_pk) {
        return bvfSvc.deleteBVF(tp_pk);
    }
}
