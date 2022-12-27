package seunghee.ajax.map.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import seunghee.tools.ImageTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AMImageService {
    @Autowired private AMImageRepository repository;

    public Map<String, Object> uploadMultiple(MultipartHttpServletRequest req) {
        List<String> imgPaths = ImageTool.uploadImage("/ajax/map/image/img", req);
        int count = 0;
        for(String imgPath : imgPaths) {
            Map<String, Object> imageMap = new HashMap<>();
            imageMap.put("imagePath", imgPath);
            count += repository.insertImagePath(imageMap);
        }
        String message = (count == imgPaths.size())
                ? "이미지 등록 성공"
                : "이미지 등록 실패";

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("message", message);
        return resultMap;
    }
}
