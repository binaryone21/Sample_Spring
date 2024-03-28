package seunghee.excel;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import seunghee.sample.SampleService;
import seunghee.module.ExcelModule_MeatBox;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService_MeatBox {
	public void downloadExcel(HttpServletRequest req, HttpServletResponse res) throws Exception {
		List<Map<String, Object>> dataList = SampleService.getDataList();

		/*
		File file = new File("");
		String rootPath1 = String.valueOf(file.getAbsoluteFile());
		String rootPath2 = String.valueOf(file.getPath());
		String rootPath3 = System.getProperty("user.dir");
		*/

		String path = req.getServletContext().getRealPath("template");

		String newExcelFilePath = path + "/tmp/" + System.currentTimeMillis() + ".xls";
		String templateFilePath = path + "/excel/" + "Sample" + ".xls";

		ExcelModule_MeatBox.writeUsingTemplate(dataList, newExcelFilePath, templateFilePath);

		String	filePath	= newExcelFilePath;
		String	fileName	= "Sample.xls";
		String	removeYn	= "Y";

		File f = new File (filePath);

		res.setContentType("text/html;charset=ISO-8859-1");
		res.setContentLength ((int)f.length());

		// res.setHeader ("Content-Disposition",		"attachment; filename=\"" + URLEncoder.encode (fileName, "UTF-8").replace ("+","%20") + "\";");
		res.setHeader ("Content-Disposition",		"attachment; filename=" +new String (("\""+fileName+"\"").getBytes ("EUC-KR"), "8859_1"));
		res.setHeader ("Content-Transfer-Encoding",	"binary");

		OutputStream os	= res.getOutputStream();
		FileInputStream fis	= null;

		try {
			fis = new FileInputStream (f);
			FileCopyUtils.copy (fis, os);
			if ("Y".equals (removeYn)) {
				FileUtils.deleteQuietly (f);
			}
		} finally {
			IOUtils.closeQuietly (fis);
		}

		os.flush();
		os.close();
	}
}
