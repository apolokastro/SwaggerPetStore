package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public FileInputStream fin;
	public FileOutputStream fout;
	public XSSFWorkbook book;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle style;
	String path;
	
	public ExcelUtility(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fin = new FileInputStream(path);
		book = new XSSFWorkbook(fin);
		sheet = book.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		book.close();
		fin.close();
		
		return rowCount;
	}
	
	public int getCellCount(String sheetName, int rowNum) throws IOException {
		fin = new FileInputStream(path);
		book = new XSSFWorkbook(fin);
		sheet = book.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		book.close();
		fin.close();
		
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fin = new FileInputStream(path);
		book = new XSSFWorkbook(fin);
		sheet = book.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		
		try {
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data="";
		}
		
		book.close();
		fin.close();
		
		return data;
	}
	
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
		File excelFile = new File(path);
		
		if (!excelFile.exists()) {
			fout = new FileOutputStream(path);
			book = new XSSFWorkbook();
			book.write(fout);
		}
		
		fin = new FileInputStream(path);
		book = new XSSFWorkbook(fin);
		
		if (book.getSheetIndex(sheet) == -1) {
			book.createSheet(sheetName);
		}
		sheet = book.getSheet(sheetName);
		
		if (sheet.getRow(rownum) == null) {
			sheet.createRow(rownum);
		}
		row = sheet.getRow(rownum);
		
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fout = new FileOutputStream(path);
		book.write(fout);
		book.close();
		fin.close();
		fout.close();
	}
	
	public void coloredGreen(String sheetName, int rownum, int colnum) throws IOException {
		fin = new FileInputStream(path);
		book = new XSSFWorkbook(fin);
		sheet = book.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = book.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		book.write(fout);
		book.close();
		fin.close();
		fout.close();
	}
	
	public void coloredRed(String sheetName, int rownum, int colnum) throws IOException {
		fin = new FileInputStream(path);
		book = new XSSFWorkbook(fin);
		sheet = book.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = book.createCellStyle();
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		book.write(fout);
		book.close();
		fin.close();
		fout.close();
	}
}
