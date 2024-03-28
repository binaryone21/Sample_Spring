package seunghee.module;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExcelModule {
	private static final Logger logger = LoggerFactory.getLogger(ExcelModule.class);

	// 나중에는 Path 를 전역변수로 빼보자 절대 안변하는 상수값인데....
	static String EXCEL_PATH = "template/excel/";

	/** 템플릿 다운로드 */
	public static void downloadTemplate(HttpServletRequest req, HttpServletResponse res, List<Map<String, Object>> dataList) throws Exception {
		req.setCharacterEncoding("UTF-8");
		String fileName = req.getParameter("fileName");

		String path = req.getServletContext().getRealPath(EXCEL_PATH + fileName);
		InputStream fis = new FileInputStream(path);
		Workbook workbook = new HSSFWorkbook(fis);

		/** 데이터가 있다다면, 데이터 세팅 */
		if(dataList != null) {
			Sheet sheet = workbook.getSheetAt(0);
			for(int rowIdx=0; rowIdx<dataList.size(); rowIdx++) {
				Row row = sheet.getRow(rowIdx+1);	// 임의로 2줄부터 시작, 나중에는 자동화 할 수 있도록
				int cellIdx = 1;
				Map<String, Object> data = dataList.get(rowIdx);
				Set<String> keys = data.keySet();
				for(String key : keys) {
					row.getCell(cellIdx++).setCellValue(String.valueOf(data.get(key)));
				}
			}
		}

		fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");	// 한글 파일명 설정
		res.setContentType("application/vsd.ms-excel");
		res.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
		workbook.write(res.getOutputStream());
	}
	public static void downloadTemplate(HttpServletRequest req, HttpServletResponse res) throws Exception {
		downloadTemplate(req, res, null);
	}
}
