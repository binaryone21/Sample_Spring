package seunghee.excel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import seunghee.sample.SampleService;
import seunghee.tools.ExcelTool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {
	/** 템플릿 다운로드 */
	public void downloadTemplate(HttpServletRequest req, HttpServletResponse res)  throws Exception {
		ExcelTool.downloadTemplate(req, res);
	}

	/** 템플릿 다운로드, 데이터와 함께 */
	public void downloadTemplateWithData(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Map<String, Object>> dataList = SampleService.getDataList();
		ExcelTool.downloadTemplate(req, res, dataList);
	}
}
