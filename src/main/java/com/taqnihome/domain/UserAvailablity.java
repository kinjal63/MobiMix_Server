package com.taqnihome.domain;

public class UserAvailablity{
	private long userId;
	private int availablity;
	private long latitude;
	private long longitude;
	
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
	public long getUserId() {
		return userId;
	}
	public int getAvailablity() {
		return availablity;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setAvailablity(int availablity) {
		this.availablity = availablity;
	}
}