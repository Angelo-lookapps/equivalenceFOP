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

import com.testHibernate.converts.equivalence.ArreteEqRefFormToArreteEqRef;
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
	 private ArreteEqRefFormToArreteEqRef arreteEqRefFormToArreteEqRef;
	 
	 private ListesDiplomeService listesDiplomeService;
	 
	 private ContentArreteService contentArreteService;
	 
	 @Autowired
	 public void setArreteEqRefFormToArreteEqRef(ArreteEqRefFormToArreteEqRef arreteEqRefFormToArreteEqRef) {
		this.arreteEqRefFormToArreteEqRef = arreteEqRefFormToArreteEqRef;
	 }
	 
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
		try {
			
		
		//initialisation ContentArrete
		ContentArrete content = new ContentArrete();
		System.out.println("\n\n\n IS WORKING ??????????????");
		System.out.println(" arreteEqRefForm === "+arreteEqRefForm.getTitre());
		content.setArreteEqRef(arreteEqRefFormToArreteEqRef.convert(arreteEqRefForm));
		content.setDateAjout(GlobalHelper.getCurrentDate());
		ContentArrete temp = this.contentArreteService.saveOrUpdate(content);
		System.out.println("\n\n\n IS WORKING ??????????????");
		arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate());
		listesSaved = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/newArrete/" + listesSaved.getId();		
	}
	//Equivalence
	@PutMapping("/updateArrete/{id}")
	public String updateArrete(@Valid @ModelAttribute ArreteEqRefForm arreteEqRefForm , @PathVariable String id, BindingResult bindingResult, Model model) {
		ArreteEqRef updateEntity = null;
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		}
		try{ 
			arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate()); 
			updateEntity= arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return "redirect:/newArrete/" + updateEntity.getId();		
	}
	
	@GetMapping("/newArrete/{id}")
	public String newArrete(@PathVariable String id, Model model){
		try { 
			 
			ArreteEqRef listesSaved = arreteEqRefService.getById(Long.valueOf(id));
			ContentArrete contentArrete = contentArreteService.getContentByArrete(Long.valueOf(id))!=null ? contentArreteService.getContentByArrete(Long.valueOf(id)) : null;
			System.out.println("\n\n\n contentArrete = "+contentArrete.getContenu());
			
			if(listesSaved==null) {
				return "redirect:/error404/listArrete";	
			}
			ArreteEqRefForm arreteEqRefForm = listesSaved!=null ? this.arreteEqRefToArreteEqRefForm.convert(listesSaved) : new ArreteEqRefForm();
			 	
			List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
			List<String> listEcole = listesDiplomeService.getAllEcole();
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
			String contentArticle = GlobalHelper._ArticleContent;
			
			model.addAttribute("arreteEqRef", listesSaved);
			model.addAttribute("annees", annee);
			model.addAttribute("listEcole", listEcole);
			model.addAttribute("listeDiploma", listeDiploma);
			model.addAttribute("arreteEqRefForm", arreteEqRefForm);
			model.addAttribute("idArrete", arreteEqRefForm!=null ? arreteEqRefForm.getId().toString() : "");
			 
			model.addAttribute("contentArticle", contentArticle);
			model.addAttribute("contentArrete",  contentArrete);
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
						
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "redirect:/newArrete/" + id ;		
	}
	

}
