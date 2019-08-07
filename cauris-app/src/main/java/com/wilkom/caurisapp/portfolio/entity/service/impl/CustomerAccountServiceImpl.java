package com.wilkom.caurisapp.portfolio.entity.service.impl;

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
import com.wilkom.caurisapp.portfolio.entity.CustomerAccount;
import com.wilkom.caurisapp.portfolio.entity.ResourcePerson;
import com.wilkom.caurisapp.portfolio.entity.repository.CustomerAccountRepository;
import com.wilkom.caurisapp.portfolio.entity.repository.ResourcePersonRepository;
import com.wilkom.caurisapp.portfolio.entity.service.CustomerAccountService;
import com.wilkom.caurisapp.portfolio.entity.service.ResourcePersonService;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.repository.RoleRepository;
import com.wilkom.caurisapp.security.model.service.RoleService;

@Service ("customerAccountService")
public class CustomerAccountServiceImpl extends MyService<CustomerAccount, String> implements CustomerAccountService {

	@Autowired
	protected CustomerAccountServiceImpl(CustomerAccountRepository repository) {
		super(repository);
	}

	private CustomerAccountRepository getRepository() {
		return (CustomerAccountRepository) repository;
	}

		
}
