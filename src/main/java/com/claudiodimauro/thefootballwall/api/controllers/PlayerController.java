package com.claudiodimauro.thefootballwall.api.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.claudiodimauro.thefootballwall.api.beans.responses.BasePlayerResponse;
import com.claudiodimauro.thefootballwall.api.beans.responses.ListPlayerResponse;
import com.claudiodimauro.thefootballwall.api.beans.responses.PlayerInsertResponse;
import com.claudiodimauro.thefootballwall.api.beans.responses.PlayerPaginationResponse;
import com.claudiodimauro.thefootballwall.api.beans.responses.SinglePlayerResponse;
import com.claudiodimauro.thefootballwall.api.beans.responses.generic.GenericResponse;
import com.claudiodimauro.thefootballwall.api.dto.RequestPostDTO;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.claudiodimauro.thefootballwall.api.services.PlayerService;
import com.claudiodimauro.thefootballwall.api.services.PlayerServiceImpl;
import com.claudiodimauro.thefootballwall.utils.Constants;
import com.claudiodimauro.thefootballwall.utils.enums.ElaborationStatus;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/api")
public class PlayerController {
	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

	@Autowired
	@Qualifier("PlayerServiceImpl") //specifico l'implementazione che voglio per questo service
	private PlayerService service;


	/*****************************************************************
	 * REST API
	 * METHOD: get
	 * PATH: /getplayer/all
	 * DESCRIPTION: It returns the complete list of Football players and their skills
	 *****************************************************************/
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(path = "/getplayer/all")
	@Operation(method = "GET", 
	summary = "Get a list of all football players and their skills", 
	description = "It returns the complete list of Football players and their skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
	public @ResponseBody ResponseEntity<?> getAllPlayers() {
		logger.debug("REST CALL - method: {} - path: {}", HttpMethod.GET, "api/getplayer/all");
		logger.info("REST CALL - method: {} - path: {}", HttpMethod.GET, "api/getplayer/all");

		GenericResponse<?> output = null;
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			BasePlayerResponse<?> response =  service.findAllPlayers();
			output = new GenericResponse<>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getplayer/all");
			output.setMethod(HttpMethod.GET.toString());
		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<>(null, "ERROR: " + ex.getMessage(), ElaborationStatus.ERRORE);
			output.setHttpStatus(httpStatus);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getPlayer/all");
			output.setMethod(HttpMethod.GET.toString());
		}

		return ResponseEntity.status(httpStatus).body(output);
	}



