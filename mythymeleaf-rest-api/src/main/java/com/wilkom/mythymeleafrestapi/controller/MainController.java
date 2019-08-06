package com.wilkom.mythymeleafrestapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * @author Informatique
 *
 */
@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
	


	@GetMapping(value="/")
	public String index(Model model) {
		logger.debug("Accueil");
		return "redirect:/posts";
	}
}
