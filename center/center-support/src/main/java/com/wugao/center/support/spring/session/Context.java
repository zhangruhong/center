package com.wugao.center.support.spring.session;

import java.io.Serializable;

import com.wugao.center.domain.user.User;

public class Context implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
