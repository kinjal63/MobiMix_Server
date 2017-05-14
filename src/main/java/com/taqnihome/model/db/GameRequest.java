package com.taqnihome.model.db;

import com.google.gson.annotations.SerializedName;

public class GameRequest {
	@SerializedName("connection_type")
    private int connectionType;

    @SerializedName("notification_type")
    private int notificationType;

    @SerializedName("remote_user_id")
    private String remoteUserId;

    @SerializedName("remote_user_name")
    private String remoteUserName;

    @SerializedName("game_id")
    private long gameId;

    @SerializedName("game_name")
    private String gameName;

    @SerializedName("game_package_name")
    private String gamePackageName;

    @SerializedName("bluetooth_address")
    private String bluetoothAddress;

    @SerializedName("wifi_address")
    private String wifiAddress;

	public int getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(int connectionType) {
		this.connectionType = connectionType;
	}

	public int getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(int notificationType) {
		this.notificationType = notificationType;
	}

	public String getRemoteUserId() {
		return remoteUserId;
	}

	public void setRemoteUserId(String remoteUserId) {
		this.remoteUserId = remoteUserId;
	}

	public String getRemoteUserName() {
		return remoteUserName;
	}

	public void setRemoteUserName(String remoteUserName) {
		this.remoteUserName = remoteUserName;
	}

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGamePackageName() {
		return gamePackageName;
	}

	public void setGamePackageName(String gamePackageName) {
		this.gamePackageName = gamePackageName;
	}

	public String getBluetoothAddress() {
		return bluetoothAddress;
	}

	public void setBluetoothAddress(String bluetoothAddress) {
		this.bluetoothAddress = bluetoothAddress;
	}

	public String getWifiAddress() {
		return wifiAddress;
	}

	public void setWifiAddress(String wifiAddress) {
		this.wifiAddress = wifiAddress;
	}
}
