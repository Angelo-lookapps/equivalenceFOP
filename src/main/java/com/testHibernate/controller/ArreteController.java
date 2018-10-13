package com.testHibernate.controller;
 
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.testHibernate.converts.equivalence.ArreteEqRefFormToArreteEqRef;
import com.testHibernate.converts.equivalence.ArreteEqRefToArreteEqRefForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.helpers.Encrypt;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;
import com.testHibernate.model.equivalence.ChampArreteEqForm;
import com.testHibernate.model.equivalence.ContentArrete;
import com.testHibernate.model.equivalence.ContentArreteForm;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.equivalence.ArreteEqRefService;
import com.testHibernate.service.equivalence.ContentArreteService;
import com.testHibernate.service.historique.ActiviteRecentService; 

@Controller
public class ArreteController {
	
	 private ArreteEqRefService arreteEqRefService;
	 private ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm; 
	 private ListesDiplomeService listesDiplomeService;
	 List<ArreteEqRef> arretes ;
	 
	 private ActiviteRecentService activiteRecentService;
	 
	 @Autowired
	 public void setActiviteRecentService(ActiviteRecentService activiteRecentService) {
		this.activiteRecentService = activiteRecentService;
	 }
	 private ContentArreteService contentArreteService;
	 @Autowired
	 public void setContentArreteService(ContentArreteService contentArreteService) {
		this.contentArreteService = contentArreteService;
		
	 } 
	 @Autowired
	 public void setArreteEqRefFormToArreteEqRef(ArreteEqRefFormToArreteEqRef arreteEqRefFormToArreteEqRef) {
	 }
	 
	
	 private HttpSession session;
	 int nombreLigneMax = 5;
	 @Autowired
	 public void setSession(HttpSession session) {
		this.session = session;
	 }

