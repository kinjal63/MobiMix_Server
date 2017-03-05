package com.taqnihome.domain;

import org.springframework.web.multipart.MultipartFile;

public class GameData {
	private long gameId;
	
	public long getGameId() {
		return gameId;
	}
	public void setGameId(long gameId) {
		this.gameId = gameId;
	}
	private String gameName;
	private String gamePublisherName;
	private String gamePackageName;
	private String gameStudioName;
	private MultipartFile gameImagePath;
	
	public MultipartFile getGameImagePath() {
		return gameImagePath;
	}
	public void setGameImagePath(MultipartFile gameImagePath) {
		this.gameImagePath = gameImagePath;
	}
	private String ageRating;
	private int osType;
	private int networkType;
	private String minPlayers;
	private String maxPlayers;
	
	public String getGameName() {
		return gameName;
	}
	public String getGamePublisherName() {
		return gamePublisherName;
	}
	public String getGamePackageName() {
		return gamePackageName;
	}
	public String getGameStudioName() {
		return gameStudioName;
	}
	public String getAgeRating() {
		return ageRating;
	}
	public int getOsType() {
		return osType;
	}
	public int getNetworkType() {
		return networkType;
	}
	public String getMinPlayers() {
		return minPlayers;
	}
	public String getMaxPlayers() {
		return maxPlayers;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	public void setGamePublisherName(String gamePublisherName) {
		this.gamePublisherName = gamePublisherName;
	}
	public void setGamePackageName(String gamePackageName) {
		this.gamePackageName = gamePackageName;
	}
	public void setGameStudioName(String gameStudioName) {
		this.gameStudioName = gameStudioName;
	}
	public void setAgeRating(String ageRating) {
		this.ageRating = ageRating;
	}
	public void setOsType(int osType) {
		this.osType = osType;
	}
	public void setNetworkType(int networkType) {
		this.networkType = networkType;
	}
	public void setMinPlayers(String minPlayers) {
		this.minPlayers = minPlayers;
	}
	public void setMaxPlayers(String maxPlayers) {
		this.maxPlayers = maxPlayers;
	}
}