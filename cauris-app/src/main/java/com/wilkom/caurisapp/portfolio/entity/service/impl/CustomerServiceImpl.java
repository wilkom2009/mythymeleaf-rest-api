package com.wilkom.caurisapp.portfolio.entity.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilkom.caurisapp.MyUtils;
import com.wilkom.caurisapp.core.MyService;
import com.wilkom.caurisapp.portfolio.entity.Customer;
import com.wilkom.caurisapp.portfolio.entity.CustomerAccount;
import com.wilkom.caurisapp.portfolio.entity.repository.CustomerRepository;
import com.wilkom.caurisapp.portfolio.entity.service.CustomerAccountService;
import com.wilkom.caurisapp.portfolio.entity.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl extends MyService<Customer, Long> implements CustomerService {

	@Autowired
	private CustomerAccountService accountSce;

	@Autowired
	protected CustomerServiceImpl(CustomerRepository repository) {
		super(repository);
	}

	private CustomerRepository getRepository() {
		return (CustomerRepository) repository;
	}

	@Override
	public Customer save(Customer entity) {
		entity.setCustomerCode(MyUtils.generate100000Code("CUS-", (int) (getRepository().count() + 1)));
		entity = super.save(entity);
		//numero de compte au format : CODECUSTOMER/ACNT01
		entity.setAccount(accountSce
				.save(new CustomerAccount(entity.getCustomerCode() + "/ACNT01", entity.getCreatedAt(), entity)));
		return entity;
	}

}
