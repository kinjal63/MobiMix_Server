package com.taqnihome.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "taqnihome_admin")
public class UserAdmin implements Serializable {
	 private static final long serialVersionUID = 1L;
	 	@Id
	    private String id;
	 	@Column(name = "name")
	    private String name;
	 	@Column(name = "password")
	    private String password;
	 	@Column(name = "email")
	    private String email;
	 	@Column(name = "date_of_birth")
	    private Long dob;
	 	@Column(name = "is_email_verified")
	    private Boolean emailVerified;
	 	@Column(name = "created_on")
	    private Long creationDate;
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
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
		public Boolean getEmailVerified() {
			return emailVerified;
		}
		public void setEmailVerified(Boolean emailVerified) {
			this.emailVerified = emailVerified;
		}
		public Long getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Long creationDate) {
			this.creationDate = creationDate;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
	    
	    
}
