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
			@RequestParam(value=Constants.RestParam.P_AGE, required=true) int age,
			@RequestParam(value=Constants.RestParam.P_NATIONALITY, required=true) String nationality,
			@RequestParam(value=Constants.RestParam.P_TOTAL_SCORE, required=true) Integer totalScore,
			
			//SkillBean parameters
			@RequestParam(value="skillMoves | accepted values in [1,5]", required=false) Integer skillMoves,
			@RequestParam(value="weakFoot | accepted values in [1,5]", required=false) Integer weakFoot,
			@RequestParam(value="attWorkRate", required=false) WorkRate attWorkRate,
			@RequestParam(value="skillMoves", required=false) FootPreference preferredFoot,
			
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
			@RequestParam(value="accelaggressioneration | accepted values in [1,100]", required=false) Integer aggression,
			@RequestParam(value="test", required=false) WorkRate test
			) { 
		

		
		SkillBean skills = new SkillBean().setSkillMoves(null)
										  .setWeakFoot(null)
										  .setAttWorkRate(null)
										  .setWeakFoot(null);
		
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
													  .setShortPassing(freeKickAccuracy)
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
		service.addPlayer(player);
		return null;
	}
}
