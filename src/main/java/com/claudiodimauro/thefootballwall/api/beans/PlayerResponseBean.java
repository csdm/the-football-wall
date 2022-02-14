package com.claudiodimauro.thefootballwall.api.beans;

import java.util.List;

import com.claudiodimauro.thefootballwall.api.models.Player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlayerResponseBean {
	private PlayerRequestBean request;
	private String status;
	private List<Player> list;
}
