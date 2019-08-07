package com.wilkom.caurisapp.common.service;

import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.core.IMyService;

public interface CountryService extends IMyService<Country, Integer> {
	Country findByNom(String nom);

	Country findByCode(int code);
}
