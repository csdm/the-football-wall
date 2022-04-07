package com.claudiodimauro.thefootballwall.api.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.claudiodimauro.thefootballwall.api.exporter.PlayerExcelExporter;
import com.claudiodimauro.thefootballwall.api.exporter.PlayerPDFExporter;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.claudiodimauro.thefootballwall.api.repositories.PlayerRepository;

@Service("ExporterServiceImpl")
public class ExporterServiceImpl implements ExporterService {
	private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);
	
	@Autowired
	PlayerRepository repository;
	
	/*****************************************************************
	 * SERVICE METHOD
	 * METHOD: loadExcelFile()
	 * DESCRIPTION: it loads the Excel file as inputStream 
	 *****************************************************************/	
	public ByteArrayInputStream loadExcelFile(boolean isNewExcel) {
		List<Player> listPlayers = repository.findAll(Sort.by("totalScore").descending());
		PlayerExcelExporter exporter = new PlayerExcelExporter(isNewExcel, listPlayers);

		ByteArrayInputStream inputStream = null;

		try {
			inputStream = exporter.export();
		} catch(IOException ex) {
			logger.error(ex.getMessage());			
		}

		return inputStream;
	}
	
	/*****************************************************************
	 * SERVICE METHOD
	 * METHOD: loadPDFFile()
	 * DESCRIPTION: it loads the PDF file as inputStream 
	 *****************************************************************/	
	public ByteArrayInputStream loadPDFFile() {
		List<Player> listPlayers = repository.findAll(Sort.by("totalScore").descending());
		PlayerPDFExporter exporter = new PlayerPDFExporter(listPlayers);

		ByteArrayInputStream inputStream = null;

		try {
			inputStream = exporter.export();
		} catch(Exception ex) {
			logger.error(ex.getMessage());			
		}

		return inputStream;
	}
}
