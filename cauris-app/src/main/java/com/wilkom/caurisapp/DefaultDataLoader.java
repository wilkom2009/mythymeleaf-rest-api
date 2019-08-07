package com.wilkom.caurisapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.wilkom.caurisapp.security.model.entity.MyUser;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.service.MyUserService;
import com.wilkom.caurisapp.security.model.service.RoleService;


/**
 * Cette classe permet le chargement des données par défaut à chaque
 * actualisation du Context Spring..
 * 
 * @author KPEGLI
 *
 */
@Component
public class DefaultDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	private boolean alreadySetup = false;

	@Autowired
	private MyUserService userRepository;

	@Autowired
	private RoleService roleRepository;
//
//	@Autowired
//	private PrivilegeDao privilegeRepository;
//
//	@Autowired
//	private UserDetailService detailRepository;

	// API

	@Override
	@Transactional
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}

		// == create initial roles
		final Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
		final Role userRole = createRoleIfNotFound("ROLE_USER");
		final Role manRole = createRoleIfNotFound("ROLE_MANAGER");

		// == create initial user
		createUserIfNotFound("support@prospinvestment.com", "admin", new ArrayList<Role>(Arrays.asList(adminRole)));
		createUserIfNotFound("info@prospinvestment.com", "info", new ArrayList<Role>(Arrays.asList(userRole,manRole)));

		alreadySetup = true;
	}

	@Transactional
	private final Role createRoleIfNotFound(final String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(name);
			role = roleRepository.save(role);
		}		
		return role;
	}

	@Transactional
	private final MyUser createUserIfNotFound(final String email, final String password, final Collection<Role> roles) {
		MyUser user = userRepository.findByEmail(email);
		if (user == null) {
			user = new MyUser(email, password, roles);
//			UserDetail ud = new UserDetail("Admin", "ADMIN", gender.MASCULIN, BirthID.defaultBirthID,
//					new Adresse("2 rue 12", "BP 3", "Qt", null, "22-22-22-22", email, "www.prospinvestment.com"));
//			
//			user.setUserDetail(detailRepository.saveOne(ud));
//			user.setEmail(email);
//			user.setPassword(password);
			return userRepository.registerUser(email, password, roles);
		}
		return user;
	}

}