package com.taqnihome.model.db;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GamePlayersInfo {
	@JsonProperty("name")
	private String username;
	
	@JsonProperty("connection_type")
	private int connectionType;
	
	@JsonProperty("is_group_owner")
	private int isGroupOwner;
	
	@JsonProperty("device_name")
	private String deviceName;

	public String getUsername() {
		return username;
	}

	public int getIsGroupOwner() {
		return isGroupOwner;
	}

	public void setIsGroupOwner(int isGroupOwner) {
		this.isGroupOwner = isGroupOwner;
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

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	
}
