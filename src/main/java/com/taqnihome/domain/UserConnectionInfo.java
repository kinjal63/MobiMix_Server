package com.taqnihome.domain;

import java.util.ArrayList;

public class UserConnectionInfo {
	private long userId;
	private ArrayList<Long> remoteUserIds;
	private String wifiDeviceAddress;
	
	public String getWifiDeviceAddress() {
		return wifiDeviceAddress;
	}
	public void setWifiDeviceAddress(String wifiDeviceAddress) {
		this.wifiDeviceAddress = wifiDeviceAddress;
	}
	public long getUserId() {
		return userId;
	}
	public ArrayList<Long> getRemoteUserIds() {
		return remoteUserIds;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setRemoteUserIds(ArrayList<Long> remoteUserIds) {
		this.remoteUserIds = remoteUserIds;
	}
}
