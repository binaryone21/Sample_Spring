package utill;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JavaToJS {
    public static String javaToJS(String param) throws FileNotFoundException, ScriptException {
        File js = new File("/src/main/webapp/resources/js/kakao.js");
        FileReader jsfr = new FileReader(js);

        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine se = sem.getEngineByName("js");

        String result = (String) se.eval(jsfr);
        return result;
    }
}
