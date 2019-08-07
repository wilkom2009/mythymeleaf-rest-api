package com.wilkom.caurisapp.security.model;

import java.util.Collection;
import java.util.Objects;

import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.wilkom.caurisapp.security.model.entity.MyUser;
import com.wilkom.caurisapp.security.model.entity.Role;

public class RoleModel {

	private Long id;

	@ManyToMany(mappedBy = "roles")
	private Collection<MyUser> users;

	@NotNull
	@Size(min = 3, message = "Minimum 3 caratères!")
	private String name;

	private Role role;

	protected RoleModel() {
		super();
	}

	public RoleModel(Role role) {
		super();
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<MyUser> getUsers() {
		return users;
	}

	public void setUsers(Collection<MyUser> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}
	
	public Role getNewRole() {
		return new Role(name);
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, role, users);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleModel other = (RoleModel) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(role, other.role)
				&& Objects.equals(users, other.users);
	}

	@Override
	public String toString() {
		return role.toString();
	}

}
