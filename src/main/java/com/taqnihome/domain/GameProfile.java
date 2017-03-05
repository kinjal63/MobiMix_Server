package com.taqnihome.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "game_profile")
public class GameProfile implements Serializable {

	@Id
	@Column(name = "game_profile_id")
	private String gameProfileId;

	@JsonIgnoreProperties("gameProfiles")
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	private User userId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="game_library_id",nullable = false)
	private GameLibrary gameLibrary;

	/*@Column(name = "start_time")
	private Long startTime;

	@Column(name = "end_time")
	private Long endTime;*/

	@Column(name = "creation_date")
	private Long creationDate;

	@Column(name = "is_available")
	private Boolean isAvailable;

	public String getGameProfileId() {
		return gameProfileId;
	}

	public void setGameProfileId(String gameProfileId) {
		this.gameProfileId = gameProfileId;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public GameLibrary getGameLibrary() {
		return gameLibrary;
	}

	public void setGameLibrary(GameLibrary gameLibrary) {
		this.gameLibrary = gameLibrary;
	}

	/*public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}*/

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	

}
