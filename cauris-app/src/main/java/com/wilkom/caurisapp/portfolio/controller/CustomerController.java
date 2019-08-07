package com.wilkom.caurisapp.portfolio.controller;


import java.util.Optional;

import javax.validation.Valid;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wilkom.caurisapp.BaseController;
import com.wilkom.caurisapp.common.entity.Adresse;
import com.wilkom.caurisapp.common.entity.BirthID;
import com.wilkom.caurisapp.common.entity.IDCard;
import com.wilkom.caurisapp.common.entity.Nationality;
import com.wilkom.caurisapp.portfolio.entity.Customer;
import com.wilkom.caurisapp.portfolio.entity.service.CustomerService;

@Controller
@RequestMapping("/customers/")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("add")
	public String showForm(Model model) {
		model.addAttribute("person", new Customer(new Adresse(), new Nationality(), new IDCard(), new BirthID()));
		return "portfolio/customerform";
	}

	@PostMapping("add")
	public String add(@ModelAttribute("person") @Validated Customer person, BindingResult bindingResult,
			final RedirectAttributes redirectAttributes) {
		
		if (bindingResult.hasErrors()) {
			return "portfolio/customerform";
		}

		if (customerService.save(person) != null) {
			redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		} else
			return "/error";

		return "redirect:list";
	}

	@GetMapping("list")
	public ModelAndView getPersons() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("persons", customerService.findAll());
		mav.setViewName("/portfolio/customerlist");
		return mav;
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Customer person = customerService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Person Id:" + id));
		model.addAttribute("person", person);
		return "/portfolio/customerform-update";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") long id, @Valid Customer person, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/portfolio/customerform-update";
		}
		person.setId(id);
		Optional<Customer> oldC = customerService.findById(id);
		person.setVersion(oldC.orElse(null).getVersion());
		if (customerService.save(person) != null) {
			redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		} else {
			return "/error";
		}
		model.addAttribute("persons", customerService.findAll());
		return "redirect:/customers/list";
	}
	
	/**
	 * 
	 * @param model
	 * @param countryCode
	 * @return 
	 */
	@GetMapping("/countrycities/{id}")
	public String getCountrycities(Model model, @PathVariable("id") int countryCode) {
		System.out.println("Country Code : "+countryCode);
		model.addAttribute("countrycities", cityService.findByCountry(countryService.findByCode(countryCode)));
		return "customerform :: indCity";
	}

}
