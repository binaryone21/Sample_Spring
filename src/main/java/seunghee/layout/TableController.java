package seunghee.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TableController {

    @GetMapping("layout/table")
    public String layoutSearch() {
        return "layout/table/table";
    }
}
