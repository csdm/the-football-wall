package com.claudiodimauro.thefootballwall.api.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticPageController {
	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@GetMapping("/")
	public String home() {
		logger.debug("Connection established on ROOT page");
		return "index.html";
	}
}
