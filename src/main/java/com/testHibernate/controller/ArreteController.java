package com.testHibernate.controller;
 
import java.util.List;

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

import com.testHibernate.converts.equivalence.ArreteEqRefToArreteEqRefForm; 
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.helpers.GlobalHelper; 
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm; 
import com.testHibernate.model.equivalence.ChampArreteEqForm;
import com.testHibernate.model.equivalence.ContentArrete;
import com.testHibernate.model.equivalence.ContentArreteForm; 
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.equivalence.ArreteEqRefService; 
import com.testHibernate.service.equivalence.ContentArreteService; 

@Controller
public class ArreteController {
	
	 private ArreteEqRefService arreteEqRefService;
	 private ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm; 
	 
	 private ListesDiplomeService listesDiplomeService;
	 
	 private ContentArreteService contentArreteService;
	 
	 @Autowired
	 public void setContentArreteService(ContentArreteService contentArreteService) {
		this.contentArreteService = contentArreteService;
	 } 
	 private HttpSession session;
	 
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
	public String ajoutArrete(@Valid  @ModelAttribute ArreteEqRefForm arreteEqRefForm , BindingResult bindingResult, Model model) {
		ArreteEqRef listesSaved = null;
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		}
		arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate());
		listesSaved = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
			
		return "redirect:/newArrete/" + listesSaved.getId();		
	}
	//Equivalence
	@PutMapping("/updateArrete/{id}")
	public String updateArrete(@Valid @ModelAttribute ArreteEqRefForm arreteEqRefForm , @PathVariable String id, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		}
		/*ArreteEqRef newEntity = arreteEqRefFormToArreteEqRef.convert(arreteEqRefForm);
		ArreteEqRef updateEntity = arreteEqRefService.getById(Long.valueOf(id));
		updateEntity.setListesDiplome(newEntity.getListesDiplome());
		updateEntity.setTitre(newEntity.getTitre());
		updateEntity.setAnneeSortie(newEntity.getAnneeSortie());*/
		arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate()); 
		ArreteEqRef updateEntity = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
		return "redirect:/newArrete/" + updateEntity.getId();		
	}
	
	@GetMapping("/newArrete/{id}")
	public String newArrete(@PathVariable String id, Model model){
		try { 
			 
			ArreteEqRef listesSaved = arreteEqRefService.getById(Long.valueOf(id));
			System.out.println("\n\n\n id == "+id);
			if(listesSaved==null) {
				return "redirect:/error404/listArrete";	
			}
			ArreteEqRefForm arreteEqRefForm = listesSaved!=null ? this.arreteEqRefToArreteEqRefForm.convert(listesSaved) : new ArreteEqRefForm();
			 	
			List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
			List<String> listEcole = listesDiplomeService.getAllEcole();
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
		
			model.addAttribute("arreteEqRef", listesSaved);
			model.addAttribute("annees", annee);
			model.addAttribute("listEcole", listEcole);
			model.addAttribute("listeDiploma", listeDiploma);
			model.addAttribute("arreteEqRefForm", arreteEqRefForm);
			model.addAttribute("idArrete", arreteEqRefForm!=null ? arreteEqRefForm.getId().toString() : "");
			 

			model.addAttribute("champArreteEqForm", new ChampArreteEqForm());
			model.addAttribute("contentArreteForm", new ContentArreteForm());
			
		} catch (Exception e) {
			e.printStackTrace();
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
	
	@GetMapping("/listArrete")
	public String listArrete(Model model) {
		
		try {	
				List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
				List<String> listEcole = listesDiplomeService.getAllEcole();
				List<ArreteEqRef> arreteList = arreteEqRefService.listAll();
				List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
				
				model.addAttribute("arreteEqRefForm", new ArreteEqRefForm());
				model.addAttribute("annees", annee);
				model.addAttribute("listEcole", listEcole);
				model.addAttribute("listeDiploma", listeDiploma);
				model.addAttribute("arreteList", arreteList);
			
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(session.getAttribute("isConnected")!=null) { 
			return "pages/equivalence/listArrete";	
		}	
		model.addAttribute("errorlogin", "4");
		return "pages/login";
	}
	 
	@PostMapping("/saveContent/{id}")
	public String articleLoiArrete(@PathVariable String id, @RequestParam(required=false, defaultValue = "Veuillez ecrire ici l'arrêté d'équivalence, svp!!! ") String contenu, Model model) {
		ContentArrete listesSaved = null;
		ContentArreteForm contentArreteForm = new ContentArreteForm();
			System.out.println("\n\n\n ************** TEST ***********\n");
			System.out.println(" = " + contenu );
			ArreteEqRef temp = arreteEqRefService.getById(Long.valueOf(id));		//add arreteEqRef to entete foreign key
			contentArreteForm.setArreteEqRef(temp);
			contentArreteForm.setContenu(contenu);
			contentArreteForm.setDateAjout(GlobalHelper.getCurrentDate());
		 
	try {
			listesSaved = contentArreteService.saveOrUpdateContentArreteForm(contentArreteForm);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "redirect:/newArrete/" + listesSaved.getId();		
	}
	

}
