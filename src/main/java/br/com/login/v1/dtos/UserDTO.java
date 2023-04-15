package br.com.login.v1.dtos;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	@NotBlank(message = "Field name cannot null")
	private String name;
	@NotBlank(message = "Field username cannot null")
	private String username;
	@NotNull(message = "Field access group cannot null")
	private AccessGroupDTO accessGroup;
	@NotNull(message = "Field enabled cannot null")
	private Boolean enabled;

	public UserDTO() {
	}

	public UserDTO(String name, String username, AccessGroupDTO accessGroup, Boolean enabled) {
		this.name = name;
		this.username = username;
		this.accessGroup = accessGroup;
		this.enabled = enabled;
	}

	public String getUsername() {
		return this.username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccessGroupDTO getAccessGroup() {
		return accessGroup;
	}

	public void setAccessGroup(AccessGroupDTO accessGroup) {
		this.accessGroup = accessGroup;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}

}
