package com.wilkom.caurisapp.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.wilkom.caurisapp.BaseController;
import com.wilkom.caurisapp.common.entity.AppMenuItem;

@Controller
public class MainController extends BaseController {
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "index";
	}

//	@GetMapping("/customers")
//	public ModelAndView getConstomers() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("customers", customerRepo.findAll());
//		mav.setViewName("/customers/customers");
//		return mav;
//	}

	@GetMapping("error")
	public ModelAndView error() {
		ModelAndView mav = new ModelAndView();
		String errorMessage = "You are not authorized for the requested data!!!";
		mav.addObject("errorMsg", errorMessage);
		mav.setViewName("403");
		return mav;
	}

	@GetMapping("/")
	public ModelAndView getIndex() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

}
