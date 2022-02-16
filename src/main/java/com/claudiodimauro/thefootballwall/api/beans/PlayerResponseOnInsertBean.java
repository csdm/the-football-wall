package com.claudiodimauro.thefootballwall.api.beans;

import com.claudiodimauro.thefootballwall.api.models.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerResponseOnInsertBean {
	private Player insertedPlayer;
	private String status;
}
