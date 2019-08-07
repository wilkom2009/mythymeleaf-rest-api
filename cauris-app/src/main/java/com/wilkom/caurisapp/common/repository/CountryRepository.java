package com.wilkom.caurisapp.common.repository;

import org.springframework.data.repository.CrudRepository;

import com.wilkom.caurisapp.common.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {

	Country findByNom(String nom);

	Country findByCode(int code);

}
