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

import com.testHibernate.converts.demande.DemandeToDemandeForm;
import com.testHibernate.converts.listePromotion.ListePromotionToListePromotionForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.model.cin.CIN; 
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionForm;
import com.testHibernate.service.cin.CINService; 
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.diplome.NiveauDiplomeService;
import com.testHibernate.service.listePromotion.*;

@Controller
public class ListePromotionController {
	
	 ///SERVICES
	 private ListePromotionService listePromotionService;
	 private ListePromotionDetailService listePromotionDetailService;
	 
	 private ListesDiplomeService listesDiplomeService;
	 
	 ///CONVERTS
	 private ListePromotionToListePromotionForm tousListeDiplomeToTousListeDiplomeForm;
	 
	 
	 @Autowired
	 public void setListePromotionService(ListePromotionService tousListeDiplomeService) {
		this.listePromotionService = tousListeDiplomeService;
	 }

	 @Autowired
	 public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
		this.listesDiplomeService = listesDiplomeService;
	 }	

	@Autowired
	 public void setListesDiplomeDetailService(ListePromotionDetailService listesDiplomeDetailService) {
		 this.listePromotionDetailService = listesDiplomeDetailService;
	 }
	 
	 @Autowired
	 public void setTousListeDiplomeToTousListeDiplomeForm(ListePromotionToListePromotionForm tousListeDiplomeToTousListeDiplomeForm) {
		 this.tousListeDiplomeToTousListeDiplomeForm = tousListeDiplomeToTousListeDiplomeForm;
	 }

	@GetMapping({"/listProm", "/proms"})
	 public String listPromo(Model model){
		List<ListePromotion> ret = this.listePromotionService.listAll();
		try {
			List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
			List<String> listEcole = listesDiplomeService.getAllEcole();
			
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
			model.addAttribute("annees", annee);
			model.addAttribute("listEcole", listEcole);
			model.addAttribute("listeDiploma", listeDiploma);
		    model.addAttribute("promos", ret);
			model.addAttribute("listPromotionForm", new ListePromotionForm());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    
       // System.out.println("\n ret.Length = " + ret.size());
        return "pages/listePromotion/listProm";
	 }	
	
	@PostMapping("/savePromo")
	public String ajoutPromo(@Valid  @ModelAttribute ListePromotionForm listePromotionForm , BindingResult bindingResult, Model model) {
		ListePromotion listesSaved = null;
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		}
		listesSaved = listePromotionService.saveOrUpdateListePromotionForm(listePromotionForm);
			
		return "redirect:/listProm/";		
	}
	

}
