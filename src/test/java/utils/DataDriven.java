package utils;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataDriven {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException {
		
		String path = System.getProperty("user.dir") + "/src/test/resources/testData.xlsx";
		ExcelUtility excel = new ExcelUtility(path);
		
		int rownum = excel.getRowCount("test");
		int colcount = excel.getCellCount("test", 1);
		
		String apidata[][] = new String[rownum][colcount];
		
		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				apidata[i-1][j] = excel.getCellData("test", i, j);
			}
		}
		
		return apidata;
	}
	
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException {
		
		String path = System.getProperty("user.dir") + "/src/test/resources/testData.xlsx";
		ExcelUtility excel = new ExcelUtility(path);
		
		int rownum = excel.getRowCount("test");
				
		String apidata[] = new String[rownum];
		
		for (int i = 1; i <= rownum; i++) {
			apidata[i-1] = excel.getCellData("test", i, 1);
		}
		
		return apidata;
	}
	
}
