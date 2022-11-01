package seunghee.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/image/imageUploadPage")
    public String imageUploadPage() {
        return "image/imageUploadPage";
    }

    @ResponseBody
    @PostMapping("/image/imageUploadAjax")
    public Map<String, Object> imageUploadAjax(MultipartHttpServletRequest req) throws Exception {
        return imageService.imageUploadAjax(req);
    }
}
