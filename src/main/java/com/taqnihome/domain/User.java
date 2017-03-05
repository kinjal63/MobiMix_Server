package com.taqnihome.domain;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.DoubleAccumulator;

import javax.persistence.*;

import com.sun.javafx.scene.paint.GradientUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.geo.Point;

@Entity
@Table(name = "taqnihome_user")
public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "mac_address")

    private String macAddress;
    @Column(name = "email")
    private String email;
    @Column(name = "dob")
    private Long dob;
    @Column(name = "gender")
    private String gender;
    @Column(name = "photo_url")
    private String photoUrl;
    @Column(name = "is_email_verified")
    private Boolean emailVerified;
    @Column(name = "creation_date")
    private Long creationDate;

    @Transient
    private Long startTime;

    @Transient
    private Long endTime;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<GameProfile> gameProfiles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<GameProfileTimeDetails> gameProfileTime;


    @ManyToMany
    @JoinTable(name = "device_details_mapping", joinColumns = @JoinColumn(name = "user_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "device_id", nullable = false))
    private List<DeviceDetails> userDeviceDetailses;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public List<DeviceDetails> getUserDeviceDetailses() {
        return userDeviceDetailses;
    }

    public void setUserDeviceDetailses(List<DeviceDetails> userDeviceDetailses) {
        this.userDeviceDetailses = userDeviceDetailses;
    }

    public List<GameProfile> getGameProfiles() {
        return gameProfiles;
    }

    public void setGameProfiles(List<GameProfile> gameProfiles) {
        this.gameProfiles = gameProfiles;

    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDob() {
        return dob;
    }

    public void setDob(Long dob) {
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public List<GameProfileTimeDetails> getGameProfileTime() {
        return gameProfileTime;
    }

    public void setGameProfileTime(List<GameProfileTimeDetails> gameProfileTime) {
        this.gameProfileTime = gameProfileTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

}
