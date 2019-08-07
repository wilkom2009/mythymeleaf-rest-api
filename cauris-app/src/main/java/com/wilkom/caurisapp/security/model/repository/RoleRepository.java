package com.wilkom.caurisapp.security.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.wilkom.caurisapp.security.model.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Role findByName(String name);

}
