package com.example.demo;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class LoginUserDetails extends User {
	private final LoginUser loginUser;
	
	public LoginUserDetails(LoginUser loginUser) {
		super(loginUser.getName(), loginUser.getPassword(), AuthorityUtils.createAuthorityList("ROLE_" + loginUser.getAuthority()));
		this.loginUser = loginUser;
	}
	
	public LoginUser getUserName() {
		return this.loginUser;
	}
}
