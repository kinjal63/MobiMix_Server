package com.taqnihome.domain;

public class UserDataUsage {
	private long mobileTx;
	private long mobileRx;
	private long wifiTx;
	private long wifiRx;
	private String userId;
	private String deviceId;
	private String country;
	private String operatorName;
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	private float latitude;
	private float longitude;
	
	public long getMobileTx() {
		return mobileTx;
	}
	public long getMobileRx() {
		return mobileRx;
	}
	public long getWifiTx() {
		return wifiTx;
	}
	public long getWifiRx() {
		return wifiRx;
	}
	public String getUserId() {
		return userId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public float getLatitude() {
		return latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setMobileTx(long mobileTx) {
		this.mobileTx = mobileTx;
	}
	public void setMobileRx(long mobileRx) {
		this.mobileRx = mobileRx;
	}
	public void setWifiTx(long wifiTx) {
		this.wifiTx = wifiTx;
	}
	public void setWifiRx(long wifiRx) {
		this.wifiRx = wifiRx;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
}
