package week5day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readExcel(String fileName) throws IOException {

		// setup path of the workbook
		XSSFWorkbook wb = new XSSFWorkbook("./data/"+fileName+".xlsx");
		XSSFSheet ws = wb.getSheet("Sheet1");

		// get into the workbook and work sheet
		int rowCount = ws.getLastRowNum();
		System.out.println(rowCount);
		short cellCount = ws.getRow(0).getLastCellNum();
		System.out.println(cellCount);

		// declare the String[][]
		String[][] data = new String[rowCount][cellCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < cellCount; j++) {
				String text = ws.getRow(i).getCell(j).getStringCellValue();
				System.out.println(text);
				data[i - 1][j] = text;
			}
		}
		wb.close();
		return data;
	}
}