package br.com.login.v1.dtos;

import java.io.Serializable;
import java.util.UUID;

public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID id;
	private String name;
	private String description;

	public RoleDTO() {
	}

	public RoleDTO(UUID id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
