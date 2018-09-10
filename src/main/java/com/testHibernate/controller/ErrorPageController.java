package com.testHibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {
	
	@GetMapping("/error404")
	public String productList() {
		return "pages/erreur/404";		
	}
	@GetMapping("/error505")
	public String homePage() {
		return "pages/erreur/505";		
	}
	
		
	
}
