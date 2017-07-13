package com.taqnihome.domain;

import java.util.ArrayList;

public class GameRemoteUserNotification {

	private String groupOwnerUserId;
	private ArrayList<String> connectedUserIds;

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
