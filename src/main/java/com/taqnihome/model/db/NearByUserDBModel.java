package com.taqnihome.model.db;

public class NearByUserDBModel {
	private String userId;
	private String deviceToken;
	
	public String getUserId() {
		return userId;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
}
