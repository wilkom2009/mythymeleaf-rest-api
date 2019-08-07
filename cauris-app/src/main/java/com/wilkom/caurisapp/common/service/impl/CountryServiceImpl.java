package com.wilkom.caurisapp.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.common.repository.CountryRepository;
import com.wilkom.caurisapp.common.service.CountryService;
import com.wilkom.caurisapp.core.MyService;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.repository.RoleRepository;
import com.wilkom.caurisapp.security.model.service.RoleService;

@Service ("countryService")
public class CountryServiceImpl extends MyService<Country, Integer> implements CountryService {

	@Autowired
	protected CountryServiceImpl(CountryRepository repository) {
		super(repository);
	}

	private CountryRepository getRepository() {
		return (CountryRepository) repository;
	}

	@Override
	public Country findByNom(String nom) {
		return getRepository().findByNom(nom);
	}

	@Override
	public Country findByCode(int code) {
		return getRepository().findByCode(code);
	}
	

}
