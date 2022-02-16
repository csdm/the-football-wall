package com.claudiodimauro.thefootballwall.api.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.claudiodimauro.thefootballwall.api.beans.SkillBean;
import com.claudiodimauro.thefootballwall.api.beans.StatisticBean;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "players")
@Getter
@Setter
@NoArgsConstructor
@Schema(accessMode = AccessMode.READ_WRITE,
		description = "It represents the data model of a football player. "
					+ "This model specifies the personal data and the data relating to the skills "
					+ "and statistics of each player.")
public class Player {

	@Id
	@Schema(description = "Unique identifier of a football player")
	private ObjectId id;
	@Schema(description = "Name of a football player")
	private String name;
	@Schema(description = "Surname of a football player")
	private String surname;
	@Schema(description = "The age of a football player")
	private int age;
	@Schema(description = "The nationality of a football player")
	private String nationality;
	@Schema(description = "Total score: a value between 1 and 100", example = "95")
	private int totalScore; //min 1 max 100
	@Schema(description = "List of skills of a player")
	private SkillBean skills;
	@Schema(description = "List of statistics about a player")
	private StatisticBean statistics;
		
	public Player(String name, String surname, int age, String nationality, int totalScore, SkillBean skills, StatisticBean statistics) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.nationality = nationality;
		this.totalScore = totalScore;
		this.skills = skills;
		this.statistics = statistics;
	}
}
