package com.claudiodimauro.thefootballwall.api.services;

import java.io.ByteArrayInputStream;

public interface ExporterService {
	public ByteArrayInputStream loadExcelFile(boolean isNewExcel);
	public ByteArrayInputStream loadPDFFile();
}
