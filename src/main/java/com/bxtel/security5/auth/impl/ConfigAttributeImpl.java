package com.bxtel.security5.auth.impl;

import com.bxtel.security5.auth.IConfigAttribute;

public class ConfigAttributeImpl  implements IConfigAttribute  {
	private static final long serialVersionUID = 1L;
	private final String role;
	ConfigAttributeImpl(String role){
		this.role=role;
	}
	@Override
	public String getAttribute() {
		return role;
	}
}
