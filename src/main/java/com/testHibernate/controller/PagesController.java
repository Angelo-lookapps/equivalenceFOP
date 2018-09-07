package com.testHibernate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PagesController {
	@GetMapping("/")
	public String accueil() {
		return "pages/login";		
	}
	@GetMapping("/productList")
	public String productList() {
		return "pages/productList";		
	}
	@GetMapping("/homePage")
	public String homePage() {
		return "pages/home";		
	}
	@GetMapping("/profile")
	public String profilePage() {
		return "pages/espacePersonnel/profile";		
	}
	
	@GetMapping("/signup")
	public String signUpPage() {
		return "pages/signup";		
	}

	@GetMapping("/home")
	public String home(@RequestParam(required=false, defaultValue="World") String name, ModelMap modelMap) {
		modelMap.put("name", name);	
		modelMap.put("pathSource", "src/main/ressources/static/");
		//System.out.println("\n\n\n " + name);
		return "pages/home";
	}

	@RequestMapping(value = "/login")
	public String login(@RequestParam(required=true) String pseudo,
			@RequestParam(required=true) String mdp, ModelMap modelMap) {
		
		if(pseudo.toUpperCase().equals("ADMIN")) {
			if(mdp.equals("admin")) {
				modelMap.put("pseudo", pseudo);	
				return "pages/home";
			}
			else if(!mdp.equals("admin")) {
				modelMap.put("errorlogin", "2");	
				return "pages/login";
			}
		}else if(!pseudo.toUpperCase().equals("ADMIN")) {
			modelMap.put("errorlogin", "1");
			return "pages/login";
		}
		
		return "pages/login";	
	}
	@RequestMapping(value = "/logout")
	public String logout() {
		return "pages/login";	
	}
	
	///enregistrement

	@GetMapping("/newRequest")
	public String ajouterDemande() {
		return "pages/enregistrement/newRequest";		
	}
	@GetMapping("/newDiploma")
	public String ajouterDiplome() {
		return "pages/enregistrement/newDiploma";		
	}
	@GetMapping("/diplomaList")
	public String listDiplome() {
		return "pages/enregistrement/diplomaList";		
	}
	@GetMapping("/requestList")
	public String listDemande() {
		return "pages/enregistrement/requestList";		
	}
	
	
	//Equivalence
	@GetMapping("/newArrete")
	public String ajoutArrete() {
		return "pages/equivalence/newArrete";		
	}
	@GetMapping("/checkArrete")
	public String matchArrete() {
		return "pages/equivalence/checkArrete";		
	}
	//espace personnel
	@GetMapping("/listStaff")
	public String listPersonnel() {
		return "pages/espacePersonnel/listStaff";		
	}
	@GetMapping("/newStaff")
	public String ajoutStaff() {
		return "pages/espacePersonnel/newStaff";		
	}
	@GetMapping("/profileUser")
	public String profile() {
		return "pages/espacePersonnel/profile";		
	}

	//Liste promo
	@GetMapping("/listProm")
	public String listePromo() {
		return "pages/listePromotion/listProm";		
	}	
	@GetMapping("/newListProm")
	public String ajoutListePromo() {
		return "pages/listePromotion/newListProm";		
	}	
	
		
	
}
