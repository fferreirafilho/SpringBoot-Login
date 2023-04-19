package br.com.login.v1.dtos;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Email cannot null")
	@Email(message = "Username must be a valid email")
	private String email;
	@NotBlank(message = "Password cannot null")
	private String password;

	public AuthDataDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
