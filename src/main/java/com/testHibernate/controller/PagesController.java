package com.testHibernate.controller;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testHibernate.converts.demande.DemandeToDemandeForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.helpers.TempActivite;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.service.cin.CINService;
import com.testHibernate.service.demande.FicheDemandeService;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.diplome.NiveauDiplomeService;
import com.testHibernate.service.historique.ActiviteRecentService;

@Controller
public class PagesController {
	 ///SERVICES
	 private FicheDemandeService ficheDemandeService;
	 private HttpSession session; 
	 private ActiviteRecentService activiteRecentService;
	 private GlobalHelper global = new GlobalHelper();
	 
	 int nombreLigneMax = 5;
	 private List<FicheDemande> fiches;
	 
	 @Autowired
	 public void setActiviteRecentService(ActiviteRecentService activiteRecentService) {
		this.activiteRecentService = activiteRecentService;
	 }
	 
	 @Autowired
	 public void setSession(HttpSession session) {
		this.session = session;
	 } 
	 
	 @Autowired
	 public void setNiveauDiplomeService(NiveauDiplomeService niveauDiplomeService) {
	 }
	 
	 @Autowired
	 public void setDemandeToDemandeForm(DemandeToDemandeForm demandeToDemandeForm) {
	 }
	 
	 @Autowired
	 public void setCINService(CINService cinService) {
	 }
	 
	 @Autowired
	 public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
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
	
	@GetMapping("/timeline")
	public String historique(ModelMap modelMap) {
		List<ActiviteRecent> activities = activiteRecentService.listAll();
		List<TempActivite> tempActivities = null;
		try {
			
			tempActivities = global.splitActivityTime(activities);  
			
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		//modelMap.put("nbFiche", ficheDemandeService.getFicheDemandeByMonth(10));
		modelMap.put("activities", activities);
		modelMap.put("tempActivities", tempActivities);  
		
		if(session.getAttribute("isConnected")!=null) {
			return "pages/timeline/timeline";	
		}	
		modelMap.put("errorlogin", "4");
		return "pages/login";
			
	}

	@GetMapping({"/home", "/home/statusRejet/{statusRejet}","/home/demande/page-{page}"})
	public String home(@RequestParam(required=false) Boolean statusRejet, @RequestParam(required=false) String name, @PathVariable(required=false) Optional<Integer> page, ModelMap modelMap) {
		//List<FicheDemande> ret = ficheDemandeService.listAll();
		List<ActiviteRecent> activities = activiteRecentService.getRecentActiviteByNumber(5);
		 //initial
		 initialListeFiche();
		int testDelete = activiteRecentService.deleteAllLast();
		List<FicheDemande> ret = ficheDemandeService.pagination(1, nombreLigneMax);
			if(page.isPresent()) {
				ret = ficheDemandeService.pagination(page.get(), nombreLigneMax);
				modelMap.put("pageActuel", page.get());
			}  
			try {
				ret = ficheDemandeService.selectByRejet(ret, false); 
				Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.fiches.size(), nombreLigneMax);
				modelMap.put("moyenne", this.getMoyenneDuMois((int)new Date().getDay()));
				modelMap.put("stats", this.getAllStatistiqueMonth());
				modelMap.put("nombrePagination", nombrePagination);
			} catch (Exception e) { 
				modelMap.put("error", e);
				e.printStackTrace();
	 			return "pages/erreur/505"; 
				//
			}
		HashMap<String, String> champs = GlobalHelper.getChampDemande();
		if(session.getAttribute("isConnected")!=null) {
			 
			String pseudo = ""+session.getAttribute("isConnected");
			modelMap.put("pseudo", pseudo);	
			modelMap.put("activities", activities);	
			modelMap.put("champs", champs); 
			modelMap.put("listeDemande", ret);
			modelMap.put("name", name);	
			modelMap.put("testDelete", testDelete);
			modelMap.put("pathSource", "src/main/ressources/static/");
			if(statusRejet!=null && statusRejet) {
				modelMap.put("statusRejet", statusRejet );
			}
			
			return "pages/home";
		}
		modelMap.put("errorlogin", "4");
		return "pages/login";
	}
	
	/*@GetMapping("/filter")
	public String home(@RequestParam(required=false, defaultValue = "fi.id") String champ, @RequestParam(required=false) String ordre, ModelMap modelMap) {
		 
		List<FicheDemande> ret = null;
		List<ActiviteRecent> activities = activiteRecentService.getRecentActiviteByNumber(5);
		if(champ.equals("*") && ordre.equals("ASC") || champ==null) {
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
			modelMap.put("activities", activities);
			
			modelMap.put("champs", champs);
			//modelMap.put("iteration", iteration);
			modelMap.put("listeDemande", ret);
			modelMap.put("pathSource", "src/main/ressources/static/");
			
			return "pages/home";
		}
		modelMap.put("errorlogin", "4");
		return "pages/login";
	}*/

	@PostMapping(value = "/login")
	public String login(@RequestParam(required=true) String pseudo,
			@RequestParam(required=true) String mdp, @RequestParam(required=false) Optional<Boolean> stillConnected, ModelMap modelMap) {
		
		if(pseudo.toUpperCase().equals("ADMIN")) {
			if(mdp.equals("admin")) {
				if(stillConnected.isPresent()) {
					String[] admin = {pseudo,mdp};
					session.setAttribute("keepConnected", admin);
				}
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
	public String logout(Model model) {
		if(session.getAttribute("keepConnected")!=null) {
			String[] ret = (String[])session.getAttribute("keepConnected");
			model.addAttribute("keepConnected", ret);
		}
		session.invalidate();
		model.addAttribute("logout", true);
		return "pages/login";	
	}
	public void initialListeFiche() {
		this.fiches = ficheDemandeService.listAll(); 
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
	
		
	public List<Long> getAllStatistiqueMonth() throws Exception{
		List<Long> ret = new ArrayList<Long>();
		int mois = 12;
		try {
			for(int i=1 ; i <= mois ; i++) {
				long somme =(Long)ficheDemandeService.getFicheDemandeByDayOrMonth("month" , i, true) + (Long)ficheDemandeService.getFicheDemandeByDayOrMonth("month" , i, false) ;
				System.out.println("\n getAllStatistiqueMonth = " + somme);
				ret.add(somme);
			}
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	
	public double getMoyenneDuMois(int mois) throws Exception{
		double ret = 0;
		try {
			double maxDaysOfMonth = 0;
			maxDaysOfMonth = DateHelper.getNombreJoursParMois(mois);
			System.out.println("\n\n maxDaysOfMonth ===== "+maxDaysOfMonth+"  DU mois = "+mois);
			long sommeStats = 0; 
			for(int k = 1 ; k < maxDaysOfMonth ; k++ ) {  
				long somme =(Long)ficheDemandeService.getFicheDemandeByDayOrMonth("day" , k, true) + (Long)ficheDemandeService.getFicheDemandeByDayOrMonth("day" , k, false) ;
				//System.out.println("\n SOMME = "+somme);
				sommeStats +=  somme;
				//System.out.println("day stats = "+sommeStats);
			}
			if(maxDaysOfMonth!=0) {
				ret = sommeStats/maxDaysOfMonth;
				System.out.println("MOYENNE = "+ret);
				//ret *= (double)100;
				System.out.println("MOYENNE ret = "+ret);
			} else {
				throw new Exception("Error division by 0 !!!");
			}
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	
}
