package Util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import config.ApiInfoConfig;
import config.ApiParamsConfig;
import pojo.ApiInfo;
import pojo.ApiParams;
import pojo.CellData;

public class ExcelUtil {
	/**
	 *       读取excel数据
	 * @param excelPath   excel路径
	 * @param SheetIndex  sheet索引（0开始）
	 * @param clazz       转化对应的pojo类
	 */
	public static void read(String excelPath, int SheetIndex, Class<?> clazz) {
		try {
			Workbook workbook = WorkbookFactory.create(ExcelUtil.class.getResourceAsStream(excelPath));
			//得到第一个sheet
			Sheet sheet = workbook.getSheetAt(SheetIndex);
			//拿到第一行
			Row firstRow = sheet.getRow(0);
			int lastCellNum = firstRow.getLastCellNum();
			String[] fieldArray = new String[lastCellNum];
			for (int i = 0; i < lastCellNum; i++) {
				//得到当前遍历的cell
				Cell cell = firstRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				//将cell的类型设置为string
				cell.setCellType(CellType.STRING);
				//获得该列string类型的值
				String cellValue = cell.getStringCellValue();
				//通过截串获得对应属性名
				String fieldName = cellValue.substring(0, cellValue.indexOf("("));
				fieldArray[i] = fieldName;
			}
            
			//获取行数
			int lastRowNum = sheet.getLastRowNum();
			//遍历每一行
			for (int i = 1; i <= lastRowNum; i++) {
				//获取列对象
				Row dataRow = sheet.getRow(i);
				//new出传入的字节码类对应的对象存储数据
				Object object = clazz.newInstance();
				//遍历每一行里的列，并取出数据
				for (int j = 0; j < lastCellNum; j++) {
					Cell dataCell = dataRow.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					dataCell.setCellType(CellType.STRING);
					//获取列值
					String cellValue = dataCell.getStringCellValue();
					//获取该列对应的字段名
					String fieldName = fieldArray[j];
					//获取反射方法名
					String setName = "set" + fieldName;
					Method setterMethod = clazz.getMethod(setName, String.class);
					//调用object对象设置属性值
					setterMethod.invoke(object, cellValue);
				}
				if (object instanceof ApiInfo) {
					ApiInfo apiInfo = (ApiInfo) object;
					ApiInfoConfig.add(apiInfo);
				} else if (object instanceof ApiParams) {
					ApiParams apiParams = (ApiParams) object;
					ApiParamsConfig.add(apiParams);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *      结果写回excel
	 * @param caseFilePath       用例路径(类路径)
	 * @param targetFilePath     写回目标路径(相对路径)
	 * @param resultStr          写回结果
	 * @param sheetIndex         写回文档sheet索引
	 * @param cellNum            写回哪一列
	 * @param caseId             caseId
	 */
	public static void write(String caseFilePath, String targetFilePath, String resultStr, int sheetIndex, int cellNum,
			String caseId) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = ExcelUtil.class.getResourceAsStream(caseFilePath);
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			int lastRowNum = sheet.getLastRowNum();
			for (int i = 0; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				String firstCellValue = cell.getStringCellValue();
				if (firstCellValue.equals(caseId)) {
					Cell cellToBeWrite = row.getCell(cellNum - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cellToBeWrite.setCellType(CellType.STRING);
					cellToBeWrite.setCellValue(resultStr);
					break;
				}
			}
			os = new FileOutputStream(new File(targetFilePath));
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 批量写入,通过map的形式
	 *   数据载体Map<String,Map<String,String>>
	 * @param caseFilePath      源文件   
	 * @param targetFilePath    目标文件
	 * @param sheetIndex        sheet的索引
	 *  
	 * 1、找到某行，某行中可能有很多列需要插入
	 *   例如：第一行，第三列插入aaa，第5列插入bbb
	 *       第二行，第二列插入ddd，第5列插入fff
	 */

	public static Map<String, Map<Integer, String>> resulToWriteMap = new HashMap<String, Map<Integer, String>>();

	public static List<CellData> resulToWriteList = new ArrayList<CellData>();

	/**
	 * pojo对象，通过List的方式
	 * @param caseFilePath
	 * @param targetFilePath
	 * @param sheetIndex
	 */
	public static void batchWrite(String caseFilePath, String targetFilePath, int sheetIndex) {

		InputStream is = null;
		OutputStream os = null;

		try {
			is = ExcelUtil.class.getResourceAsStream(caseFilePath);
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			int lastRowNum = sheet.getLastRowNum();
			for (CellData cellData : resulToWriteList) {
				for (int i = 0; i <= lastRowNum; i++) {
					//得到当前遍历的这一行
					Row row = sheet.getRow(i);
					//得到第一列
					Cell cell = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					//得到第一列的值
					String firstCellValue = cell.getStringCellValue();
					//比较当前行的第一列的值是否等于传入的caseId
					if (firstCellValue.equals(cellData.getCaseId())) {
						Cell cellToBeWrite = row.getCell(cellData.getCellNum() - 1,
								MissingCellPolicy.CREATE_NULL_AS_BLANK);
						cellToBeWrite.setCellType(CellType.STRING);
						cellToBeWrite.setCellValue(cellData.getResultStr());
						break;
					}
				}
			}
			//把一个本地的excel文件的数据加载到java对象中来，这个对象具有之前我们加载文档的所有信息以及我们自己修改的信息
			os = new FileOutputStream(new File(targetFilePath));
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void writeResultToExcel(String caseId, int celltarget, String resultStr) {
		resulToWriteList.add(new CellData(caseId, celltarget, resultStr));
	}

	//	public static void main(String[] args) {
	//		read("/rest_info1.xlsx", 1, ApiParams.class);
	//		
	//	}
}
