package com.siriam.empmgt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	Logger log=LoggerFactory.getLogger(getClass());
	
	@GetMapping
	public String getHome() {
		log.info("Website: Index Requested");
		return "index";
	}

	@GetMapping("/about")
	public String getAboutProject() {
		log.info("Website: About Requested");
		return "about";
	}
	
	@GetMapping("/technologies")
	public String getTechnologies() {
		return "/technologies";
	}
	@GetMapping("/contact")
	public String getContact() {
		return "/contact";
	}
	@GetMapping("/test")
	public String gettest() {
		return "test";
	}
}
