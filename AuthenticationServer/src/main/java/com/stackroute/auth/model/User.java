package com.stackroute.auth.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	String userid;
	String username;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
