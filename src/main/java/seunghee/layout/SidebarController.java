package seunghee.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SidebarController {

    @RequestMapping("/layout/sidebar1")
    public String layoutSidebar1() {
        return "layout/sidebar/sidebar_1";
    }
}