	/*****************************************************************
	 * REST API
	 * METHOD: get
	 * PATH: /getplayer/all/paginated
	 * DESCRIPTION: It returns  list of Football players and their skills
	 *****************************************************************/
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/getplayer/all/paginated")
	@Operation(method = "GET", 
	summary = "Get a paginated list of all football players and their skills", 
	description = "It returns a complete paginated list of Football players and their skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
	public @ResponseBody ResponseEntity<?> getPaginatedListOfPlayers(
			@RequestParam(required = false, defaultValue = "0") int pageNum , 
			@RequestParam(required = false, defaultValue = "10") int pageSize
			) {
		logger.debug("REST CALL - method: {} - path: {}", HttpMethod.GET, "api/getplayer/all/paginated");
		logger.info("REST CALL - method: {} - path: {}", HttpMethod.GET, "api/getplayer/all/paginated");

		GenericResponse<?> output = null;
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			BasePlayerResponse<?> response = service.findPaginatedPlayers(pageNum, pageSize);
			output = new GenericResponse<>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getplayer/all/paginated");
			output.setMethod(HttpMethod.GET.toString());
		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<PlayerPaginationResponse>(null, "ERRORE:" + ex.getMessage(), ElaborationStatus.ERRORE);
			output.setHttpStatus(httpStatus);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getplayer/all/paginated");
			output.setMethod(HttpMethod.GET.toString());
		}

		return ResponseEntity.status(httpStatus).body(output);
	}

	
	/*****************************************************************
	 * REST API
	 * METHOD: get
	 * PATH: /getplayer/topten
	 * DESCRIPTION: It returns the Top Ten list of Football players and their skills
	 *****************************************************************/
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(path = "/getplayer/topten")
	@Operation(method = "GET", 
	summary = "Get a list of all football players and their skills", 
	description = "It returns the Top Ten list of Football players and their skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
	public @ResponseBody ResponseEntity<?> getTopTen() {
		logger.debug("REST CALL - method: {} - path: {}", HttpMethod.GET, "api/getplayer/topten");
		logger.info("REST CALL - method: {} - path: {}", HttpMethod.GET, "api/getplayer/topten");

		GenericResponse<?> output = null;
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			BasePlayerResponse<?> response = service.findTopTenPlayers();
			output = new GenericResponse<>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getplayer/topten");
			output.setMethod(HttpMethod.GET.toString());
		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<>(null, "ERROR: " + ex.getMessage(), ElaborationStatus.ERRORE);
			output.setHttpStatus(httpStatus);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getplayer/topten");
			output.setMethod(HttpMethod.GET.toString());
		}

		return ResponseEntity.status(httpStatus).body(output);
	}

	
	/*****************************************************************
	 * REST API
	 * METHOD: get
	 * PATH: /getPlayer
	 * DESCRIPTION: It returns the data about a specified football player and his 
	 * 				skills. The player could be retrieved by name or surname or by 
	 * 				the combination name-surname.
	 * 				If with the passed key will retrieved more than one player,
	 * 				will be returned a list of data.
	 *****************************************************************/
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(path = "/getplayer")
	@Operation(method = "GET", 
	summary = "Get data about the specified football player and his skills", 
	description = "It returns the data about a football player and their skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
	public @ResponseBody ResponseEntity<?> getPlayer(
			@RequestParam(value=Constants.RestParam.P_SURNAME, required=false) String surname,
			@RequestParam(value=Constants.RestParam.P_NAME, required=false) String name
			) {
		
		/**
		 * Make the parameters case insensitive
		 */
		String stCapitalSurname=null;
		String stCapitalName=null;
		if(surname != null)
			stCapitalSurname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
		if(name != null)
			stCapitalName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		
		logger.debug("REST CALL - method: {} - path: {} - searchKey: {}, {}", HttpMethod.GET, "api/getplayer", stCapitalName, stCapitalSurname);
		logger.info("REST CALL - method: {} - path: {} - searchKey: {}, {}", HttpMethod.GET, "api/getplayer", stCapitalName, stCapitalSurname);

		GenericResponse<?> output = null;
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			BasePlayerResponse<?> response =  service.findPlayer(stCapitalSurname, stCapitalName);
			output = new GenericResponse<>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getplayer");
			output.setMethod(HttpMethod.GET.toString());
		} catch (Exception ex) {
			logger.error("ERROR {}", HttpStatus.INTERNAL_SERVER_ERROR.toString());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<>(null, "ERROR: " + ex.getMessage(), ElaborationStatus.ERRORE);
			output.setHttpStatus(httpStatus);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getplayer");
			output.setMethod(HttpMethod.GET.toString());
		}

		return ResponseEntity.status(httpStatus).body(output);
	}

	
	/*****************************************************************
	 * REST API
	 * METHOD: get
	 * PATH: /getplayer/{playerId}
	 * DESCRIPTION: It returns the data about a football player and his skills
	 *****************************************************************/
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping(path = "/getplayer/{playerId}")
	@Operation(method = "GET", 
	summary = "Get data about the specified football player and his skills", 
	description = "It returns the data about a football player and their skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
	public @ResponseBody ResponseEntity<?> getPlayer(
			@PathVariable("playerId") String playerId
			) {
		logger.debug("REST CALL - method: {} - path: {} - searchKey: {}", HttpMethod.GET, "api/getplayer", playerId);

		GenericResponse<?> output = null;
		HttpStatus httpStatus = HttpStatus.OK;


		try {
			BasePlayerResponse<?> response = service.findPlayerByPlayerId(playerId);
			output = new GenericResponse<>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getplayer");
			output.setMethod(HttpMethod.GET.toString());
		} catch (Exception ex) {
			logger.error("ERROR {}", HttpStatus.INTERNAL_SERVER_ERROR.toString());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<>(null, "ERROR: " + ex.getMessage(), ElaborationStatus.ERRORE);
			output.setHttpStatus(httpStatus);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/getplayer");
			output.setMethod(HttpMethod.GET.toString());
		}

		return ResponseEntity.status(httpStatus).body(output);
	}

	
	/*****************************************************************
	 * REST API
	 * METHOD: post
	 * PATH: /addplayer
	 * DESCRIPTION: It allows to insert a new player and his skills
	 *****************************************************************/
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping(path = "/addplayer")
	@Operation(method = "POST", 
	summary = "Add new player", 
	description = "It allows to insert a new player and his skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
	public @ResponseBody ResponseEntity<?> addPlayer(@Valid @RequestBody RequestPostDTO request) { 
		logger.debug("REST CALL - method: {} - path: {} - request for: {}, {}", HttpMethod.POST, "api/addplayer", request.getSurname(), request.getName());
		logger.info("REST CALL - method: {} - path: {} - request for: {}, {}", HttpMethod.POST, "api/addplayer", request.getSurname(), request.getName());

		Player player = new Player(request.getPlayerId(), request.getName(), request.getSurname(), 
				request.getAge(), request.getNationality(), request.getRole(), 
				request.getTotalScore(), request.getSkills(), request.getStatistics());

		GenericResponse<?> output = null;
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			BasePlayerResponse<?> response = service.addPlayer(player);
			output = new GenericResponse<>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/addplayer");
			output.setMethod(HttpMethod.POST.toString());
		} catch(Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<>(null, "ERROR: " + ex.getMessage(), ElaborationStatus.ERRORE);
			output.setHttpStatus(httpStatus);
			output.setTimestamp(new Date()); //setting up the request date
			output.setPath("api/addplayer");
			output.setMethod(HttpMethod.POST.toString());
		}

		return ResponseEntity.status(httpStatus).body(output);
	}
}