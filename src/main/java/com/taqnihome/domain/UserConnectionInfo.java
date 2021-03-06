package com.taqnihome.domain;

import java.util.ArrayList;

public class UserConnectionInfo {
	private String userId;
	private ArrayList<String> remoteUserIds;
	private int connectionInvite;
	private String gamePackageName;
	
	public int getConnectionInvite() {
		return connectionInvite;
	}
	public void setConnectionInvite(int connectionInvite) {
		this.connectionInvite = connectionInvite;
	}
	public String getUserId() {
		return userId;
	}
	public ArrayList<String> getRemoteUserIds() {
		return remoteUserIds;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setRemoteUserIds(ArrayList<String> remoteUserIds) {
		this.remoteUserIds = remoteUserIds;
	}
	public void setGamePackageName(String gamePackageName) {
		this.gamePackageName = gamePackageName;
	}
	public String getGamePackageName() {
		return this.gamePackageName;
	}
}
