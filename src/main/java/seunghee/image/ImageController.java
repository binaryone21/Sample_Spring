package seunghee.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/image")
public class ImageController {

	@Autowired
	private ImageService service;

	@RequestMapping("/page")
	public String filePage() {
		return "/image/page";
	}

	/** PDF 파일 다운로드 */
	@RequestMapping("/downloadPDF.file")
	public void downloadPDF(HttpServletRequest req, HttpServletResponse res) throws Exception {
		service.downloadPDF(req, res);
	}

	/** 이미지 멀티 업로드 */
	@ResponseBody
	@RequestMapping("/uploadMultiple.file")
	public Map<String, Object> uploadMultiple(MultipartHttpServletRequest req) {
		return service.uploadMultiple(req);
	}
}
