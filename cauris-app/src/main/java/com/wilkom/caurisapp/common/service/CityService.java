package com.wilkom.caurisapp.common.service;

import java.util.List;

import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.core.IMyService;

public interface CityService extends IMyService<City, Integer> {
	City findByName(String name);

	List<City> findByCountry(Country country);
}
