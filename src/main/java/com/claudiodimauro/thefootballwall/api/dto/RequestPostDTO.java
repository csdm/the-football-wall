package com.claudiodimauro.thefootballwall.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.claudiodimauro.thefootballwall.api.beans.SkillBean;
import com.claudiodimauro.thefootballwall.api.beans.StatisticBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPostDTO {
	@NotEmpty
	private String playerId;

	@NotEmpty
	private String name;

	@NotEmpty
	private String surname;

	@Min(value = 17, message = "The minimum age to be a professional player is 17 years old")
	@Max(value = 100, message = "The maximum age to be a professional player is 45 years old")
	private int age;

	@NotEmpty
	private String nationality;

	@NotEmpty
	private String role;

	@Min(value = 1, message = "The minimum number must be greater than or equal to 1")
	@Max(value = 100, message = "The maximum number must be less than or equal to 1")
	private int totalScore;

	private SkillBean skills;

	private StatisticBean statistics;	
}