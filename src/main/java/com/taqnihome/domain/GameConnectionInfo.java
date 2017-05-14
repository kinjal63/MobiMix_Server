package com.taqnihome.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GameConnectionInfo {
	
	@JsonProperty("game_id")
	private long gameId;
	
	@JsonProperty("user_id")
	private String userId;

	@JsonProperty("is_group_owner")
	private int isGroupOwner;
	
	@JsonProperty("connected_user_id")
	private String connectedUserId;
	
	@JsonProperty("is_need_to_notify")
	private boolean isNeedToNotify;

	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getIsGroupOwner() {
		return isGroupOwner;
	}

	public void setIsGroupOwner(int isGroupOwner) {
		this.isGroupOwner = isGroupOwner;
	}

	public String getConnectedUserId() {
		return connectedUserId;
	}

	public void setConnectedUserId(String connectedUserId) {
		this.connectedUserId = connectedUserId;
	}

	public boolean getIsNeedToNotify() {
		return isNeedToNotify;
	}

	public void setIsNeedToNotify(boolean isNeedToNotify) {
		this.isNeedToNotify = isNeedToNotify;
	}
}
