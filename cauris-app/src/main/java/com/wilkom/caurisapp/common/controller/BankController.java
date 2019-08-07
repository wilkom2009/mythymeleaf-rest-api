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
import com.wilkom.caurisapp.common.entity.Bank;
import com.wilkom.caurisapp.common.entity.City;
import com.wilkom.caurisapp.common.entity.Country;
import com.wilkom.caurisapp.common.service.BankService;
import com.wilkom.caurisapp.common.service.CityService;
import com.wilkom.caurisapp.common.service.CountryService;
import com.wilkom.caurisapp.security.model.RoleModel;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.service.RoleService;

@Controller
@RequestMapping("/banks/")
public class BankController extends BaseController {

	@Autowired
	private BankService bankService;

	@GetMapping("add")
	public String showForm(Bank bank) {
		return "common/bankform";
	}

	@PostMapping("add")
	public String add(@Valid Bank bank, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "common/bankform";
		}
		if (bankService.findByName(bank.getName()) != null) {
			redirectAttributes.addFlashAttribute("message", "Une banque de meme nom existe deja!");
			return "common/bankform";
		}
		if (bankService.save(bank) != null) {
			redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		} else
			return "/error";

		return "redirect:list";
	}

	@GetMapping("list")
	public ModelAndView getBanks() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("banks", bankService.findAll());
		mav.setViewName("/common/banklist");
		return mav;
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
		Bank bank = bankService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Bank Id:" + id));
		model.addAttribute("bank", bank);
		return "/common/bankform-update";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") int id, @Valid Bank bank, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/common/bankform-update";
		}
		bank.setCode(id);
		Optional<Bank> oldC = bankService.findById(id);
		bank.setVersion(oldC.orElse(null).getVersion());
		if (bankService.save(bank) != null) {
			redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		} else {
			return "/error";
		}
		model.addAttribute("banks", bankService.findAll());
		return "redirect:/banks/list";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
		Bank bank = bankService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bank Id:" + id));
		bankService.delete(bank);
		redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		model.addAttribute("banks", bankService.findAll());
		return "redirect:/banks/list";
	}


}
