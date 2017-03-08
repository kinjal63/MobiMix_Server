package com.taqnihome.domain;

import java.util.List;

public class UserGameResponse {
	
	private String userId;
	private String userImagePath;
	private String userFirstName;
	private String userLastName;
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	private List<Long> gameId;
	private List<String> gameName;
	private List<String> gameImagePath;
	
	public String getUserId() {
		return userId;
	}
	public String getUserImagePath() {
		return userImagePath;
	}
	public List<Long> getGameId() {
		return gameId;
	}
	public List<String> getGameName() {
		return gameName;
	}
	public List<String> getGameImagePath() {
		return gameImagePath;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}
	public void setGameId(List<Long> gameId) {
		this.gameId = gameId;
	}
	public void setGameName(List<String> gameName) {
		this.gameName = gameName;
	}
	public void setGameImagePath(List<String> gameImagePath) {
		this.gameImagePath = gameImagePath;
	}
}