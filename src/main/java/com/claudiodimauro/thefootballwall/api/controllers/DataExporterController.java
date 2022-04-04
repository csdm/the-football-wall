package com.claudiodimauro.thefootballwall.api.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudiodimauro.thefootballwall.api.exporter.PlayerExcelExporter;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.claudiodimauro.thefootballwall.api.services.PlayerService;

@RestController
@RequestMapping(value = "/api/export")
public class DataExporterController {
	@Autowired
	@Qualifier("PlayerServiceImpl") //specifico l'implementazione che voglio per questo service
	private PlayerService service;
	
	@GetMapping("excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		//DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		DateFormat dateFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=players_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<Player> listPlayers = service.listAll();
		
		boolean isNewExcel;
		if(headerValue.endsWith(".xlsx")) {
			isNewExcel = true;
		} else if(headerValue.endsWith(".xls")) {
			isNewExcel = false;
		} else {
			throw new IllegalArgumentException("The specified extension is not allowed!");
		}
		
		PlayerExcelExporter exporter = new PlayerExcelExporter(isNewExcel, listPlayers);
		
		//PlayerExcelExporter exporter = new PlayerExcelExporter(listPlayers);
		
		exporter.export(response);
	}
}
