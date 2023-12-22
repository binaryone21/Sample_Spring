package seunghee.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/excel")
public class ExcelController {

	@Autowired private ExcelService 		service;
	@Autowired private ExcelService_MeatBox serviceMeatBox;

	@RequestMapping("/page")
	public String excel() {
		return "/excel/page";
	}

	/** 템플릿 다운로드 */
	@RequestMapping("/downloadTemplate.file")
	public void template(HttpServletRequest req, HttpServletResponse res) throws Exception {
		service.downloadTemplate(req, res);
	}

	/** 템플릿 다운로드, 데이터와 함께 */
	@RequestMapping("/downloadTemplateWithData.file")
	public void templateWithData(HttpServletRequest req, HttpServletResponse res) throws Exception {
		service.downloadTemplateWithData(req, res);
	}

	@RequestMapping("/download.file")
	public void ExcelDownload(HttpServletRequest req, HttpServletResponse res) throws Exception {
		serviceMeatBox.downloadExcel(req, res);
	}
}
