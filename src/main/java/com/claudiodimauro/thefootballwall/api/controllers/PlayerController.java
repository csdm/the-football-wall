package com.claudiodimauro.thefootballwall.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.claudiodimauro.thefootballwall.api.beans.PlayerResponseBean;
import com.claudiodimauro.thefootballwall.api.services.PlayerService;
import com.claudiodimauro.thefootballwall.utils.enums.ElaborationStatus;
import com.claudiodimauro.thefootballwall.utils.responses.GenericResponse;

@RestController
@RequestMapping(value = "/api")
public class PlayerController {
	@Autowired
	private PlayerService service;
	
	@GetMapping(path = "/getPlayers/all")
	//@ApiResponses -> swagger
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = GenericResponse.class),
//							@ApiResponse(code = 404, message = "Not Found", response = GenericResponse.class),
//							@ApiResponse(code = 500, message = "Internal Server Error", response = GenericResponse.class) 
//	})
	public @ResponseBody ResponseEntity<?> getAllPlayers() {
		
		GenericResponse<PlayerResponseBean> output = null;
		HttpStatus httpStatus = HttpStatus.OK;
		
		try {
			PlayerResponseBean response = service.findAllPlayers();
			output = new GenericResponse<PlayerResponseBean>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<PlayerResponseBean>(null, "ERRORE:" + ex.getMessage(), ElaborationStatus.ERRORE);
		}
		
		return ResponseEntity.status(httpStatus).body(output);
	}
}
