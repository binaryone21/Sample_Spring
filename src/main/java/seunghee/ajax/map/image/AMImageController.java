package seunghee.ajax.map.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

@RequestMapping("/ajax/map/image")
@Controller
public class AMImageController {

    @Autowired private AMImageService service;

    @GetMapping("/uploadMultiple")
    public String uploadMultiplePage() {
        return "/ajax/map/image/uploadMultiple";
    }

    @ResponseBody
    @PostMapping("/uploadMultiple.ajax")
    public Map<String, Object> uploadMultiple(MultipartHttpServletRequest req) {
        return service.uploadMultiple(req);
    }
}
