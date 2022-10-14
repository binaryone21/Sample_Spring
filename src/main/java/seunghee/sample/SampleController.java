package seunghee.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @GetMapping("/sample/sample")
    public String sampleSample() {
        return "sample/sample";
    }

    @GetMapping("/sample/sample1")
    public String sampleSample1() {
        return "sample/sample1";
    }

    @GetMapping("/sample/sample2")
    public String sampleSample2() {
        return "sample/sample2";
    }


}
