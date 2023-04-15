package br.com.login.v1.dtos;

import java.io.Serializable;

public class AuthTokenDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String accessToken;

	public AuthTokenDTO() {

	}

	public AuthTokenDTO(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
