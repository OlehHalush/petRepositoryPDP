package excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadingExcel {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("src/main/java/excel_example.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        fis.close();

        Map<String, Map<String, String>> content = getExcelContentMap(workbook, "Users");
        System.out.println(content);

        List<String> columnValues = getColumnValues(workbook, "Users", "FirstName");
        System.out.println(columnValues);
    }

    public static Map<String, Map<String, String>> getExcelContentMap(Workbook workbook, String tabName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = workbook.getSheet(tabName);
        Row titlesRow = sheet.getRow(sheet.getTopRow());
        List<String> titles = new ArrayList<>();
        titlesRow.forEach(cell -> titles.add(formatter.formatCellValue(cell)));
        String identifierColumnName = "Id";

        Map<String, Map<String, String>> excelContentMap = new HashMap<>();
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            Map<String, String> rowColumnValueMap = new HashMap<>();
            String identifier = null;
            for (int cell = 0; cell < row.getLastCellNum(); cell++) {
                Cell cellValue = row.getCell(cell);
                if (titles.get(cell).equals(identifierColumnName)) {
                    identifier = formatter.formatCellValue(cellValue);
                }
                rowColumnValueMap.put(titles.get(cell), formatter.formatCellValue(cellValue));
            }
            excelContentMap.put(identifier, rowColumnValueMap);
        }
        workbook.close();
        return excelContentMap;
    }

    public static List<String> getColumnValues(Workbook workbook, String tabName, String columnName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = workbook.getSheet(tabName);
        Row titlesRow = sheet.getRow(sheet.getTopRow());
        List<String> titles = new ArrayList<>();
        titlesRow.forEach(cell -> titles.add(formatter.formatCellValue(cell)));
        int cellIndex = titles.indexOf(columnName);

        List<String> columnValues = new ArrayList<>();
        for (int r = 1; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            for (int cell = 0; cell < row.getLastCellNum(); cell++) {

                if (cell == cellIndex) {
                    columnValues.add(formatter.formatCellValue(row.getCell(cell)));
                }
            }
        }
        workbook.close();
        return columnValues;
    }
}
