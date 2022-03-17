package com.claudiodimauro.thefootballwall.api.beans.responses;

import com.claudiodimauro.thefootballwall.api.models.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerInsertResponse extends BasePlayerResponse<Player> {
	private Player insertedPlayer;
	private String status;	
	
	public PlayerInsertResponse(Player insertedPlayer, String status) {
		super(null, status, null, null);
		this.insertedPlayer = insertedPlayer;
	}
	
}