package kakao;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KakaoController {

    private KakaoService kkSer;

    /* 기본 텍스트  */
    @RequestMapping(value="/simpleText")
    public String simpleText() {
        JavaToJS.
        return "";
    }

    @RequestMapping(value="/select", method= RequestMethod.GET)
    public String select() {
        kkSer.select();
        return "";
    }
}
