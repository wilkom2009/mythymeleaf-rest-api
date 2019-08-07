package com.wilkom.caurisapp.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wilkom.caurisapp.BaseController;
import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.common.service.CityService;
import com.wilkom.caurisapp.common.service.CountryService;
import com.wilkom.caurisapp.security.model.RoleModel;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.service.RoleService;

@Controller
@RequestMapping("/cities/")
public class CityController extends BaseController {

	@Autowired
	private CityService cityService;

	@GetMapping("add")
	public String showForm(City city) {
		return "common/cityform";
	}

	@PostMapping("add")
	public String add(@Valid City city, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "common/cityform";
		}
		if (cityService.findByName(city.getName()) != null) {
			redirectAttributes.addFlashAttribute("message", "Une ville de meme nom existe deja!");
			return "common/cityform";
		}
		if (cityService.save(city) != null) {
			redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		} else
			return "/error";

		return "redirect:list";
	}

	@GetMapping("list")
	public ModelAndView getRoles() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cities", cityService.findAll());
		mav.setViewName("/common/citieslist");
		return mav;
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		City city = cityService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid city Id:" + id));
		model.addAttribute("city", city);
		return "/common/cityform-update";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") int id, @Valid City city, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			city.setId(id);
			return "/common/cityform-update";
		}
		Optional<City> oldC = cityService.findById(id);
		city.setVersion(oldC.orElse(null).getVersion());
		if (cityService.save(city) != null) {
			redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		} else {
			return "/error";
		}
		model.addAttribute("cities", cityService.findAll());
		return "/common/citieslist";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
		City city = cityService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid city Id:" + id));
		cityService.delete(city);
		redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		model.addAttribute("cities", cityService.findAll());
		return "/common/citieslist";
	}

}
