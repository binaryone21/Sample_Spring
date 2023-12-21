package seunghee.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import seunghee.tools.ExcelTool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {
	public List<Map<String, Object>> getDataList() {
		List<Map<String, Object>> dataList = new LinkedList<>();
		for(int i=0; i<3; i++) {
			Map<String, Object> data = new HashMap<>();
			data.put("name", "이름" + i);
			data.put("age", 20 + i);
			dataList.add(data);
		}
		return dataList;
	}

	/** 템플릿 다운로드 */
	public void downloadTemplate(HttpServletRequest req, HttpServletResponse res)  throws Exception {
		ExcelTool.downloadTemplate(req, res);
	}

	/** 템플릿 다운로드, 데이터와 함께 */
	public void downloadTemplateWithData(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Map<String, Object>> dataList = getDataList();
		ExcelTool.downloadTemplate(req, res, dataList);
	}





	public void downloadExcel(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Map<String, Object>> dataList = null;	// 데이터 리스트

		/*
		File file = new File("");
		String rootPath1 = String.valueOf(file.getAbsoluteFile());
		String rootPath2 = String.valueOf(file.getPath());
		String rootPath3 = System.getProperty("user.dir");
		*/

		String path = req.getServletContext().getRealPath("template");

		String newExcelFilePath = path + "/tmp/" + System.currentTimeMillis() + ".xls";
		String templateFilePath = path + "/excel/" + "Sample" + ".xls";

		ExcelTool.writeUsingTemplate(dataList, newExcelFilePath, templateFilePath);

		// ???
		Map<String, Object> fileMap = new HashMap<>();
		fileMap.put("filePath", newExcelFilePath);
		fileMap.put("fileName", "파일이름");
		fileMap.put("removeYn", "Y");
		// ???

	}
}
