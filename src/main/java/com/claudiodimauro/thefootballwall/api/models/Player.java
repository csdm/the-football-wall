package com.claudiodimauro.thefootballwall.api.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.claudiodimauro.thefootballwall.api.beans.SkillBean;
import com.claudiodimauro.thefootballwall.api.beans.StatisticBean;

@Document(collection = "players")
public class Player {

	@Id
	private ObjectId id;
	private String name;
	private String surname;
	private int totalScore; //min 1 max 100
	private SkillBean skills;
	private StatisticBean statistics;
	
}
