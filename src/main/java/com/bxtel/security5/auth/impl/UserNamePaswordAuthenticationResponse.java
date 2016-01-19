package com.bxtel.security5.auth.impl;

import java.util.ArrayList;

import com.bxtel.security5.auth.IAuthenticationResponse;

public class UserNamePaswordAuthenticationResponse implements IAuthenticationResponse {
	Object userdata;
	ArrayList<String>  authorities;
	UserNamePaswordAuthenticationResponse(Object userdata,ArrayList<String>  authorities)
	{
		this.userdata=userdata;
		this.authorities=authorities;
	}
	@Override
	public Object getUserData() {
		return userdata;
	}
	@Override
	public ArrayList getAuthorities() {
		return authorities;
	}
}
