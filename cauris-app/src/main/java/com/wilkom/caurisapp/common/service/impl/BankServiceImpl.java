package com.wilkom.caurisapp.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilkom.caurisapp.common.entity.Bank;
import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.common.repository.BankRepository;
import com.wilkom.caurisapp.common.repository.CityRepository;
import com.wilkom.caurisapp.common.repository.CountryRepository;
import com.wilkom.caurisapp.common.service.BankService;
import com.wilkom.caurisapp.common.service.CityService;
import com.wilkom.caurisapp.common.service.CountryService;
import com.wilkom.caurisapp.core.MyService;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.repository.RoleRepository;
import com.wilkom.caurisapp.security.model.service.RoleService;

@Service ("bankService")
public class BankServiceImpl extends MyService<Bank, Integer> implements BankService {

	@Autowired
	protected BankServiceImpl(BankRepository repository) {
		super(repository);
	}

	private BankRepository getRepository() {
		return (BankRepository) repository;
	}

	@Override
	public Bank findByName(String name) {
		return getRepository().findByName(name);
	}

	
}
