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

    @RequestMapping("/board/vo_fetch_bin21/list")
    public String BVFList(Bin21_BvfSearchVO searchVO, Model model) {
        model.addAttribute("search", searchVO);
        return "board/vo_fetch_bin21/bin21_bvfList";
    }

    @ResponseBody
    @RequestMapping("/board/vo_fetch_bin21/list/fetch")
    public Map<String, Object> BVFListFetch(@RequestBody Bin21_BvfSearchVO searchVO) {
        Map<String, Object> map = new HashMap<>();
        searchVO = bvfSvc.searchBVF(searchVO);
        map.put("search", searchVO);
        map.put("list", bvfSvc.arraysBVF(searchVO));
        return map;
    }
}
