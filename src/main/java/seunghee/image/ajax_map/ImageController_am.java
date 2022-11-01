package seunghee.image.ajax_map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

@RequestMapping("/image/ajax_map")
@Controller
public class ImageController_am {

    @Autowired
    private ImageService_am imageService;

    @GetMapping("/multipleUploadPage")
    public String multipleUploadPage() {
        return "image/ajax_map/multipleUploadPage";
    }

    @ResponseBody
    @PostMapping("/multipleUploadAjax")
    public Map<String, Object> multipleUploadAjax(MultipartHttpServletRequest req) throws Exception {
        return imageService.multipleUploadAjax(req);
    }
}
