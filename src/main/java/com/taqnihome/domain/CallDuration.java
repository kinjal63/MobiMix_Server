package com.taqnihome.domain;

public class CallDuration {

	private String userId;
	private long sim1_call_duration;
	private long sim2_call_duration;
	
	public String getUserId() {
		return userId;
	}
	public long getSim1_call_duration() {
		return sim1_call_duration;
	}
	public long getSim2_call_duration() {
		return sim2_call_duration;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setSim1_call_duration(long sim1_call_duration) {
		this.sim1_call_duration = sim1_call_duration;
	}
	public void setSim2_call_duration(long sim2_call_duration) {
		this.sim2_call_duration = sim2_call_duration;
	}
}
