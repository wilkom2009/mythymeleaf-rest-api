package com.wilkom.caurisapp.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.common.repository.CityRepository;
import com.wilkom.caurisapp.common.repository.CountryRepository;
import com.wilkom.caurisapp.common.service.CityService;
import com.wilkom.caurisapp.common.service.CountryService;
import com.wilkom.caurisapp.core.MyService;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.repository.RoleRepository;
import com.wilkom.caurisapp.security.model.service.RoleService;

@Service ("cityService")
public class CityServiceImpl extends MyService<City, Integer> implements CityService {

	@Autowired
	protected CityServiceImpl(CityRepository repository) {
		super(repository);
	}

	private CityRepository getRepository() {
		return (CityRepository) repository;
	}

	@Override
	public City findByName(String name) {
		return getRepository().findByName(name);
	}

	@Override
	public List<City> findByCountry(Country country) {
		return getRepository().findByCountry(country);
	}

	

}
