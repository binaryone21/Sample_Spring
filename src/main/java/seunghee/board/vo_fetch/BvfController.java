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

    @RequestMapping("/board/vo_fetch/list")
    public String BVFList(BvfSearchVO searchVO, Model model) {
        model.addAttribute("search", searchVO);
        return "board/vo_fetch/bvfList";
    }

    @ResponseBody
    @RequestMapping("/board/vo_fetch/list/fetch")
    public Map<String, Object> BVFListFetch(@RequestBody BvfSearchVO searchVO) {
        Map<String, Object> map = new HashMap<>();
        searchVO = bvfSvc.searchBVF(searchVO);
        map.put("search", searchVO);
        map.put("list", bvfSvc.arraysBVF(searchVO));
        return map;
    }
}
