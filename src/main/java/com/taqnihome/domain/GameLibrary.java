package com.taqnihome.domain;

import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//@Table(name ="game_library")
public class GameLibrary implements Serializable{

//	@Id
//	@Column(name = "game_id")
//	private String gameId;
//	@Column(name = "game_name")
//	private String gameName;	
//	@ManyToOne
//	@Cascade(org.hibernate.annotations.CascadeType.ALL)
//	@JoinColumn(name = "game_category")
//	private GameCategory gameCategoryId;
//	@Column(name = "game_publiher")
//	private String gamePublisher;
//	@Column(name = "game_version")
//	private String gameVersion;
//
//	@Column
//	private Double gameRating;
//
//	@Column
//	private Integer ageRating;
//	
//	@Column
//	private String gamePlatform;
//	@Column
//	private Long creationDate;
//	@Column
//	private String googlePlayUrl;
//	@Column
//	private Long approvedDate;
//	@Column
//	private Boolean isApproved;
//	@Column
//	private String packageName;
//
//
//
//	public GameCategory getGameCategoryId() {
//		return gameCategoryId;
//	}
//
//	public void setGameCategoryId(GameCategory gameCategoryId) {
//		this.gameCategoryId = gameCategoryId;
//	}
//
//	public Boolean getApproved() {
//		return isApproved;
//	}
//
//	public void setApproved(Boolean approved) {
//		isApproved = approved;
//	}
//	
//	
//	public Integer getAgeRating() {
//		return ageRating;
//	}
//
//	public void setAgeRating(Integer ageRating) {
//		this.ageRating = ageRating;
//	}
//
//	public String getGameId() {
//		return gameId;
//	}
//
//	public void setGameId(String gameId) {
//		this.gameId = gameId;
//	}
//
//	public String getGameName() {
//		return gameName;
//	}
//
//	public void setGameName(String gameName) {
//		this.gameName = gameName;
//	}
//
//	public String getGameVersion() {
//		return gameVersion;
//	}
//
//	public void setGameVersion(String gameVersion) {
//		this.gameVersion = gameVersion;
//	}
//
//	public String getGamePublisher() {
//		return gamePublisher;
//	}
//
//	public void setGamePublisher(String gamePublisher) {
//		this.gamePublisher = gamePublisher;
//	}
//
//	public Double getGameRating() {
//		return gameRating;
//	}
//
//	public void setGameRating(Double gameRating) {
//		this.gameRating = gameRating;
//	}
//
//	public String getGamePlatform() {
//		return gamePlatform;
//	}
//
//	public void setGamePlatform(String gamePlatform) {
//		this.gamePlatform = gamePlatform;
//	}
//
//	public Long getCreationDate() {
//		return creationDate;
//	}
//
//	public void setCreationDate(Long creationDate) {
//		this.creationDate = creationDate;
//	}
//
//	public String getGooglePlayUrl() {
//		return googlePlayUrl;
//	}
//
//	public void setGooglePlayUrl(String googlePlayUrl) {
//		this.googlePlayUrl = googlePlayUrl;
//	}
//
//	public Long getApprovedDate() {
//		return approvedDate;
//	}
//
//	public void setApprovedDate(Long approvedDate) {
//		this.approvedDate = approvedDate;
//	}
//
//	public Boolean getIsApproved() {
//		return isApproved;
//	}
//
//	public void setIsApproved(Boolean isApproved) {
//		this.isApproved = isApproved;
//	}
//
//	public String getPackageName() {
//		return packageName;
//	}
//
//	public void setPackageName(String packageName) {
//		this.packageName = packageName;
//	}
	
}
