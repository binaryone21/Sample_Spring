package seunghee.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import seunghee.module.ImageModule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ImageService {
	@Autowired
	private ImageRepository repository;

	/** PDF 다운로드 */
	public void downloadPDF(HttpServletRequest req, HttpServletResponse res) throws Exception {
		ImageModule.downloadPDF(req, res);
	}

	/** 이미지 멀티 업로드 */
	public Map<String, Object> uploadMultiple(MultipartHttpServletRequest req) {
		List<String> imgPaths = ImageModule.uploadImage(req);

		/**
		 * todo : 나중에 이부분 List 로 던져서 뒤에서 받는걸로 해보자
		 * todo : 왜냐하면 이미지 개수만큼 DB 찌르는건 부담됨
		 * todo : int count = repository.insertImagePathList(imageMap);
		 */
		int count = 0;
		for(String imgPath : imgPaths) {
			Map<String, Object> imageMap = new HashMap<>();
			imageMap.put("imagePath", imgPath);
			count += repository.insertImagePath(imageMap);
		}
		/** */

		String message = (count == imgPaths.size()) ? "이미지 등록 성공" : "이미지 등록 실패";

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", message);
		return resultMap;
	}
}
