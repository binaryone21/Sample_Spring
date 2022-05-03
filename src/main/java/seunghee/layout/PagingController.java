package seunghee.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagingController {

    @GetMapping("layout/paging")
    public String layoutPaging() {
        return "layout/paging/paging";
    }
}
