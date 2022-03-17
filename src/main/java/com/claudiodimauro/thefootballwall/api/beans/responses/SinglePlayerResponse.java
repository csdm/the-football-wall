package com.claudiodimauro.thefootballwall.api.beans.responses;

import com.claudiodimauro.thefootballwall.api.beans.requests.PlayerRequestBean;
import com.claudiodimauro.thefootballwall.api.models.Player;

public class SinglePlayerResponse extends BasePlayerResponse<Player> {

	public SinglePlayerResponse(PlayerRequestBean requestKey, String status, Long retrievedItems, Player payload) {
		super(requestKey, status, retrievedItems, payload);
	}

}
