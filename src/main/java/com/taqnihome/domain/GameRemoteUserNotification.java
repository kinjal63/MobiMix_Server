package com.taqnihome.domain;

import java.util.ArrayList;

public class GameRemoteUserNotification {

	private String groupOwnerPushToken;
	private String groupOwnerUserId;
	private ArrayList<String> connectedUserIds;

	public String getGroupOwnerPushToken() {
		return groupOwnerPushToken;
	}

	public void setGroupOwnerPushToken(String groupOwnerPushToken) {
		this.groupOwnerPushToken = groupOwnerPushToken;
	}

	public String getGroupOwnerUserId() {
		return groupOwnerUserId;
	}

	public void setGroupOwnerUserId(String groupOwnerUserId) {
		this.groupOwnerUserId = groupOwnerUserId;
	}

	public ArrayList<String> getConnectedUserIds() {
		return connectedUserIds;
	}

	public void setConnectedUserIds(ArrayList<String> connectedUserIds) {
		this.connectedUserIds = connectedUserIds;
	}

}
