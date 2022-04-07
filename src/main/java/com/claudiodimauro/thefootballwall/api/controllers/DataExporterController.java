package com.claudiodimauro.thefootballwall.api.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudiodimauro.thefootballwall.api.exporter.PlayerExcelExporter;
import com.claudiodimauro.thefootballwall.api.exporter.PlayerPDFExporter;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.claudiodimauro.thefootballwall.api.services.ExporterService;
import com.claudiodimauro.thefootballwall.api.services.PlayerService;

@RestController
@RequestMapping(value = "/api/export")
public class DataExporterController {
	private static final Logger logger = LoggerFactory.getLogger(DataExporterController.class);

	@Autowired
	@Qualifier("ExporterServiceImpl")
	private ExporterService exporterService;
	
	@Autowired
	@Qualifier("PlayerServiceImpl")
	private PlayerService playerService;

	/*****************************************************************
	 * REST API
	 * METHOD: get
	 * PATH: /api/v0/export/excel
	 * DESCRIPTION: (First time implementation of excel exporter) Return the response with 
	 * 				the attached excel file
	 *****************************************************************/
	@GetMapping("v0/excel")
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		logger.info("REST CALL - method: {} - path: {}", HttpMethod.GET, "/api/export/v0/excel");

		response.setContentType("application/octet-stream");
		//DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=players_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<Player> listPlayers = (List<Player>) playerService.findAllPlayers().getPayload();		

		boolean isNewExcel;
		if(headerValue.endsWith(".xlsx")) {
			isNewExcel = true;
		} else if(headerValue.endsWith(".xls")) {
			isNewExcel = false;
		} else {
			logger.error("Invalid extension provided for an Excel file");
			throw new IllegalArgumentException("The specified extension is not allowed!");
		}

		PlayerExcelExporter exporter = new PlayerExcelExporter(isNewExcel, listPlayers);

		exporter.export(response);

		logger.info("Produced {} : {}", headerKey, headerValue);
	}


	/*****************************************************************
	 * REST API
	 * METHOD: get
	 * PATH: /api/export/excel
	 * DESCRIPTION: Return the response with the attached excel file
	 *****************************************************************/
	@GetMapping("excel")
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	public ResponseEntity<?> exportToExcel() {
		logger.info("REST CALL - method: {} - path: {}", HttpMethod.GET, "/api/export/excel");
		
		HttpStatus httpStatus = HttpStatus.OK;
		DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = HttpHeaders.CONTENT_DISPOSITION;
		String headerValue = "attachment; filename=players_" + currentDateTime + ".xlsx";
		
		boolean isNewExcel;
		if(headerValue.endsWith(".xlsx")) {
			isNewExcel = true;
		} else if(headerValue.endsWith(".xls")) {
			isNewExcel = false;
		} else {
			logger.error("Invalid extension provided for an Excel file");
			throw new IllegalArgumentException("Invalid extension provided for an Excel file");
		}
		
		InputStreamResource file = new InputStreamResource(exporterService.loadExcelFile(isNewExcel));
		
		logger.info("Produced {} : {}", headerKey, headerValue);
		
		return ResponseEntity
				.status(httpStatus)
				.header(headerKey, headerValue)
				.contentType(MediaType.parseMediaType("application/vdn.ms-excel"))
				.body(file);
	}
	
	/*****************************************************************
	 * REST API
	 * METHOD: get
	 * PATH: /api/v0/export/pdf
	 * DESCRIPTION: (First time implementation of pdf exporter) Return the response with 
	 * 				the attached pdf file
	 *****************************************************************/
	@GetMapping("v0/pdf")
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	public void exportToPDF(HttpServletResponse response) {
		logger.info("REST CALL - method: {} - path: {}", HttpMethod.GET, "/api/export/v0/pdf");

		response.setContentType("application/pdf");
		//DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=players_" + currentDateTime + ".pdf";
		
		response.setHeader(headerKey, headerValue);
		
		List<Player> listPlayers = (List<Player>) playerService.findAllPlayers().getPayload();
		
		PlayerPDFExporter exporter = new PlayerPDFExporter(listPlayers);
		exporter.export(response);
	}

	/*****************************************************************
	 * REST API
	 * METHOD: get
	 * PATH: /api/export/pdf
	 * DESCRIPTION: Return the response with the attached pdf file
	 *****************************************************************/
	@GetMapping("pdf")
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	public ResponseEntity<?> exportToPDF() {
		logger.info("REST CALL - method: {} - path: {}", HttpMethod.GET, "/api/export/excel");
		
		HttpStatus httpStatus = HttpStatus.OK;
		DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = HttpHeaders.CONTENT_DISPOSITION;
		String headerValue = "attachment; filename=players_" + currentDateTime + ".pdf";
		
		InputStreamResource file = new InputStreamResource(exporterService.loadPDFFile());
		
		logger.info("Produced {} : {}", headerKey, headerValue);
		
		return ResponseEntity
				.status(httpStatus)
				.header(headerKey, headerValue)
				.contentType(MediaType.parseMediaType("application/pdf"))
				.body(file);
	}


}

































