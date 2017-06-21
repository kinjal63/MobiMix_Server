package com.taqnihome.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseGamePlayers {
	@JsonProperty("game_players")
	private Object gamePlayers;

	public Object getGamePlayers() {
		return gamePlayers;
	}

	public void setGamePlayers(Object gamePlayers) {
		this.gamePlayers = gamePlayers;
	}
}
