package com.claudiodimauro.thefootballwall.api.exporter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

import com.claudiodimauro.thefootballwall.api.controllers.DataExporterController;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class PlayerPDFExporter {
	private static final Logger logger = LoggerFactory.getLogger(DataExporterController.class);

	private List<Player> listPlayers;
	
	private OutputStream PDFBuilder(HttpServletResponse response) throws IOException{
		OutputStream outputStream = null;
		Document doc = new Document(PageSize.A4);
		
		if(response != null) {
			outputStream = response.getOutputStream();
		} else {
			outputStream = new ByteArrayOutputStream();
		}
		
		try {
			PdfWriter.getInstance(doc, outputStream);
			
			HeaderFooter header = new HeaderFooter(new Phrase("The Football Wall"), false);
			header.setBorder(Rectangle.NO_BORDER); 
			header.setBorder(Rectangle.BOTTOM);
			HeaderFooter footer = new HeaderFooter(new Phrase("https://thefootballwall.herokuapp.com   |   "), true);
			footer.setAlignment(Element.ALIGN_RIGHT);
			footer.setBorder(Rectangle.NO_BORDER); 
			footer.setBorder(Rectangle.TOP);
			doc.setHeader(header);
			doc.setFooter(footer);			
			
			doc.open();

			Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			fontTitle.setSize(20);

			Paragraph titleParagraph = new Paragraph("Players", fontTitle);
			titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
			titleParagraph.setSpacingAfter(10f);
			doc.add(titleParagraph);

			//Create a table setting up its column number
			PdfPTable playersTable = new PdfPTable(5);

			playersTable.setWidthPercentage(100f);
			playersTable.setWidths(new int[] {3, 3, 3, 2, 3});
			playersTable.setSpacingBefore(5);

			PdfPCell headerCell = new PdfPCell();
			headerCell.setBackgroundColor(CMYKColor.LIGHT_GRAY);
			headerCell.setPadding(6);			
			Font headerCellFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			headerCellFont.setColor(CMYKColor.BLACK);

			headerCell.setPhrase(new Phrase("Total score", headerCellFont));
			playersTable.addCell(headerCell);
			headerCell.setPhrase(new Phrase("Surname", headerCellFont));
			playersTable.addCell(headerCell);
			headerCell.setPhrase(new Phrase("Name", headerCellFont));
			playersTable.addCell(headerCell);
			headerCell.setPhrase(new Phrase("Age", headerCellFont));
			playersTable.addCell(headerCell);
			headerCell.setPhrase(new Phrase("Nationality", headerCellFont));
			playersTable.addCell(headerCell);

			PdfPCell dataCell = new PdfPCell();
			dataCell.setBackgroundColor(CMYKColor.WHITE);
			dataCell.setPadding(4);
			Font dataCellFont = FontFactory.getFont(FontFactory.HELVETICA);
			dataCellFont.setColor(CMYKColor.BLACK);


			for(Player p : listPlayers) {
				dataCell.setPhrase(new Phrase(String.valueOf(p.getTotalScore()), dataCellFont));
				playersTable.addCell(dataCell);
				dataCell.setPhrase(new Phrase(String.valueOf(p.getSurname()), dataCellFont));
				playersTable.addCell(dataCell);
				dataCell.setPhrase(new Phrase(String.valueOf(p.getName()), dataCellFont));
				playersTable.addCell(dataCell);
				dataCell.setPhrase(new Phrase(String.valueOf(p.getAge()), dataCellFont));
				playersTable.addCell(dataCell);
				dataCell.setPhrase(new Phrase(String.valueOf(p.getNationality()), dataCellFont));
				playersTable.addCell(dataCell);
			}

			doc.add(playersTable);
		} catch(DocumentException ex) {		
			logger.error(ex.getMessage());
		}  finally {
			doc.close();
		}
		
		return outputStream;		
	}
	
	
	public void export(HttpServletResponse response) {
		try {
			PDFBuilder(response);
		} catch(IOException ex) {
			logger.error(ex.getMessage());
		}
	}
	
	public ByteArrayInputStream export() {
		ByteArrayOutputStream outputStream = null; 
		
		try{
			outputStream = (ByteArrayOutputStream)PDFBuilder(null);
		} catch(IOException ex) {
			logger.error(ex.getMessage());
		}
		
		return new ByteArrayInputStream(outputStream.toByteArray());
	} 
}