package com.twoteethreeeight.userservice.services;

import java.time.LocalDateTime;

public class CodeTmp {

	private final String code;
	private final LocalDateTime expirationTime;
	
	public CodeTmp(String code,LocalDateTime expirationTime) {
		this.code = code;
		this.expirationTime = expirationTime;
	}
	
	public String getCode() {
		return code;
	}
	public boolean isExpirated() {
		return LocalDateTime.now().isAfter(expirationTime);
	}
	public boolean isExpired(LocalDateTime currentTime) {
        return currentTime.isAfter(expirationTime);
    }
}
