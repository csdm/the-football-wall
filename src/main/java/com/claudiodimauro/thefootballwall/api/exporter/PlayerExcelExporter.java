package com.claudiodimauro.thefootballwall.api.exporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.claudiodimauro.thefootballwall.api.models.Player;

public class PlayerExcelExporter {
	private Workbook workbook;
	private Sheet sheet;
	private CellStyle headerRowStyle;
	private CellStyle dataRowStyle;
	private Font headerRowFont;
	private Font dataRowFont;
	private List<Player> listPlayers;
	
	 //isNewExcel -> true if the excel version is 2007 or higher - false if the excel version is 2003
	public PlayerExcelExporter(boolean isNewExcel, List<Player> listPlayers) {
		
		workbook = isNewExcel 
				? new XSSFWorkbook() 
				: new HSSFWorkbook();

		sheet = workbook.createSheet("Players");
		headerRowFont = workbook.createFont();
		dataRowFont = workbook.createFont();
		headerRowStyle = workbook.createCellStyle();
		dataRowStyle = workbook.createCellStyle();
		
		this.listPlayers = listPlayers;
	}

	 private void createCell(Row row, int columnCount, Object value, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        
	        Cell cell = row.createCell(columnCount);
	        
	        if (value instanceof Integer) {
	            cell.setCellValue((Integer) value);
	        } else if (value instanceof Boolean) {
	            cell.setCellValue((Boolean) value);
	        }else {
	            cell.setCellValue((String) value);
	        }
	        
	        cell.setCellStyle(style);
	    }
	     
	 private void writeHeaderRow() {
		Row row = sheet.createRow(0);

		headerRowFont.setFontHeightInPoints((short) 12);
		headerRowFont.setFontName("Arial");
		headerRowFont.setBold(true);		
		headerRowStyle.setFont(headerRowFont);
		
		createCell(row, 0, "Total score", headerRowStyle);
		createCell(row, 1, "Surname", headerRowStyle);
		createCell(row, 2, "Name", headerRowStyle);
		createCell(row, 3, "Age", headerRowStyle);
		createCell(row, 4, "Nationality", headerRowStyle);
	}
	
	 private void writeDataRows() {
		int rowCount = 1;

		dataRowFont.setFontHeightInPoints((short) 10);
		dataRowStyle.setFont(dataRowFont);
		dataRowStyle.setAlignment(HorizontalAlignment.LEFT);
		
		for(Player p : listPlayers) {
			Row row = sheet.createRow(rowCount++);
			row.setRowStyle(dataRowStyle);
			int cellCount = 0;
			
			createCell(row, cellCount++, p.getTotalScore(), dataRowStyle);
			createCell(row, cellCount++, p.getSurname(), dataRowStyle);
			createCell(row, cellCount++, p.getName(), dataRowStyle);
			createCell(row, cellCount++, p.getAge(), dataRowStyle);
			createCell(row, cellCount++, p.getNationality(), dataRowStyle);
		}
	}
	
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderRow();
		writeDataRows();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();		
	}
	
	public ByteArrayInputStream export() throws IOException {
		writeHeaderRow();
		writeDataRows();
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		workbook.write(outputStream);
		workbook.close();
		return new ByteArrayInputStream(outputStream.toByteArray());
	}
}
