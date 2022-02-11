package kakaoChatbot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import utill.utillJS;

import javax.script.ScriptException;
import java.io.FileNotFoundException;

@Controller
public class KakaoController {

    private KakaoService kkSer;

    /* 기본 텍스트  */
    @RequestMapping(value="/simpleText")
    public String simpleText() {
        String param = "";
        String result = "";
        try {
            result = utillJS.chatbotJS("simpleText", param);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value="/select", method= RequestMethod.GET)
    public String select() {
        kkSer.select();
        return "";
    }
}
