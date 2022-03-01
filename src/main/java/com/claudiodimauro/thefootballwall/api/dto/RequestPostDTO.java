package com.claudiodimauro.thefootballwall.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.claudiodimauro.thefootballwall.api.beans.SkillBean;
import com.claudiodimauro.thefootballwall.api.beans.StatisticBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPostDTO {
	@NotNull
	private String playerId;
	@NotEmpty
	private String name;
	@NotNull
	private String surname;
	@NotNull
	private int age;
	@NotNull
	private String nationality;
	@NotNull
	@Min(1)
	@Max(100)
	private int totalScore; //min 1 max 100
	private SkillBean skills;
	private StatisticBean statistics;	
}
