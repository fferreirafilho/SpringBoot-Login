package br.com.login.v1.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
@Table(name = "tb_access_groups")
public class AccessGroupModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "tb_roles_groups", 
	  joinColumns = @JoinColumn(name = "group_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<RoleModel> roles;

	public AccessGroupModel() {
	}

	public AccessGroupModel(UUID id, String name) {
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

	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	
	public void addRole(RoleModel role) {
		roles.add(role);
	}
}
