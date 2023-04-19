package br.com.login.v1.dtos;

import br.com.login.v1.dtos.validations.PasswordValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class InsertUserDTO {

	@NotNull(message = "User cannot null")
	@Valid
	private UserDTO user;
	@NotNull(message = "Password cannot null")
	@PasswordValidation()
	private String password;

	public InsertUserDTO() {

	}

	public InsertUserDTO(UserDTO user, String password) {
		this.user = user;
		this.password = password;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
