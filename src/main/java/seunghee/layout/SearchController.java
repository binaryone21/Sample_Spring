package seunghee.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    @GetMapping("layout/search")
    public String layoutSearch() {
        return "layout/search/search";
    }
}
