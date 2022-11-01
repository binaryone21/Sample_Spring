package seunghee.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import seunghee.tools.ImageTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public Map<String, Object> imageUploadAjax(MultipartHttpServletRequest req) throws Exception {
        List<String> imgPaths = ImageTool.uploadImage("/image/img", req);
        for(String imgPath : imgPaths) {
            Map<String, Object> params = new HashMap<>();
            params.put("image_path", imgPath);
            imageRepository.insertImageFile(params);
        }
        return null;
    }

}
