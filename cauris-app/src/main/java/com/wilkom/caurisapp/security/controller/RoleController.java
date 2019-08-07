package com.wilkom.caurisapp.security.controller;

import javax.validation.Valid;

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
import com.wilkom.caurisapp.security.model.RoleModel;
import com.wilkom.caurisapp.security.model.entity.Role;
import com.wilkom.caurisapp.security.model.service.RoleService;

@Controller
@RequestMapping("/roles/")
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleSce;

	@GetMapping("add")
	public String showForm(Role role) {
		return "admin/roleform";
	}

	@PostMapping("add")
	public String add(@Valid Role role, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			return "admin/roleform";
		}
		if (roleSce.findByName(role.getName())!=null) {
			redirectAttributes.addFlashAttribute("message", "Un role de meme nom existe déjà!");
			return "admin/roleform";
		}
		if (roleSce.save(role) != null) {
			redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		} else
			return "/error";

		return "redirect:list";
	}

	@GetMapping("list")
	public ModelAndView getRoles() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("roles", roleSce.findAll());
		mav.setViewName("/admin/rolelist");
		return mav;
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Role role = roleSce.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		model.addAttribute("role", role);
		return "/admin/roleform-update";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") long id, @Valid Role role, BindingResult result, Model model) {
		if (result.hasErrors()) {
			role.setId(id);
			return "/admin/roleform-update";
		}

		roleSce.save(role);
		model.addAttribute("roles", roleSce.findAll());
		return "/admin/rolelist";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") long id, Model model, final RedirectAttributes redirectAttributes) {
		Role role = roleSce.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		roleSce.delete(role);
		redirectAttributes.addFlashAttribute("message", "Operation effectuee avec succes!");
		model.addAttribute("roles", roleSce.findAll());
		return "/admin/rolelist";
	}
}
