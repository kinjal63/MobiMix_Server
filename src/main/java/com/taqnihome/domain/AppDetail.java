package com.taqnihome.domain;

public class AppDetail {
	private String appName;
	private String appPackageName;

	public String getAppName() {
		return appName;
	}

	public String getAppPackageName() {
		return appPackageName;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	private String appVersion;
}
