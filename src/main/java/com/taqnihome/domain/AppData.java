package com.taqnihome.domain;

public class AppData {
	private String userId;
	private double latitude;
	private double longitude;
	private AppDetail[] appDetail;
	
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getUserId() {
		return userId;
	}
	public AppDetail[] getAppDetail() {
		return appDetail;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setAppDetail(AppDetail[] appDetail) {
		this.appDetail = appDetail;
	}
}
