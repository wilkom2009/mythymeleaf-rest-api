package com.wilkom.caurisapp.security.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilkom.caurisapp.core.MyService;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.repository.RoleRepository;
import com.wilkom.caurisapp.security.model.service.RoleService;

@Service
public class RoleServiceImpl extends MyService<Role, Long> implements RoleService {

	@Autowired
	protected RoleServiceImpl(RoleRepository repository) {
		super(repository);
	}

	private RoleRepository getRepository() {
		return (RoleRepository) repository;
	}
	@Override
	public Role findByName(String name) {
		return getRepository().findByName(name);
	}

}
