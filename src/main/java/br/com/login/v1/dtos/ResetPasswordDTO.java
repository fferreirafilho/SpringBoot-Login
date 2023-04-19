package br.com.login.v1.dtos;

import java.io.Serializable;

import br.com.login.v1.dtos.validations.PasswordValidation;
import jakarta.validation.constraints.NotBlank;

public class ResetPasswordDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Password cannot null")
	@PasswordValidation
	private String password;

	public ResetPasswordDTO() {

	}

	public ResetPasswordDTO(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
