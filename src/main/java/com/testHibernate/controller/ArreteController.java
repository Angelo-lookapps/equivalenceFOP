package com.testHibernate.controller;
 
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.testHibernate.converts.equivalence.ArreteEqRefToArreteEqRefForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.equivalence.ArreteEqRefService;

@Controller
public class ArreteController {
	
	 private ArreteEqRefService arreteEqRefService;
	 private ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm;
	 private ListesDiplomeService listesDiplomeService;
	
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
	@GetMapping("/saveArrete")
	public String ajoutArrete(@Valid  @ModelAttribute ArreteEqRefForm arreteEqRefForm, BindingResult bindingResult) {
		 if(bindingResult.hasErrors()){
			 return "pages/equivalence/listArrete";
		 }

		 ArreteEqRef listesSaved = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);

		return "redirect:/newArrete/" + listesSaved.getId();		
	}
	
	@GetMapping("/newArrete/{id}")
	public String newArrete(@PathVariable String id, Model model){
		 model.addAttribute("arreteEqRef", arreteEqRefService.getById(Long.valueOf(id)));
		
		 return "pages/equivalence/newArrete";	
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
		
		return "pages/equivalence/listArrete";		
	}
	

}
