package com.builder;

import java.util.concurrent.atomic.AtomicInteger;

import com.model.ArtBoxUser;

public class UserBuilder {

	private ArtBoxUser user;
	private static AtomicInteger counter = new AtomicInteger(0);

	public UserBuilder() {
		this.user = new ArtBoxUser();
	}
	
	public UserBuilder setUserName(String name){
		this.user.setName(name);
		return this;
	}

	public UserBuilder setUserId() {
		Integer id = null;
		id = counter.incrementAndGet();
		this.user.setId(id);
		return this;
	}

	public UserBuilder setUserEmail(String email) {
		this.user.setEmail(email);
		return this;
	}

	public UserBuilder setUserPassword(String password) {
		this.user.setPassword(password);
		return this;
	}

	public ArtBoxUser getUserBuild() {
		return this.user;
	}

}
