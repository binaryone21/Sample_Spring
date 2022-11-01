package seunghee.image.ajax_map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import seunghee.image.ImageService;
import seunghee.tools.ImageTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageService_am {

    @Autowired
    private ImageRepository_am imageRepository;

    public Map<String, Object> multipleUploadAjax(MultipartHttpServletRequest req) throws Exception {
        List<String> imgPaths = ImageTool.uploadImage("/image/ajax_map/img", req);
        for(String imgPath : imgPaths) {
            Map<String, Object> params = new HashMap<>();
            params.put("image_path", imgPath);
            imageRepository.insertImagePath(params);
        }
        return null;
    }
}
