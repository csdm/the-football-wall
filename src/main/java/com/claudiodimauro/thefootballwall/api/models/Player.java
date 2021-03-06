package com.claudiodimauro.thefootballwall.api.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.claudiodimauro.thefootballwall.api.beans.SkillBean;
import com.claudiodimauro.thefootballwall.api.beans.StatisticBean;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "players")
@Getter
@Setter
@ToString
@Schema(description = "It represents the data model of a football player. "
					+ "This model specifies the personal data and the data relating to the skills "
					+ "and statistics of each player.")
public class Player {

	@Id
	@Schema(description = "Unique identifier of the stored document")
	private ObjectId id;
	@Schema(description = "Unique identifier of a football player")
	private String playerId;
	@Schema(description = "Name of a football player")
	private String name;
	@Schema(description = "Surname of a football player")
	private String surname;
	@Schema(description = "The age of a football player")
	private int age;
	@Schema(description = "The nationality of a football player")
	private String nationality;
	@Schema(description = "The role of a football player")
	private String role;
	@Schema(description = "Total score: a value between 1 and 100", example = "95")
	private Integer totalScore; //min 1 max 100
	@Schema(description = "List of skills of a player")
	private SkillBean skills;
	@Schema(description = "List of statistics about a player")
	private StatisticBean statistics;
		
	public Player(String playerId, String name, String surname, int age, String nationality, String role, int totalScore, SkillBean skills, StatisticBean statistics) {
		super();
		this.playerId = playerId;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.nationality = nationality;
		this.role = role;
		this.totalScore = totalScore;
		this.skills = skills;
		this.statistics = statistics;
	}	
}