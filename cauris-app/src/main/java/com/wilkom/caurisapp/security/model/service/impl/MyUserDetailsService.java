package com.wilkom.caurisapp.security.model.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wilkom.caurisapp.security.error.UserNotFoundException;
import com.wilkom.caurisapp.security.model.entity.MyUser;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.repository.MyUserRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	MyUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
		MyUser mUser = userRepo.findByEmail(username);
		if (mUser != null) {
			return new User(mUser.getEmail(), mUser.getPassword(), getGrantedAuthorities(mUser));
		}
		throw new UserNotFoundException("Aucun utilisateur avec le nom : " + username);
	}

	private final List<GrantedAuthority> getGrantedAuthorities(final MyUser user) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final Role role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}

}
