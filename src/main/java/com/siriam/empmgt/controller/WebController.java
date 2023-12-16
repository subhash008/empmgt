package com.siriam.empmgt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	Logger log=LoggerFactory.getLogger(getClass());
	
	@GetMapping
	public String getHome(Model model) {
		log.info("Website: Index Requested");
		model.addAttribute("page", "index");
		return "index";
	}

	@GetMapping("/about")
	public String getAboutProject(Model model) {
		log.info("Website: About Requested");
		model.addAttribute("page", "about");
		return "about";
	}
	
	@GetMapping("/technologies")
	public String getTechnologies(Model model) {
		model.addAttribute("page", "technologies");
		return "technologies";
	}
	@GetMapping("/contact")
	public String getContact(Model model) {
		model.addAttribute("page", "contact");
		return "contact";
	}
	@GetMapping("/test")
	public String gettest(Model model) {
		return "test";
	}
}
