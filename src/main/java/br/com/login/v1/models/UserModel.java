package br.com.login.v1.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class UserModel implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id")
	private UUID id;
	@Column(nullable = false, name = "name")
	private String name;
	@Column(nullable = false, unique = true, name = "username")
	private String username;
	@Column(nullable = false, name = "password")
	private String password;
	@Column(nullable = false, name = "enabled")
	private Boolean enabled;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "acces_group_id")
	private AccessGroupModel accessGroup;

	public UserModel() {
	}

	public UserModel(String name, String username, String password, Boolean enabled,
			AccessGroupModel accessGroup) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accessGroup = accessGroup;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.accessGroup.getRoles();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public AccessGroupModel getAccessGroup() {
		return accessGroup;
	}

	public void setAccessGroup(AccessGroupModel accessGroup) {
		this.accessGroup = accessGroup;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
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
		UserModel other = (UserModel) obj;
		return Objects.equals(id, other.id);
	}

}