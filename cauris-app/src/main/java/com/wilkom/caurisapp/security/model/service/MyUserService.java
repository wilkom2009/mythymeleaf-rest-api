package com.wilkom.caurisapp.security.model.service;

import java.util.Collection;

import com.wilkom.caurisapp.core.IMyService;
import com.wilkom.caurisapp.security.model.entity.MyUser;
import com.wilkom.caurisapp.security.model.entity.Role;

public interface MyUserService extends IMyService<MyUser, Long>{

	MyUser registerUser(String email, String password, Collection<Role> roles);

	void deleteUser(MyUser user);

	MyUser findByEmail(String email);
}
