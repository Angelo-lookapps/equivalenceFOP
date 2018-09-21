package com.testHibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ErrorPageController {
	
	@GetMapping("/error404/{page}")
	public String productList(@PathVariable String page, Model model) {
		model.addAttribute("pageRedirect", page);
		return "pages/erreur/404";		
	}
	@GetMapping("/error505")
	public String homePage() {
		return "pages/erreur/505";		
	}
	
		
	
}
