package com.taqnihome.model.db;

public class NearByUserDBModel {
	private String userId;
	private String name;
	private String deviceToken;
	
	public String getUserId() {
		return userId;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public String getUserName() {
		return name;
	}
	public void setUserName(String userName) {
		this.name = userName;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
}
