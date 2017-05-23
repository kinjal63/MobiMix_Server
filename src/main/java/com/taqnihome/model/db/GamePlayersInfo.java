package com.taqnihome.model.db;

import com.google.gson.annotations.SerializedName;

public class GamePlayersInfo {
	@SerializedName("name")
	private String username;
	
	@SerializedName("connection_type")
	private int connectionType;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(int connectionType) {
		this.connectionType = connectionType;
	}
}
