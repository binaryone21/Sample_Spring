package utill;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class utillJS {
    static String path = "/src/main/webapp/resources/js";

    public static String chatbotJS(String target, String param) throws FileNotFoundException, ScriptException {
        File js = new File(path + "/kakaoChatbot/" + target + ".js");
        FileReader jsfr = new FileReader(js);

        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("js");

        String result = (String) se.eval(jsfr);
        result = "";
        return result;
    }
}
