package com.wilkom.caurisapp.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wilkom.caurisapp.common.entity.Bank;
import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;

public interface BankRepository extends CrudRepository<Bank, Integer> {

	Bank findByName(String name);

}