	@Autowired
	public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
		this.listesDiplomeService = listesDiplomeService;
	}
	 
	@Autowired
	public void setArreteEqRefService(ArreteEqRefService arreteEqRefService) {
		this.arreteEqRefService = arreteEqRefService;
	}
	
	@Autowired
	public void setArreteEqRefToArreteEqRefForm(ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm) {
		this.arreteEqRefToArreteEqRefForm = arreteEqRefToArreteEqRefForm;
	}
	
	//Equivalence
	@PostMapping("/saveArrete")
	public String ajoutArrete(@Valid  @ModelAttribute ArreteEqRefForm arreteEqRefForm , @RequestParam String listeDiplome, BindingResult bindingResult, Model model) {
		
		ArreteEqRef listesSaved = null;
		if(bindingResult.hasErrors()){
			return "redirect/error404/listArrete";
		}
		try {
			listesSaved = arreteEqRefService.getArreteByIdDiplome(Long.valueOf(listeDiplome));
			
		if(listesSaved!=null) {
			return "redirect:/listArrete/isExist-"+listesSaved.getId();
		}
		 
		arreteEqRefForm.setListesDiplome(listesDiplomeService.getById(Long.valueOf(listeDiplome)));
		
		listesSaved = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
		//Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "une nouvelle arrêté avec comme titre : \""+listesSaved.getTitre()+" année sortie: "+listesSaved.getAnneeSortie()+"\""));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique
		 	
		//initialisation ContentArrete
		ContentArrete content = new ContentArrete(); 
		content.setArreteEqRef(listesSaved);
		content.setDateAjout(GlobalHelper.getCurrentDate());
		ContentArrete temp = this.contentArreteService.saveOrUpdate(content);
		arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate());
		}catch(Exception e) {
			model.addAttribute("error", e.getMessage());
 			return "pages/erreur/505"; 
		//	e.printStackTrace();
		}
		return "redirect:/newArrete/" + listesSaved.getId();		
	}
	//Equivalence
	@PutMapping("/updateArrete/{id}")
	public String updateArrete(@Valid @ModelAttribute ArreteEqRefForm arreteEqRefForm, @RequestParam String listeDiplome , @PathVariable String id, BindingResult bindingResult, Model model) {
		ArreteEqRef updateEntity = null;
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		}
		try{ 
			arreteEqRefForm.setListesDiplome(listesDiplomeService.getById(Long.valueOf(listeDiplome)));
			arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate()); 
			updateEntity= arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
		}catch(Exception ex) {
			model.addAttribute("error", ex.getMessage());
 			return "pages/erreur/505"; 
			//ex.printStackTrace();
		}
		return "redirect:/newArrete/" + updateEntity.getId();		
	}
	
	@GetMapping("/newArrete/{id}")
	public String newArrete(@PathVariable String id, Model model){
		try { 
			 
			ArreteEqRef listesSaved = arreteEqRefService.getById(Long.valueOf(id));
			ContentArrete contentArrete = contentArreteService.getContentByArrete(Long.valueOf(id))!=null ? contentArreteService.getContentByArrete(Long.valueOf(id)) : null;
			
			
			if(listesSaved==null) {
				return "redirect:/error404/listArrete";	
			}
			ArreteEqRefForm arreteEqRefForm = listesSaved!=null ? this.arreteEqRefToArreteEqRefForm.convert(listesSaved) : new ArreteEqRefForm();
			 	
			//List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
			List<String> listEcole = listesDiplomeService.getAllEcole();
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
			String contentArticle = GlobalHelper._ArticleContent;
			
			model.addAttribute("arreteEqRef", listesSaved);
			model.addAttribute("annees", annee);
			model.addAttribute("listEcole", listEcole);
			//model.addAttribute("listeDiploma", listeDiploma);
			model.addAttribute("arreteEqRefForm", arreteEqRefForm);
			model.addAttribute("idArrete", arreteEqRefForm!=null ? arreteEqRefForm.getId().toString() : "");
			 
			model.addAttribute("contentArticle", contentArticle);
			model.addAttribute("contentArrete",  contentArrete);
			model.addAttribute("champArreteEqForm", new ChampArreteEqForm());
			model.addAttribute("contentArreteForm", new ContentArreteForm());
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}
		if(session.getAttribute("isConnected")!=null) { 
			return "pages/equivalence/newArrete";
		}	
		model.addAttribute("errorlogin", "4");
		return "pages/login";
		
	
	}
	
	@GetMapping("/checkArrete")
	public String matchArrete() {
		return "pages/equivalence/checkArrete";		
	}
	
	@GetMapping({"/listArrete", "/listArrete/isExist-{idArrete}", "/listArrete/page-{page}"})
	public String listArrete(@PathVariable(required=false) Optional<Integer> idArrete, @PathVariable(required=false) Optional<Integer> page, Model model) {
		
		try {	
				List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
				List<String> listEcole = listesDiplomeService.getAllEcole();
				 
				List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
				
				if(idArrete.isPresent()) {
					model.addAttribute("isExistArrete", idArrete.get());
				}
				initialListeArrete();
				List<ArreteEqRef> arreteList = arreteEqRefService.pagination(1, nombreLigneMax);
					if(page.isPresent()) {
						arreteList = arreteEqRefService.pagination(page.get(), nombreLigneMax);
					}  
					try {
						Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.arretes.size(), nombreLigneMax);
						model.addAttribute("nombrePagination", nombrePagination);
					} catch (Exception e) { 
						e.printStackTrace();
					}
				
				model.addAttribute("arreteEqRefForm", new ArreteEqRefForm());
				model.addAttribute("annees", annee);
				model.addAttribute("listEcole", listEcole);
				model.addAttribute("listeDiploma", listeDiploma);
				model.addAttribute("arreteList", arreteList);
		
			 
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}

		if(session.getAttribute("isConnected")!=null) { 
			return "pages/equivalence/listArrete";	
		}
		model.addAttribute("errorlogin", "4");
		return "pages/login";
	}
	 
	@PostMapping("/saveContent/{id}")
	public String articleLoiArrete(@PathVariable String id, @Valid @ModelAttribute ContentArreteForm contentArreteForm, Model model, BindingResult bindingResult) {
		
		ContentArrete listesSaved = null;
		ContentArreteForm content = contentArreteForm; 
	/*	System.out.println("\n\n\n contenu = "+contenu);
			ArreteEqRef temp = arreteEqRefService.getById(Long.valueOf(id));		//add arreteEqRef to entete foreign key
			contentArreteForm.setArreteEqRef(temp);
			contentArreteForm.setContenu(contenu);*/
		ArreteEqRef temp = arreteEqRefService.getById(Long.valueOf(id));		//add arreteEqRef to entete foreign key
		content.setArreteEqRef(temp);
		content.setDateAjout(GlobalHelper.getCurrentDate());
		if(bindingResult.hasErrors()){
			 return "redirect:/error505"; 
		 }
	try {
			
			listesSaved = contentArreteService.saveOrUpdateContentArreteForm(content);
			//Mis en historique
			 ActiviteRecent historique = new ActiviteRecent(); 
			 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "un contenue à l'arrêté \""+listesSaved.getArreteEqRef().getTitre()+" année sortie: "+listesSaved.getArreteEqRef().getAnneeSortie()+"\""));
			 	historique.setDateAjout(GlobalHelper.getCurrentDate());
			 	activiteRecentService.saveOrUpdate(historique);
		 	 //fin historique
						
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}
			
		return "redirect:/newArrete/" + id ;		
	}
	
	public void initialListeArrete() {
		if(this.arretes==null){
			this.arretes = arreteEqRefService.listAll();
		}
	 }
	

}
