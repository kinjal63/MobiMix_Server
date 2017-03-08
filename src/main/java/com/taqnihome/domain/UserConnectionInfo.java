package com.taqnihome.domain;

import java.util.ArrayList;

public class UserConnectionInfo {
	private String userId;
	private ArrayList<String> remoteUserIds;
	private String wifiDeviceAddress;
	
	public String getWifiDeviceAddress() {
		return wifiDeviceAddress;
	}
	public void setWifiDeviceAddress(String wifiDeviceAddress) {
		this.wifiDeviceAddress = wifiDeviceAddress;
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
}
