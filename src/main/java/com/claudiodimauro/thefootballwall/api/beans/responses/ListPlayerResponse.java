package com.claudiodimauro.thefootballwall.api.beans.responses;

import java.util.List;

import com.claudiodimauro.thefootballwall.api.beans.requests.PlayerRequestBean;
import com.claudiodimauro.thefootballwall.api.models.Player;

public class ListPlayerResponse extends BasePlayerResponse<List<Player>> {

	public ListPlayerResponse(PlayerRequestBean requestKey, String status, Long retrievedItems, List<Player> payload) {
		super(requestKey, status, retrievedItems, payload);
	}
	
}
