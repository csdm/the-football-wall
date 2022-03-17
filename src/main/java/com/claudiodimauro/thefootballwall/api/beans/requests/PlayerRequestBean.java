package com.claudiodimauro.thefootballwall.api.beans.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequestBean {
	private String playerName;
	private String playerSurname;
	private String playerId;
}