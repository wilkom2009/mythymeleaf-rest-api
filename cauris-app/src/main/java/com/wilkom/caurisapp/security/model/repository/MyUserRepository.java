package com.wilkom.caurisapp.security.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wilkom.caurisapp.security.model.entity.MyUser;

public interface MyUserRepository extends CrudRepository<MyUser, Long> {
	MyUser findByEmail(String email);

	@Override
	void delete(MyUser user);
}
