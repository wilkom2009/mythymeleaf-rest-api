package com.wilkom.caurisapp.security.model.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilkom.caurisapp.core.MyService;
import com.wilkom.caurisapp.security.model.entity.MyUser;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.repository.MyUserRepository;
import com.wilkom.caurisapp.security.model.service.MyUserService;

@Service
public class MyUserServiceImpl extends MyService<MyUser, Long> implements MyUserService {

	@Autowired
	protected MyUserServiceImpl(MyUserRepository repository) {
		super(repository);
	}

	@Override
	public MyUser registerUser(String email, String password, Collection<Role> roles) {
		MyUser us = findByEmail(email);
		if (us == null) {
			us = new MyUser(email, password, roles);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			us.setPassword(encoder.encode(us.getPassword()));
			return repository.save(us);
		}
		return us;
	}

	private MyUserRepository getRepository() {
		return (MyUserRepository) repository;
	}

	@Override
	public void deleteUser(MyUser user) {
		getRepository().delete(user);
	}

	@Override
	public MyUser findByEmail(String email) {
		return getRepository().findByEmail(email);
	}

}
