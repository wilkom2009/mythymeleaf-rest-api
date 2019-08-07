package com.wilkom.caurisapp.portfolio.entity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wilkom.caurisapp.common.entity.Bank;
import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.portfolio.entity.Customer;
import com.wilkom.caurisapp.portfolio.entity.ResourcePerson;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
