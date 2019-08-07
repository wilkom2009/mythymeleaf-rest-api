package com.wilkom.caurisapp.common.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;

public interface CityRepository extends CrudRepository<City, Integer> {

	City findByName(String name);

	List<City> findByCountry(Country country);

}
