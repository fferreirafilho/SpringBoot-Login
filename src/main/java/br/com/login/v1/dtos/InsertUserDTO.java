package br.com.login.v1.dtos;

public class InsertUserDTO {

	private UserDTO user;
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
