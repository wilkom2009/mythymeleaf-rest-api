package com.wilkom.caurisapp;

import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.hibernate.annotations.LazyCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wilkom.caurisapp.common.entity.AppMenuItem;
import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.common.service.CityService;
import com.wilkom.caurisapp.common.service.CountryService;

public class BaseController {

	@Autowired
	protected CityService cityService;
	@Autowired
	protected CountryService countryService;

	@ModelAttribute("countries")
	@Lazy
	public List<Country> getCountries() {
		return IteratorUtils.toList(countryService.findAll().iterator());
	}

	@ModelAttribute("cities")
	@Lazy
	public List<City> getCities() {
		return IteratorUtils.toList(cityService.findAll().iterator());
	}

	@ModelAttribute("portefeuilleMenu")
	public AppMenuItem getPorteFeuilleMenu() {
		AppMenuItem mnPrt = new AppMenuItem("Portefeuille", "ROLE_PORTEFEUILLE", "#", null);
		AppMenuItem mnItRes = new AppMenuItem("Personnes Ressources", "ROLE_PORTEFEUILLE", "/resourcepersons/list",
				mnPrt);
		AppMenuItem mnItClt = new AppMenuItem("Clients", "ROLE_PORTEFEUILLE", "/customers/list", mnPrt);
		AppMenuItem mnItCom = new AppMenuItem("Commerciaux", "ROLE_PORTEFEUILLE", "/commercials/list", mnPrt);

		AppMenuItem mnItPay = new AppMenuItem("Paiements", "ROLE_PORTEFEUILLE", "/paiements/list", mnPrt);
		mnPrt.getMenuItems().add(mnItRes);
		mnPrt.getMenuItems().add(mnItClt);
		mnPrt.getMenuItems().add(mnItCom);

		mnPrt.getMenuItems().add(mnItPay);

		return mnPrt;
	}
}
