package com.claudiodimauro.thefootballwall.api.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.claudiodimauro.thefootballwall.api.beans.PlayerResponseBean;
import com.claudiodimauro.thefootballwall.api.beans.SkillBean;
import com.claudiodimauro.thefootballwall.api.beans.StatisticBean;
import com.claudiodimauro.thefootballwall.api.services.PlayerService;
import com.claudiodimauro.thefootballwall.utils.Constants;
import com.claudiodimauro.thefootballwall.utils.enums.ElaborationStatus;
import com.claudiodimauro.thefootballwall.utils.responses.GenericResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/api")
public class PlayerController {
	@Autowired
	private PlayerService service;

	@GetMapping(path = "/getPlayer/all")
	@Operation(method = "GET", 
			   summary = "Get a list of all football players and their skills", 
			   description = "It returns the complete list of Football players and their skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
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

	@GetMapping(path = "/getPlayer")
	@Operation(method = "GET", 
			   summary = "Get a list of the specified football player and his skills", 
			   description = "It returns the data about a football player and their skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
	public @ResponseBody ResponseEntity<?> getPlayer(
			@RequestParam(value=Constants.RestParam.P_NAME, required=true) String name,
			@RequestParam(value=Constants.RestParam.P_SURNAME, required=false) String surname,
			@RequestParam(value=Constants.RestParam.P_ID, required=false) String id
			) {

		GenericResponse<PlayerResponseBean> output = null;
		HttpStatus httpStatus = HttpStatus.OK;


		try {
			PlayerResponseBean response = service.findPlayerByName(name);
			output = new GenericResponse<PlayerResponseBean>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setto la data della richiesta
			output.setPath("api/getPlayer");
			output.setMethod(HttpMethod.GET.toString());
		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<PlayerResponseBean>(null, "ERRORE:" + ex.getMessage(), ElaborationStatus.ERRORE);
		}

		return ResponseEntity.status(httpStatus).body(output);
	}
	
	@PostMapping(path = "/addPlayer")
	@Operation(method = "POST", 
			   summary = "Add new player", 
			   description = "It allows to insert a new player and his skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
	public @ResponseBody ResponseEntity<?> addPlayer(
			@RequestParam(value=Constants.RestParam.P_NAME, required=true) String name,
			@RequestParam(value=Constants.RestParam.P_SURNAME, required=true) String surname,
			@RequestParam(value=Constants.RestParam.P_TOTAL_SCORE, required=true) int totalScore,
			@RequestParam(value=Constants.RestParam.P_SKILLS, required=true) SkillBean skills,
			@RequestParam(value=Constants.RestParam.P_STATISTICS, required=true) StatisticBean statistics
			) {
		return null;
	}
}
