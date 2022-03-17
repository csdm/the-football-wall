package com.claudiodimauro.thefootballwall.api.beans.responses;

import org.springframework.data.domain.Page;

import com.claudiodimauro.thefootballwall.api.beans.requests.PlayerRequestBean;
import com.claudiodimauro.thefootballwall.api.models.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerPaginationResponse extends BasePlayerResponse<Page<Player>> {

	public PlayerPaginationResponse(PlayerRequestBean requestKey, String status, Long retrievedItems, Page<Player> payload) {
		super(requestKey, status, retrievedItems, payload);
	}

}