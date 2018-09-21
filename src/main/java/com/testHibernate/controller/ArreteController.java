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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.testHibernate.converts.equivalence.ArreteEqRefFormToArreteEqRef;
import com.testHibernate.converts.equivalence.ArreteEqRefToArreteEqRefForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.equivalence.ArreteEqRefService;

@Controller
public class ArreteController {
	
	 private ArreteEqRefService arreteEqRefService;
	 private ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm;
	 private ListesDiplomeService listesDiplomeService;
	 private ArreteEqRefFormToArreteEqRef arreteEqRefFormToArreteEqRef;
	 
	@Autowired
	public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
		this.listesDiplomeService = listesDiplomeService;
	}
	
	@Autowired
	public void setArreteEqRefFormToArreteEqRef(ArreteEqRefFormToArreteEqRef arreteEqRefFormToArreteEqRef) {
		this.arreteEqRefFormToArreteEqRef = arreteEqRefFormToArreteEqRef;
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

		listesSaved = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
			
		return "redirect:/newArrete/" + listesSaved.getId();		
	}
	//Equivalence
	@PutMapping("/updateArrete/{id}")
	public String updateArrete(@Valid @ModelAttribute ArreteEqRefForm arreteEqRefForm , @PathVariable String id, BindingResult bindingResult, Model model) {
	
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		}
		ArreteEqRef newEntity = arreteEqRefFormToArreteEqRef.convert(arreteEqRefForm);
		ArreteEqRef oldEntity = arreteEqRefService.getById(Long.valueOf(id));
		oldEntity.setListesDiplome(newEntity.getListesDiplome());
		oldEntity.setTitre(newEntity.getTitre());
		oldEntity.setAnneeSortie(newEntity.getAnneeSortie());
		
		
		oldEntity = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
		//int test = arreteEqRefService.update(oldEntity.getId(), newEntity.getListesDiplome().getId(), newEntity.getAnneeSortie(), newEntity.getTitre());
		
		//System.out.println("\n\t TEST :::> "+test);
		return "redirect:/newArrete/" + oldEntity.getId();		
	}
	
	@GetMapping("/newArrete/{id}")
	public String newArrete(@PathVariable String id, Model model){
		try { 
			 
			ArreteEqRef listesSaved = arreteEqRefService.getById(Long.valueOf(id));
			if(listesSaved==null) {
				return "redirect:/error404/listArrete";	
			}
			ArreteEqRefForm arreteEqRefForm = this.arreteEqRefToArreteEqRefForm.convert(listesSaved);
			 
			List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
			List<String> listEcole = listesDiplomeService.getAllEcole();
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
		
			model.addAttribute("arreteEqRef", listesSaved);
			model.addAttribute("annees", annee);
			model.addAttribute("listEcole", listEcole);
			model.addAttribute("listeDiploma", listeDiploma);
			model.addAttribute("arreteEqRefForm", arreteEqRefForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
