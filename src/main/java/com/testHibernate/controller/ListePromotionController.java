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

import com.testHibernate.converts.listePromotion.ListePromotionToListePromotionForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;
import com.testHibernate.model.listePromotion.ListePromotionForm;
import com.testHibernate.service.diplome.ListesDiplomeService;
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
		@SuppressWarnings("unused")
		ListePromotion listesSaved = null;
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		}
		listesSaved = listePromotionService.saveOrUpdateListePromotionForm(listePromotionForm);
			
		return "redirect:/listProm/";		
	}
	@PostMapping("/saveAdmis/{id}")
	public String ajoutAdmis(@PathVariable String id,@Valid  @ModelAttribute ListePromotionDetailForm listePromotionDetailForm , BindingResult bindingResult, Model model) {
		@SuppressWarnings("unused")
		ListePromotionDetail listesSaved = null;
		ListePromotion listePromotion1 = listePromotionService.getById(Long.valueOf(id));
		if(listePromotion1==null) {
			return "redirect:/error404/listProm";	
		}
		if(bindingResult.hasErrors()){
			return "redirect:/error505";
			//return "pages/equivalence/listArrete";
		}
		listePromotionDetailForm.setListePromotion(listePromotion1);
		listesSaved = listePromotionDetailService.saveOrUpdateListePromotionDetailForm(listePromotionDetailForm);
		
		System.out.println("\n\n Hello : "+listesSaved.getNumeroMatricule());	
		return "redirect:/showPromoDetail/"+listePromotion1.getId();		
	}
	@GetMapping("/showPromoDetail/{id}")
	public String ajoutPromo(@PathVariable String id, Model model) {
		List<ListePromotionDetail> listePromotionDetails = listePromotionDetailService.getDetailByIdListePromotion(Long.valueOf(id));
		ListePromotion listePromotion = listePromotionService.getById(Long.valueOf(id));
		List<String> mentions = GlobalHelper.getMentionList();
		
		if(listePromotion==null) {
			return "redirect:/error404/listProm";	
		}
		
		model.addAttribute("mentions", mentions);
		model.addAttribute("listePromotion", listePromotion);
		model.addAttribute("listePromotionDetails", listePromotionDetails);
		model.addAttribute("listePromotionDetailForm", new ListePromotionDetailForm());
		return "pages/listePromotion/listPromDet";		
	}
	

}
