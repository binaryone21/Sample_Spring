package seunghee.excel;

import org.springframework.stereotype.Service;
import seunghee.sample.SampleService;
import seunghee.module.ExcelModule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {
	/** 템플릿 다운로드 */
	public void downloadTemplate(HttpServletRequest req, HttpServletResponse res)  throws Exception {
		ExcelModule.downloadTemplate(req, res);
	}

	/** 템플릿 다운로드, 데이터와 함께 */
	public void downloadTemplateWithData(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Map<String, Object>> dataList = SampleService.getDataList();
		ExcelModule.downloadTemplate(req, res, dataList);
	}
}
