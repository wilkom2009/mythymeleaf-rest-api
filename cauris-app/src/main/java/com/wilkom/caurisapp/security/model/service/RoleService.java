package com.wilkom.caurisapp.security.model.service;

import com.wilkom.caurisapp.core.IMyService;
import com.wilkom.caurisapp.security.model.entity.Role;

public interface RoleService extends IMyService<Role, Long> {
	Role findByName(String name);

}
