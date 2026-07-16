package Service.Util.Universal;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class ExcelHandler {

    // 清空 Sheet 中从第二行开始的所有数据
    public static void clearSheetDataExceptHeader(Sheet sheet) {
        int lastRowNum = sheet.getLastRowNum();  // 获取最后一行的行号
        // 从第二行开始删除数据
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                // 清除单元格样式
                for (Cell cell : row) {
                    cell.setCellStyle(null); // Remove the style from each cell
                }
                sheet.removeRow(row);  // 删除该行
            }
        }
    }

    // 将 List<Person> 导出为 Excel 文件
    public static void exportToExcel(List<?> dataList, String fileName) {
        // 1. 读取模板文件
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;

        String templatePath = "src/test/resources/LoanModules/" + fileName + ".xlsx";
        try {
            fis = new FileInputStream(templatePath);
            workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Sheet sheet = workbook.getSheetAt(0);
        clearSheetDataExceptHeader(sheet);

        // 从第二行开始写入数据
        int rowIndex = 1;
        Class<?> clazz = dataList.get(0).getClass();

        // 遍历数据列表，填充每一行
        for (Object object : dataList) {
            Row row = sheet.createRow(rowIndex++);

            // 获取对象的所有字段
            Field[] fields = clazz.getDeclaredFields();

            // 遍历每个字段并填充对应的单元格
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true); // 设置字段可访问
                try {
                    Object value = fields[i].get(object); // 获取字段值
                    Cell cell = row.createCell(i); // 创建单元格
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        // 文件输出位置
        String outPutPath = "src/test/resources/" + fileName + ".xlsx";
        FileHandler.deleteFile(outPutPath);

        // 写入文件
        try (FileOutputStream fileOut = new FileOutputStream(outPutPath)) {
            workbook.write(fileOut);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                workbook.close(); // 关闭工作簿
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {

    }
}
