package com.bxtel.user.vo;

import com.bxtel.user.model.User;

public class RegistInfo {
	String yzm;
	User user;
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}