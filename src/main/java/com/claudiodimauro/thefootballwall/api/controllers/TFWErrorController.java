package com.claudiodimauro.thefootballwall.api.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TFWErrorController implements ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
	
	@RequestMapping("/error") //@GetMapping ?
	public String handleError(HttpServletRequest request) {
				
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if(status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			if(statusCode == HttpStatus.BAD_REQUEST.value()) {
				logger.error("Error 400");
				return "error/400.html";				
			} else if(statusCode == HttpStatus.FORBIDDEN.value()) {
				logger.error("Error 403");
				return "error/403.html";			
			} else if(statusCode == HttpStatus.NOT_FOUND.value()) {
				logger.error("Error 404");
				return "error/404.html";			
			} else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				logger.error("Error 500");
				return "error/500.html";	
			}
		}	
		
		return "error/error.html"; //generic error
	}
}
