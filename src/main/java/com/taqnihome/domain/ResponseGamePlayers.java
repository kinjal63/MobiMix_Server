package com.taqnihome.domain;

import com.google.gson.annotations.SerializedName;

public class ResponseGamePlayers {
	@SerializedName("game_players")
	private Object gamePlayers;

	public Object getGamePlayers() {
		return gamePlayers;
	}

	public void setGamePlayers(Object gamePlayers) {
		this.gamePlayers = gamePlayers;
	}
}
