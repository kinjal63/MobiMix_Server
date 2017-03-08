package com.taqnihome.domain;

public class GameDataModel {
	private String gameName;
	private String gamePackageName;
	private long gameId;
	private String gameImagePath;
	private int gameNetworkType;
	
	public int getGameNetworkType() {
		return gameNetworkType;
	}
	public void setGameNetworkType(int gameNetworkType) {
		this.gameNetworkType = gameNetworkType;
	}
	public String getGameName() {
		return gameName;
	}
	public String getGamePackageName() {
		return gamePackageName;
	}
	public long getGameId() {
		return gameId;
	}
	public String getGameImagePath() {
		return gameImagePath;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public void setGamePackageName(String gamePackageName) {
		this.gamePackageName = gamePackageName;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
	public void setGameImagePath(String gameImagePath) {
		this.gameImagePath = gameImagePath;
	}
}
