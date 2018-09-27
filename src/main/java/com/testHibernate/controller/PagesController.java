package com.testHibernate.controller;
 
import java.util.HashMap;
import java.util.List;  
 
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testHibernate.converts.demande.DemandeToDemandeForm;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.service.cin.CINService;
import com.testHibernate.service.demande.FicheDemandeService;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.diplome.NiveauDiplomeService;

@Controller
public class PagesController {
	 ///SERVICES
	 private FicheDemandeService ficheDemandeService;
	 private CINService cinService;
	 private ListesDiplomeService listesDiplomeService;
	 private HttpSession session;
	 
	 @Autowired
	 public void setSession(HttpSession session) {
		this.session = session;
	 }
	 
	 ///CONVERTS
	 private DemandeToDemandeForm demandeToDemandeForm;
	 
	 @Autowired
	 public void setNiveauDiplomeService(NiveauDiplomeService niveauDiplomeService) {
	 }
	 
	 @Autowired
	 public void setDemandeToDemandeForm(DemandeToDemandeForm demandeToDemandeForm) {
		this.demandeToDemandeForm = demandeToDemandeForm;
	 }
	 
	 @Autowired
	 public void setCINService(CINService cinService) {
		this.cinService = cinService;
	 }
	 
	 @Autowired
	 public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
		this.listesDiplomeService = listesDiplomeService;
	 }
	 
	 @Autowired
	 public void setFicheDemandeService(FicheDemandeService ficheDemandeService) {
		this.ficheDemandeService = ficheDemandeService;
	 }
	
	
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
		return "redirect:/home";		
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
	public String home(@RequestParam(required=false) String name, ModelMap modelMap) {
		List<FicheDemande> ret = ficheDemandeService.listAll();
		HashMap<String, String> champs = GlobalHelper.getChampDemande();
		if(session.getAttribute("isConnected")!=null) {
			 
			String pseudo = ""+session.getAttribute("isConnected");
			modelMap.put("pseudo", pseudo);	
			modelMap.put("champs", champs); 
			modelMap.put("listeDemande", ret);
			modelMap.put("name", name);	
			modelMap.put("pathSource", "src/main/ressources/static/");
			
			return "pages/home";
		}
		modelMap.put("errorlogin", "4");
		return "pages/login";
	}
	@GetMapping("/filter")
	public String home(@RequestParam(required=false) String champ, @RequestParam(required=false) String ordre, ModelMap modelMap) {
		 
		List<FicheDemande> ret = null;
		if(champ.equals("*") && ordre.equals("ASC")) {
			ret = ficheDemandeService.listAll();
		}
		else if(!champ.equals("*") && ordre.equals("ASC")) {
			ret = ficheDemandeService.getFicheDemandeByFilterASC(champ );
		}else {
			ret = ficheDemandeService.getFicheDemandeByFilterDESC(champ );
		}
											
		HashMap<String, String> champs = GlobalHelper.getChampDemande();
		
		if(session.getAttribute("isConnected")!=null) {
			String pseudo = ""+session.getAttribute("isConnected");
			modelMap.put("pseudo", pseudo);	
			modelMap.put("champs", champs);
			//modelMap.put("iteration", iteration);
			modelMap.put("listeDemande", ret);
			modelMap.put("pathSource", "src/main/ressources/static/");
			
			return "pages/home";
		}
		modelMap.put("errorlogin", "4");
		return "pages/login";
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam(required=true) String pseudo,
			@RequestParam(required=true) String mdp, ModelMap modelMap) {
		
		if(pseudo.toUpperCase().equals("ADMIN")) {
			if(mdp.equals("admin")) {
				
				session.setAttribute("isConnected", pseudo);
				return "redirect:/home";
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
	@GetMapping(value = "/logout")
	public String logout() {
		session.invalidate();
		return "pages/login";	
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

		
	
}
