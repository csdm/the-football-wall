package com.claudiodimauro.thefootballwall.api.beans.responses;

import java.util.List;

import com.claudiodimauro.thefootballwall.api.beans.requests.PlayerRequestBean;
import com.claudiodimauro.thefootballwall.api.models.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BasePlayerResponse<T> {
	private PlayerRequestBean requestKey;
	private String status;
	private Long retrievedItems;
	private T payload;
}