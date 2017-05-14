package com.taqnihome.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameParticipantsDetail {
	
	@JsonProperty("wifi_bluetooth_name")
	private String wifiOrBluetoothName;
	
	@JsonProperty("game_package_name")
	private String gamePackageName;

	public String getWifiorBluetoothName() {
		return wifiOrBluetoothName;
	}

	public void setWifiorBluetoothName(String wifiOrBluetoothName) {
		this.wifiOrBluetoothName = wifiOrBluetoothName;
	}

	public String getGamePackageName() {
		return gamePackageName;
	}

	public void setGamePackageName(String gamePackageName) {
		this.gamePackageName = gamePackageName;
	}
}
