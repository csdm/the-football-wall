package com.claudiodimauro.thefootballwall.api.beans;

import com.claudiodimauro.thefootballwall.utils.enums.FootPreference;
import com.claudiodimauro.thefootballwall.utils.enums.WorkRate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SkillBean {
	private int skillMoves;
	private int weakFoot;
	private WorkRate attWorkRate;
	private FootPreference preferredFoot;
	private int age;
	private String nationality;
	
	public SkillBean(int skillMoves, int weakFoot, WorkRate attWorkRate, FootPreference preferredFoot, int age,
			String nationality) {
		super();
		this.skillMoves = skillMoves;
		this.weakFoot = weakFoot;
		this.attWorkRate = attWorkRate;
		this.preferredFoot = preferredFoot;
		this.age = age;
		this.nationality = nationality;
	}	
}
