package br.com.login.v1.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AccessGroupDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private UUID id;
	@NotBlank(message = "Name cannot empty")
	private String name;
	@NotNull(message = "List roles cannot null")
	private List<RoleDTO> roles;

	public AccessGroupDTO() {
	}

	public AccessGroupDTO(UUID id, String name) {
		this.id = id;
		this.name = name;
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

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}
	
}
