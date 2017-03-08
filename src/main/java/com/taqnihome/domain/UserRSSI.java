package com.taqnihome.domain;

public class UserRSSI {
	private String rssi;
	private String userId;
	private long latitude;
	private String operatorName;
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public long getLatitude() {
		return latitude;
	}
	public long getLongitude() {
		return longitude;
	}
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	private long longitude;
	
	public String getRssi() {
		return rssi;
	}
	public String getUserId() {
		return userId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setRssi(String rssi) {
		this.rssi = rssi;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	private String deviceId;
}
