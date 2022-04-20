package com.claudiodimauro.thefootballwall.api.services;

import java.io.IOException;
import java.io.InputStream;

import com.claudiodimauro.thefootballwall.api.beans.responses.BasePlayerResponse;
import com.claudiodimauro.thefootballwall.api.models.Player;

public interface PlayerService {
	public BasePlayerResponse<?> findAllPlayers();
	public BasePlayerResponse<?> findPaginatedPlayers(int pg, int size);
	public BasePlayerResponse<?> findPlayer(String surname, String name);
	public BasePlayerResponse<?> findPlayerByPlayerId(String playerId);
	public BasePlayerResponse<?> findTopTenPlayers();
	public BasePlayerResponse<?> addPlayer(Player player);
	public InputStream showImage(String playerId, boolean isFullImage);
}
