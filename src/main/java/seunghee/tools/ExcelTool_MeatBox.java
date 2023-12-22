package seunghee.tools;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import seunghee.common.ListTool;
import seunghee.common.ObjectTool;
import seunghee.common.PrintTool;
import seunghee.excel.CellAttrVO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelTool_MeatBox {
	private static final Logger logger = LoggerFactory.getLogger(ExcelTool.class);

	/**
	 * 워크북 포맷이 XSSF 이면 true를 반환.
	 *
	 * @param	workbook	검사할 워크북
	 */
	public static final boolean isXSSF(Workbook workbook) {
		return workbook instanceof XSSFWorkbook;
	}

	public static final void writeUsingTemplate(List<Map<String, Object>> dataList, String newExcelPath, String templatePath) throws Exception {
		Workbook templateWorkbook = getWorkbook(templatePath);		// Excel 파일을 가져옴
		boolean isXSSF = isXSSF(templateWorkbook);					// XSSF 인지 HSSF 인지 판단
		Workbook newWorkbook = createWorkbook(isXSSF);				// 새로운 Excel 파일을 만듬 (빈파일)

		int templateSheetCount = templateWorkbook.getNumberOfSheets();				// 통합문서의 스프레드시트 수를 가져옴
		for (int templateSheetIdx = 0; templateSheetIdx < templateSheetCount; templateSheetIdx++) {
			Sheet templateSheet = templateWorkbook.getSheetAt(templateSheetIdx);	// 지정된 Idx 의 Sheet 가져옴
			int templateRowCount = templateSheet.getLastRowNum() + 1;				// 시트의 마지막 행을 가져옴 한번이라도 수정했다면 그 행도 인식
			int templateMappingRowIdx = getMappingRowIdx(templateSheet);			// 0열에 존재하는 '매핑' 글자를 찾아 로우 찾기
			if (templateSheetIdx == -1) {											// '매핑' 로우가 존재하지 않을 때
				logger.warn("매핑로우가 존재하지 않습니다. [" + templateSheet.getSheetName() + "]");
				copySheet(templateSheet, newWorkbook);
				continue;
			}

			int templateDataRowIdx = templateMappingRowIdx + 1;						// 템플릿 데이터 로우 인덱스 = 템플릿 매핑 로우 인덱스 + 1
			Row templateMappingRow = templateSheet.getRow(templateMappingRowIdx);	// 템플링 매핑 로우
			Row templateDataRow = templateSheet.getRow(templateDataRowIdx);			// 템플릿 데이터 로우
			if(templateDataRow == null) {											// 템플릿 데이터 로우가 없다면
				logger.warn("데이터로우가 존재하지 않습니다. [" + templateSheet.getSheetName() + "]");
				copySheet(templateSheet, newWorkbook);
				continue;
			}

			int mappingCellCount = templateMappingRow.getLastCellNum() - 1;			// 매핑 셀 개수(첫번째 셀 제외 0은 '매핑')
			int dataCellCount = templateDataRow.getLastCellNum() - 1;				// 데이타 셀 개수(첫번째 셀 제외 0은 '매핑')
			if(dataCellCount < mappingCellCount) {									// 데이터 셀 개수가 매핑 셀 개수보다 적을 수는 없다.
				logger.warn(String.format("데이터 셀 개수[%d]가 매핑 셀 개수[%d]보다 적습니다.[%s]", dataCellCount, mappingCellCount, templateSheet.getSheetName()));
				copySheet(templateSheet, newWorkbook);
				continue;
			}

			int dataCount = ListTool.size(dataList);								// 데이터 갯수
			String[] mappingNameArr = new String[mappingCellCount];					// 매핑명 배열
			CellAttrVO[] templateDataCellAttrArr = new CellAttrVO[dataCellCount];	// 템플릿 데이터 셀 속성 배열 @todo  => String[] 으로 바꿔보자
			for(int columnIdx=0; columnIdx<mappingCellCount; columnIdx++) {			// 매핑명 세팅
				mappingNameArr[columnIdx] = StringUtils.trim(String.valueOf(templateMappingRow.getCell(columnIdx + 1)));	// mappingNameArr 에 th 순서대로 넣어줌
			}
			logger.info("시트 이름 : " + templateSheet.getSheetName());
			logger.info("템플릿 로우 갯수 : " + templateRowCount);
			logger.info("매핑 로우 인덱스 : " + templateMappingRowIdx);
			logger.info("매핑 셀 개수 : " + mappingCellCount);
			logger.info("데이터 셀 개수 : " + dataCellCount);
			logger.info("데이터 개수 : " + dataCount);
			logger.info("매핑명");
			PrintTool.print(mappingNameArr);

			for(int dataCellIdx=0; dataCellIdx<dataCellCount; dataCellIdx++) {
				Cell templateCell = templateDataRow.getCell(dataCellIdx + 1);		// 데이터 셀을 돌면서
				CellAttrVO cellAttrVO = null;
				if(templateCell != null) {
					CellStyle cs = newWorkbook.createCellStyle();					// 셀 스타일
					cs.cloneStyleFrom(templateCell.getCellStyle());					// 셀 스타일을 복사
					cs.setWrapText(true);											// 셀 내부 텍스트를 줄바꿈할지 여부

					// 개행문자 처리
					cs.setWrapText(true);											// 왜 두번한거임? @todo => 개행문자?
					cellAttrVO = new CellAttrVO(cs, templateCell.getCellType(), getValue(templateCell));	// 스타일, 타입, 값
				}
				templateDataCellAttrArr[dataCellIdx] = cellAttrVO;					// cellAttrVO 배열
			}

			int newSheetCount = 0;
			Sheet newSheet = null;
			int currentRowIdx = -1;

			if(isXSSF) {
				newSheetCount = 1;
				currentRowIdx = -1;

				// 시트생성
				newSheet = newWorkbook.createSheet();
				applyAttr(newSheet, templateSheet, 1);	// 템플릿 속성 적용 (템플릿의 두번째 셀부터 새로운 시트에 적용)

				// 컬럼명 로우 복사 (매핑 로우 위 모두 복사)
				for(int ii=0; ii<templateMappingRowIdx; ii++) {
					copyRow(templateSheet.getRow(ii), newSheet, 1);		// 두번째 셀부터 복사
					currentRowIdx++;													// @todo currentRowIdx = templateMappingRowIdx 해도 되지 않았을 까
				}

				createDataRow(newSheet, currentRowIdx+1, mappingCellCount, templateDataRow, dataCellCount, templateDataCellAttrArr, mappingNameArr, dataList, 0, -1);
			} else {					// HSSF 는 최대 65536줄 이상 못넘어가나봐
				int headerRowCount = templateMappingRowIdx;								// 데이터로우 위에 있는 로우 개수
				int availableDataRowCount = 65536 - headerRowCount;						// 데이터 기준 페이징 처리(시트당 데이터 몇줄)

				newSheetCount = (int)(dataCount + (availableDataRowCount - 1)) / availableDataRowCount;
				for(int newSheetIdx=0; newSheetIdx<newSheetCount; newSheetIdx++) {
					currentRowIdx = -1;

					// 시트 생성
					newSheet = newWorkbook.createSheet();
					applyAttr(newSheet, templateSheet, 1);					// 템플릿 속성 적용 (템플릿 두번째 셀부터 새로운 시트에 적용)
					newWorkbook.setSheetName(newSheetIdx, templateSheet.getSheetName() + (newSheetIdx + 1));

					// 컬럼명 로우 복사 (매핑 로우 위 모두 복사)
					for(int ii=0; ii<templateMappingRowIdx; ii++) {
						copyRow(templateSheet.getRow(ii), newSheet, 1);
						currentRowIdx++;												// @todo currentRowIdx = templateMappingRowIdx 해도 되지 않았을 까
					}
					int dataBeginIdx = newSheetIdx * availableDataRowCount;				// 데이터 몇번째 부터 넣을건지
					int dataEndIdx = (newSheetIdx + 1) * availableDataRowCount - 1;		// 데이터 몇번째 까지 넣을건지
					if(dataEndIdx > (dataCount - 1)) {									// 데이터 입력 가능 수가, 데이터 수보다 많으면
						dataEndIdx = dataCount -1;
					}

					createDataRow(newSheet, currentRowIdx+1, mappingCellCount, templateDataRow, dataCellCount, templateDataCellAttrArr, mappingNameArr, dataList, dataBeginIdx, dataEndIdx);
				}
			}
			if (isXSSF || (newSheetCount == 1 && dataCount < 60000)) {
				//나머지 로우
				for(int etcIdx=templateDataRowIdx+1; etcIdx<templateRowCount; etcIdx++) {
					copyRow(templateSheet.getRow(etcIdx), newSheet, 1);
					currentRowIdx++;
				}
				// 셀 병합정보 적용(Top)
				applyMergedTopRegions(templateSheet, newSheet, templateMappingRowIdx-1, 1);

				// 셀 병합정보 적용(Bottom)
				applyMergedBottomRegions(templateSheet, newSheet, templateDataRowIdx+1, 1, dataCount-2);
			}
		}

		// 임시 파일 생성
		FileOutputStream fos = new FileOutputStream(newExcelPath);
		newWorkbook.write(fos);
		fos.close();
	}
	/**
	 * 엑셀파일을 읽어 Workbook 을 반환.
	 *
	 * @param	excelPath	읽을 엑셀파일 경로
	 */
	public static final Workbook getWorkbook(String excelPath) throws Exception {
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = WorkbookFactory.create(fis);
		fis.close();
		return workbook;
	}

	/**
	 * 워크북 생성.
	 *
	 * @param	isXSSF		OLE2여부(xlsx 파일인 경우)
	 */
	public static final Workbook createWorkbook(boolean isXSSF) {
		Workbook wb = null;
		if(isXSSF) {
			wb = new XSSFWorkbook();
		} else {
			wb = new HSSFWorkbook();
		}
		return wb;
	}

	public static final int getMappingRowIdx(Sheet templateSheet) {
		if (templateSheet == null) {
			return -1;
		}
		int rowCount = templateSheet.getLastRowNum() + 1;    // 빈 시트일 경우 로우가 한개 존재
		int idx = -1;    // 매핑 로우 찾기
		for(int ii=0; ii<rowCount; ii++) {
			Row tmpRow = templateSheet.getRow(ii);
			if(tmpRow != null) {
				if(StringUtils.equals("매핑", getString(tmpRow.getCell(0)))) {
					idx = ii;
					break;
				}
			}
		}

		return idx;
	}

	/**
	 * 셀의 값을 문자열로 반환.
	 *
	 * @param	cell	셀
	 */
	public static final String getString(Cell cell) {
		String value = null;
		Object obj = getValue(cell);
		if(obj instanceof Double) {
			value = ((Double)obj).toString();
		} else {
			value = (String) obj;
		}
		return value;
	}

	/**
	 * 셀 타입별로 값 꺼내기.
	 * 셀타입이 함수일 때는 함수문자열
	 *
	 * @param 	cell	셀
	 */
	public static final Object getValue(Cell cell) {
		if(cell == null) {
			return null;
		}
		Object obj = null;
		switch(cell.getCellType()) {
			case NUMERIC : obj = cell.getNumericCellValue(); break;
			case STRING : obj = cell.getStringCellValue(); break;
			case FORMULA: obj = cell.getCellFormula(); break;
			default:
		}
		return obj;
	}

	/**
	 * Cell 에 Object 별로 값 세팅
	 * 셀값을 매핑해서 적용
	 *
	 * @param cell		대상셀
	 * @param value		셀값
	 * @param mappings	소스셀의 값이 "#"로 시작하고 "#"로 끝날 경우 적용할 값 정보
	 */
	public static final void setValue(Cell cell, Object value, Map<String, Object> mappings) {
		if(cell.getCellType() == CellType.FORMULA) {
			cell.setCellFormula((String) value);
			return;
		}
		if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if(value instanceof Calendar) {
			cell.setCellValue((Calendar) value);
		} else if(value instanceof Date) {
			cell.setCellValue((Date) value);
		} else if(value instanceof Double) {
			cell.setCellValue((Double) value);
		} else if(value instanceof RichTextString) {
			cell.setCellValue((RichTextString) value);
		} else if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if(value instanceof Long) {
			cell.setCellValue((Long) value);
		} else {
			// 셀값을 이용해서 적용
			String v = (value == null) ? null : value.toString();
			if(mappings != null && v != null) {
				if(StringUtils.startsWith(v, "#") && StringUtils.endsWith(v, "#")) {
					v = MapUtils.getString(mappings, v);
				}
			}
			cell.setCellValue(v);
		}
	}
	public static final void setValue(Cell cell, Object value) {
		setValue(cell, value, null);
	}

	/**
	 * 소스시트를 타겟워크북에 copy (소스셀 값 포함).
	 *
	 * @param sourceSheet		복사할 시트
	 * @param targetWorkbook	소스 시트를 붙여넣기 할 워크북
	 */
	public static void copySheet(Sheet sourceSheet, Workbook targetWorkbook) {
		if(targetWorkbook == null) {
			return;
		}

		// 시트명
		Sheet newSheet = targetWorkbook.createSheet();
		applyAttr(newSheet, sourceSheet);	// 템플릿 속성 적용

		int rowCount = sourceSheet.getLastRowNum() + 1;	// 빈 시트일 경우에 로우가 한 개 존재
		for(int rowIdx=0; rowIdx<rowCount; rowIdx++) {
			Row row = sourceSheet.getRow(rowIdx);
			copyRow(row, newSheet);
		}
	}

	/**
	 * 소스로우를 라켓시트에 copy(소스셀 값 포함)
	 *
	 * @param sourceRow		복사할 로우
	 * @param targetSheet	소스로우에 붙여넣기 할 시트
	 * @param startColumnIdx	copy할 로우의 copy할 셀 시작인덱스
	 * @param mappings		소스셀의 값이 "#"로 시작하고 "#"로 끝날 경우 적용할 값 정보
	 */
	public static void copyRow(Row sourceRow, Sheet targetSheet, int startColumnIdx, Map<String, Object> mappings) {
		if(targetSheet == null) {
			return;
		}
		Row newRow = targetSheet.createRow(targetSheet.getPhysicalNumberOfRows()); // 새로운 로우 생성
		applyAttr(newRow, sourceRow);
		if(sourceRow == null) {
			return;
		}

		int cellCount = sourceRow.getLastCellNum()+1;
		for(int columnIdx = startColumnIdx; columnIdx<cellCount; columnIdx++) {
			Cell cell = sourceRow.getCell(columnIdx);
			copyCell(cell, newRow, null, mappings);
		}
	}
	public static void copyRow(Row sourceRow, Sheet targetSheet, int startColumnIdx) {
		copyRow(sourceRow, targetSheet, startColumnIdx, null);
	}
	public static void copyRow(Row sourceRow, Sheet targetSheet) {
		copyRow(sourceRow, targetSheet, 0);
	}

	/**
	 * 소스셀을 타겟로우에 셀속성을 적용해서 copy (소스셀 값 포함)
	 * 셀속성이 없으면 소스셀의 속성을 적용
	 *
	 * @param sourceCell	복사할 셀
	 * @param targetRow		소스셀을 붙여넣기 할 로우
	 * @param cellAttrVO	새로운 셀에 적용할 속성
	 * @param mappings		소스세르이 값이 "#"로 시작하고 "#"로 끝날 경우 적용할 값 정보
	 */
	public static void copyCell(Cell sourceCell, Row targetRow, CellAttrVO cellAttrVO, Map<String, Object> mappings) {
		if(targetRow == null) {
			return;
		}

		Cell newCell = targetRow.createCell(targetRow.getPhysicalNumberOfCells());	// 새로운 셀 생성
		if(cellAttrVO == null) {
			applyAttr(newCell, sourceCell);
		} else {
			applyCellAttr(newCell, cellAttrVO);
		}
		setValue(newCell, getValue(sourceCell), mappings);
	}
	public static void copyCell(Cell sourceCell, Row targetRow, CellAttrVO cellAttrVO) {
		copyCell(sourceCell, targetRow, cellAttrVO, null);
	}
	public static void cellCell(Cell sourceEll, Row targetRow) {
		copyCell(sourceEll, targetRow, null);
	}


	/**
	 * 소스시트의 속성(시트명,칼럼너비)을 타겟시트에 startCellIdx 셀부터 적용.
	 *
	 * @param	targetSheet		타겟시트
	 * @param	sourceSheet		소스 시트
	 * @param	startCellIdx	적용할 셀의 인덱스
	 */
	public static final void applyAttr(Sheet targetSheet, Sheet sourceSheet, int startCellIdx) {
		if(targetSheet == null || sourceSheet == null) {
			return;
		}

		// 시트명
		Workbook wb = targetSheet.getWorkbook();
		wb.setSheetName(wb.getSheetIndex(targetSheet), sourceSheet.getSheetName());
		if(sourceSheet.getPhysicalNumberOfRows() == 0) {
			return;
		}

		Row firstRow = sourceSheet.getRow(sourceSheet.getFirstRowNum());
		if(firstRow != null) {
			int cellCount = firstRow.getLastCellNum() + 1;
			// 칼럼 너비
			for(int cellIdx = startCellIdx; cellIdx<cellCount; cellIdx++) {
				targetSheet.setColumnWidth(cellIdx-startCellIdx, sourceSheet.getColumnWidth(cellIdx));
			}
		}

	}
	public static final void applyAttr(Sheet targetSheet, Sheet sourceSheet) {
		applyAttr(targetSheet, sourceSheet, 0);
	}

	/**
	 * 소스로우의 속성(높이)을 타겟로우에 적용.
	 *
	 * @param targetRow		타겟로우
	 * @param sourceRow		소스로우
	 */
	public static final void applyAttr(Row targetRow, Row sourceRow) {
		if(targetRow == null || sourceRow == null) {
			return;
		}

		targetRow.setHeight(sourceRow.getHeight());
	}

	/**
	 * 소스셀의 속성(스타일,타입)을 타겟셀에 적용.
	 *
	 * @param targetCell	타겟 셀
	 * @param sourceCell	소스 셀
	 */
	public static final void applyAttr(Cell targetCell, Cell sourceCell) {
		if(targetCell == null || sourceCell == null) {
			return;
		}
		// 셀 타입
		targetCell.setCellType(sourceCell.getCellType());

		// 셀 스타일
		CellStyle cs =targetCell.getSheet().getWorkbook().createCellStyle();
		cs.cloneStyleFrom(sourceCell.getCellStyle());
		targetCell.setCellStyle(cs);
	}

	/**
	 * 타겟셀에 셀 속성(스타일,타입) 적용.
	 *
	 * @param targetCell	적용받을 셀
	 * @param cellAttr		적용할 셀 속성
	 */
	public static final void applyCellAttr(Cell targetCell, CellAttrVO cellAttr) {
		if(targetCell == null || cellAttr == null) {
			return;
		}

		if(cellAttr.getType() != CellType._NONE) {
			targetCell.setCellType(cellAttr.getType());	// 셀 타입
		}
		if(cellAttr.getStyle() != null) {
			targetCell.setCellStyle(cellAttr.getStyle());
		}
	}

	/**
	 * 데이터 로우 생성
	 *
	 * @param newSheet
	 * @param beginRowIdx
	 * @param mappingCellCount
	 * @param templateDataRow
	 * @param dataCellCount
	 * @param templateDataCellAttrArr
	 * @param mappingNameArr
	 * @param dataList
	 * @param dataBeginIdx
	 * @param dataEndIdx
	 */
	public static final void createDataRow(Sheet newSheet, int beginRowIdx, int mappingCellCount, Row templateDataRow, int dataCellCount, CellAttrVO[] templateDataCellAttrArr, String[] mappingNameArr, List<Map<String, Object>> dataList, int dataBeginIdx, int dataEndIdx) throws Exception {
		// 데이터 로우 생성
		for(int dataIdx=dataBeginIdx; dataIdx<=dataEndIdx; dataIdx++) {
			Row dataRow = newSheet.createRow(beginRowIdx++);
			applyAttr(dataRow, templateDataRow);	// 템플릿 속성 적용

			// 매핑명에 따른 데이터 셀 생성 (매핑명 개수만큼)
			for(int columnIdx=0; columnIdx<mappingCellCount; columnIdx++) {
				Cell newCell = dataRow.createCell(columnIdx);
				applyCellAttr(newCell, templateDataCellAttrArr[columnIdx]);
				if(StringUtils.isNotBlank(mappingNameArr[columnIdx])) {
					setValue(newCell, ObjectTool.getObject(dataList.get(dataIdx), mappingNameArr[columnIdx]));
				} else {
					setValue(newCell, templateDataCellAttrArr[columnIdx].getValue());
				}
			}
			// 나머지 셀 복사
			for(int etcColumnIdx = mappingCellCount; etcColumnIdx<dataCellCount; etcColumnIdx++) {
				copyCell(templateDataRow.getCell(etcColumnIdx+1), dataRow, templateDataCellAttrArr[etcColumnIdx]);
			}
		}
	}

	/**
	 * 소스시트의 특정로우까지(toRowIdx)의 셀 병합정보를 excludingCellCount 만큼 제외하고 타겟시트에 적용.
	 *
	 * @param	sourceSheet			소스시트
	 * @param	targetSheet			타겟시트
	 * @param	toRowIdx			로우구간 끝인덱스
	 * @param	excludingCellCount	제외할 셀 갯수
	 */
	public static void applyMergedTopRegions(Sheet sourceSheet, Sheet targetSheet, int toRowIdx, int excludingCellCount) {
		if(sourceSheet == null || targetSheet == null) {
			return;
		}

		int mergedCount = sourceSheet.getNumMergedRegions();
		for(int mergedIdx=0; mergedIdx<mergedCount; mergedIdx++) {
			CellRangeAddress addr = sourceSheet.getMergedRegion(mergedIdx);
			logger.info(addr.getFirstRow() + "," + addr.getLastRow() + "," + addr.getFirstColumn() + "," + addr.getLastColumn());
			if(addr.getLastRow()<=toRowIdx) {
				targetSheet.addMergedRegion(new CellRangeAddress(addr.getFirstRow(), addr.getLastRow(), addr.getFirstColumn()-excludingCellCount, addr.getLastColumn()-excludingCellCount));
			}
		}
	}

	/**
	 * 소스시트의 특정로우부터(toRowIdx)의 셀 병합정보를 excludingCellCount 만큼 제외하고 타겟시트에 적용.
	 *
	 * @param	sourceSheet			소스시트
	 * @param	targetSheet			타겟시트
	 * @param	fromRowIdx			소스시트의 로우구간 시작인덱스
	 * @param	excludingCellCount	제외할 셀 갯수
	 * @param	rowDistance			병합셀의 로우 증감분
	 */
	public static void applyMergedBottomRegions(Sheet sourceSheet, Sheet targetSheet, int fromRowIdx, int excludingCellCount, int rowDistance) {
		if (sourceSheet == null || targetSheet == null) {
			return;
		}

		int mergedCount = sourceSheet.getNumMergedRegions();
		for (int mergedIdx = 0;  mergedIdx < mergedCount;  mergedIdx++) {
			CellRangeAddress addr = sourceSheet.getMergedRegion (mergedIdx);
			logger.info ("# " +fromRowIdx);
			logger.info (addr.getFirstRow() + "," + addr.getLastRow() + "," + addr.getFirstColumn() + "," + addr.getLastColumn());
			if (addr.getFirstRow() >= fromRowIdx) {
				targetSheet.addMergedRegion (new CellRangeAddress (
						addr.getFirstRow()		+ rowDistance
						,addr.getLastRow()		+ rowDistance
						,addr.getFirstColumn()	- excludingCellCount
						,addr.getLastColumn()	- excludingCellCount
				));
			}
		}
	}
}
