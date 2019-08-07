package com.wilkom.caurisapp.portfolio.controller;

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
import org.springframework.validation.annotation.Validated;
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
import com.wilkom.caurisapp.common.entity.Bank;
import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.common.service.BankService;
import com.wilkom.caurisapp.common.service.CityService;
import com.wilkom.caurisapp.common.service.CountryService;
import com.wilkom.caurisapp.portfolio.entity.ResourcePerson;
import com.wilkom.caurisapp.portfolio.entity.service.ResourcePersonService;
import com.wilkom.caurisapp.security.model.RoleModel;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.service.RoleService;

@Controller
@RequestMapping("/resourcepersons/")
public class ResourcePersonController extends BaseController {

	@Autowired
	private ResourcePersonService personService;

	@GetMapping("add")
	public String showForm(Model model) {
		model.addAttribute("person", new ResourcePerson());
		return "portfolio/resourcepersonform";
	}

	@PostMapping("add")
	public String add(@ModelAttribute("person") @Validated ResourcePerson person, BindingResult bindingResult,
			final RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "portfolio/resourcepersonform";
		}

		if (personService.save(person) != null) {
			redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		} else
			return "/error";

		return "redirect:list";
	}

	@GetMapping("list")
	public ModelAndView getPersons() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("persons", personService.findAll());
		mav.setViewName("/portfolio/resourcepersonlist");
		return mav;
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		ResourcePerson person = personService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Person Id:" + id));
		model.addAttribute("person", person);
		return "/portfolio/resourcepersonform-update";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") long id, @Valid ResourcePerson person, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/portfolio/resourcepersonform-update";
		}
		person.setId(id);
		Optional<ResourcePerson> oldC = personService.findById(id);
		person.setVersion(oldC.orElse(null).getVersion());
		if (personService.save(person) != null) {
			redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		} else {
			return "/error";
		}
		model.addAttribute("persons", personService.findAll());
		return "redirect:/resourcepersons/list";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") long id, Model model, final RedirectAttributes redirectAttributes) {
		ResourcePerson person = personService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid bank Id:" + id));
		personService.delete(person);
		redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		model.addAttribute("persons", personService.findAll());
		return "redirect:/resourcepersons/list";
	}

}
