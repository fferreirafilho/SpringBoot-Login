package br.com.login.v1.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_roles")
public class RoleModel implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	@Column(name = "name", nullable = false, unique = true)
	private String name;
	@Column(name = "description", nullable = false)
	private String description;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "tb_roles_groups", 
	  joinColumns = @JoinColumn(name = "role_id"), 
	  inverseJoinColumns = @JoinColumn(name = "group_id"))
	private List<AccessGroupModel> groups;

	public RoleModel() {
	}

	public RoleModel(UUID id, String name, String description) {
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

	@Override
	public String getAuthority() {
		return this.name;
	}
	@JsonIgnore
	public List<AccessGroupModel> getGroups() {
		return groups;
	}

	public void setGroups(List<AccessGroupModel> groups) {
		this.groups = groups;
	}
}
