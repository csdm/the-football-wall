package com.claudiodimauro.thefootballwall.api.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.claudiodimauro.thefootballwall.api.beans.PlayerResponseBean;
import com.claudiodimauro.thefootballwall.api.beans.PlayerResponseOnInsertBean;
import com.claudiodimauro.thefootballwall.api.beans.SkillBean;
import com.claudiodimauro.thefootballwall.api.beans.StatisticBean;
import com.claudiodimauro.thefootballwall.api.models.Player;
import com.claudiodimauro.thefootballwall.api.services.PlayerService;
import com.claudiodimauro.thefootballwall.utils.Constants;
import com.claudiodimauro.thefootballwall.utils.enums.ElaborationStatus;
import com.claudiodimauro.thefootballwall.utils.enums.FootPreference;
import com.claudiodimauro.thefootballwall.utils.enums.WorkRate;
import com.claudiodimauro.thefootballwall.utils.responses.GenericResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/api")
public class PlayerController {
	private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);
	
	@Autowired
	private PlayerService service;

	@CrossOrigin(origins = "http://localhost:3000")
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
		logger.debug("REST CALL - method: {} - path: {}", HttpMethod.GET, "api/getPlayer/all");

		GenericResponse<PlayerResponseBean> output = null;
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			PlayerResponseBean response = service.findAllPlayers();
			output = new GenericResponse<PlayerResponseBean>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setto la data della richiesta
			output.setPath("api/getPlayer/all");
			output.setMethod(HttpMethod.GET.toString());
		} catch (Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<PlayerResponseBean>(null, "ERRORE:" + ex.getMessage(), ElaborationStatus.ERRORE);
			output.setHttpStatus(httpStatus);
			output.setTimestamp(new Date()); //setto la data della richiesta
			output.setPath("api/getPlayer/all");
			output.setMethod(HttpMethod.GET.toString());
		}

		return ResponseEntity.status(httpStatus).body(output);
	}

	@GetMapping(path = "/getPlayer")
	@Operation(method = "GET", 
			   summary = "Get data about the specified football player and his skills", 
			   description = "It returns the data about a football player and their skills")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") 
	})
	public @ResponseBody ResponseEntity<?> getPlayer(
			@RequestParam(value=Constants.RestParam.P_NAME, required=false) String name,
			@RequestParam(value=Constants.RestParam.P_SURNAME, required=true) String surname,
			@RequestParam(value=Constants.RestParam.P_ID, required=false) String id
			) {
		logger.debug("REST CALL - method: {} - path: {} - searchKey: {}, {}, {}", HttpMethod.GET, "api/getPlayer", name, surname, id);
		
		GenericResponse<PlayerResponseBean> output = null;
		HttpStatus httpStatus = HttpStatus.OK;


		try {
			PlayerResponseBean response = service.findPlayerBySurname(surname);
			output = new GenericResponse<PlayerResponseBean>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setto la data della richiesta
			output.setPath("api/getPlayer");
			output.setMethod(HttpMethod.GET.toString());
		} catch (Exception ex) {
			logger.error("ERROR {}", HttpStatus.INTERNAL_SERVER_ERROR.toString());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<PlayerResponseBean>(null, "ERROR: " + ex.getMessage(), ElaborationStatus.ERRORE);
			output.setHttpStatus(httpStatus);
			output.setTimestamp(new Date()); //setto la data della richiesta
			output.setPath("api/getPlayer");
			output.setMethod(HttpMethod.GET.toString());
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
			@RequestParam(value=Constants.RestParam.P_AGE, required=true) int age,
			@RequestParam(value=Constants.RestParam.P_NATIONALITY, required=true) String nationality,
			@RequestParam(value=Constants.RestParam.P_TOTAL_SCORE, required=true) Integer totalScore,
			
			//SkillBean parameters
			@RequestParam(value="skillMoves | accepted values in [1,5]", required=false) Integer skillMoves,
			@RequestParam(value="weakFoot | accepted values in [1,5]", required=false) Integer weakFoot,
			@RequestParam(value="attWorkRate", required=false) WorkRate attWorkRate,
			@RequestParam(value="defWorkRate", required=false) WorkRate defWorkRate,
			@RequestParam(value="preferredFoot", required=false) FootPreference preferredFoot,
			
			//StatisticBean parameters
			@RequestParam(value="acceleration | accepted values in [1,100]", required=false) Integer acceleration,
			@RequestParam(value="sprintSpeed | accepted values in [1,100]", required=false) Integer sprintSpeed,
			@RequestParam(value="positioning | accepted values in [1,100]", required=false) Integer positioning,
			@RequestParam(value="finishing | accepted values in [1,100]", required=false) Integer finishing,
			@RequestParam(value="shotPower | accepted values in [1,100]", required=false) Integer shotPower,
			@RequestParam(value="longShots | accepted values in [1,100]", required=false) Integer longShots,
			@RequestParam(value="volleys | accepted values in [1,100]", required=false) Integer volleys,
			@RequestParam(value="penalties | accepted values in [1,100]", required=false) Integer penalties,
			@RequestParam(value="vision | accepted values in [1,100]", required=false) Integer vision,
			@RequestParam(value="crossing | accepted values in [1,100]", required=false) Integer crossing,
			@RequestParam(value="freeKickAccuracy | accepted values in [1,100]", required=false) Integer freeKickAccuracy,
			@RequestParam(value="shortPassing | accepted values in [1,100]", required=false) Integer shortPassing,
			@RequestParam(value="longPassing | accepted values in [1,100]", required=false) Integer longPassing,
			@RequestParam(value="curve | accepted values in [1,100]", required=false) Integer curve,
			@RequestParam(value="agility | accepted values in [1,100]", required=false) Integer agility,
			@RequestParam(value="balance | accepted values in [1,100]", required=false) Integer balance,
			@RequestParam(value="reactions | accepted values in [1,100]", required=false) Integer reactions,
			@RequestParam(value="ballControl | accepted values in [1,100]", required=false) Integer ballControl,
			@RequestParam(value="dribbling | accepted values in [1,100]", required=false) Integer dribbling,
			@RequestParam(value="composure | accepted values in [1,100]", required=false) Integer composure,
			@RequestParam(value="interceptions | accepted values in [1,100]", required=false) Integer interceptions,
			@RequestParam(value="headingAccuracy | accepted values in [1,100]", required=false) Integer headingAccuracy,
			@RequestParam(value="marking | accepted values in [1,100]", required=false) Integer marking,
			@RequestParam(value="standingTackle | accepted values in [1,100]", required=false) Integer standingTackle,
			@RequestParam(value="slidingTackle | accepted values in [1,100]", required=false) Integer slidingTackle,
			@RequestParam(value="jumping | accepted values in [1,100]", required=false) Integer jumping,
			@RequestParam(value="stamina | accepted values in [1,100]", required=false) Integer stamina,
			@RequestParam(value="accestrengthleration | accepted values in [1,100]", required=false) Integer strength,
			@RequestParam(value="accelaggressioneration | accepted values in [1,100]", required=false) Integer aggression
			) { 
		logger.debug("REST CALL - method: {} - path: {} - request for: {}, {}", HttpMethod.POST, "api/addPlayer", surname, name);

		
		SkillBean skills = new SkillBean().setSkillMoves(skillMoves)
										  .setWeakFoot(weakFoot)
										  .setAttWorkRate(attWorkRate)
										  .setDefWorkRate(defWorkRate)
										  .setPreferredFoot(preferredFoot);
		
		StatisticBean statistics = new StatisticBean().setAcceleration(acceleration)
													  .setSprintSpeed(sprintSpeed)
													  .setPositioning(positioning)
													  .setFinishing(finishing)
													  .setShotPower(shotPower)
													  .setLongShots(longShots)
													  .setVolleys(volleys)
													  .setPenalties(penalties)
													  .setVision(vision)
													  .setCrossing(crossing)
													  .setFreeKickAccuracy(freeKickAccuracy)
													  .setShortPassing(shortPassing)
													  .setLongPassing(longPassing)
													  .setCurve(curve)
													  .setAgility(agility)
													  .setBalance(balance)
													  .setReactions(reactions)
													  .setBallControl(ballControl)
													  .setDribbling(dribbling)
													  .setComposure(composure)
													  .setInterceptions(interceptions)
													  .setHeadingAccuracy(headingAccuracy)
													  .setMarking(marking)
													  .setStandingTackle(standingTackle)
													  .setSlidingTackle(slidingTackle)
													  .setJumping(jumping)
													  .setStamina(stamina)
													  .setStrength(strength)
													  .setAggression(aggression);
		
		Player player = new Player(name, surname, age, nationality, totalScore, skills, statistics);
		
		GenericResponse<PlayerResponseOnInsertBean> output = null;
		HttpStatus httpStatus = HttpStatus.OK;
						
		try {
			PlayerResponseOnInsertBean response = service.addPlayer(player);
			output = new GenericResponse<PlayerResponseOnInsertBean>(response, httpStatus.toString(), ElaborationStatus.ELABORATO);
			output.setHttpStatus(HttpStatus.OK);
			output.setTimestamp(new Date()); //setto la data della richiesta
			output.setPath("api/addPlayer");
			output.setMethod(HttpMethod.POST.toString());
		} catch(Exception ex) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			output = new GenericResponse<PlayerResponseOnInsertBean>(null, "ERROR: " + ex.getMessage(), ElaborationStatus.ERRORE);
			output.setHttpStatus(httpStatus);
			output.setTimestamp(new Date()); //setto la data della richiesta
			output.setPath("api/addPlayer");
			output.setMethod(HttpMethod.POST.toString());
		}
		
		return ResponseEntity.status(httpStatus).body(output);
	}
}
