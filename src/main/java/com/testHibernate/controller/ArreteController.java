package com.testHibernate.controller;
 
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.testHibernate.converts.equivalence.ArreteEqRefFormToArreteEqRef;
import com.testHibernate.converts.equivalence.ArreteEqRefToArreteEqRefForm;
import com.testHibernate.converts.equivalence.EnteteArreteFormToEnteteArrete;
import com.testHibernate.converts.equivalence.EnteteArreteToEnteteArreteForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;
import com.testHibernate.model.diplome.NiveauDiplome;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;
import com.testHibernate.model.equivalence.EnteteArrete;
import com.testHibernate.model.equivalence.EnteteArreteForm;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.equivalence.ArreteEqRefService;
import com.testHibernate.service.equivalence.EnteteArreteService;

@Controller
public class ArreteController {
	
	 private ArreteEqRefService arreteEqRefService;
	 private ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm;
	 private ListesDiplomeService listesDiplomeService;
	 private ArreteEqRefFormToArreteEqRef arreteEqRefFormToArreteEqRef;
	 private EnteteArreteFormToEnteteArrete enteteArreteFormToEnteteArrete;
	 private EnteteArreteService enteteArreteService;
	 private EnteteArreteToEnteteArreteForm enteteArreteToEnteteArreteForm;
	 
	@Autowired
	public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
		this.listesDiplomeService = listesDiplomeService;
	}
	
	@Autowired
	public void setEnteteArreteToEnteteArreteForm(EnteteArreteToEnteteArreteForm enteteArreteToEnteteArreteForm) {
		this.enteteArreteToEnteteArreteForm = enteteArreteToEnteteArreteForm;
	}
	
	@Autowired
	public void setEnteteArreteService(EnteteArreteService enteteArreteService) {
		this.enteteArreteService = enteteArreteService;
	}
	
	@Autowired
	public void setEnteteArreteFormToEnteteArrete(EnteteArreteFormToEnteteArrete enteteArreteFormToEnteteArrete) {
		this.enteteArreteFormToEnteteArrete = enteteArreteFormToEnteteArrete;
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
		/*ArreteEqRef newEntity = arreteEqRefFormToArreteEqRef.convert(arreteEqRefForm);
		ArreteEqRef updateEntity = arreteEqRefService.getById(Long.valueOf(id));
		updateEntity.setListesDiplome(newEntity.getListesDiplome());
		updateEntity.setTitre(newEntity.getTitre());
		updateEntity.setAnneeSortie(newEntity.getAnneeSortie());*/
		ArreteEqRef updateEntity = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
		return "redirect:/newArrete/" + updateEntity.getId();		
	}
	
	@GetMapping("/newArrete/{id}")
	public String newArrete(@PathVariable String id, Model model){
		try { 
			 
			ArreteEqRef listesSaved = arreteEqRefService.getById(Long.valueOf(id));
			if(listesSaved==null) {
				return "redirect:/error404/listArrete";	
			}
			ArreteEqRefForm arreteEqRefForm = listesSaved!=null ? this.arreteEqRefToArreteEqRefForm.convert(listesSaved) : new ArreteEqRefForm();
			 
		//	System.out.println("\n\n ArreteEq Ref FOrm = "+arreteEqRefForm.getTitre());
			
			
			//EnteteArrete enteteArrete = this.enteteArreteService.getEnteteByIdArreteEqRef(id);
		//	EnteteArreteForm enteteArreteForm = enteteArrete!=null ? this.enteteArreteToEnteteArreteForm.convert(enteteArrete) : new EnteteArreteForm();
			
		
			
			List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
			List<String> listEcole = listesDiplomeService.getAllEcole();
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
		
			model.addAttribute("arreteEqRef", listesSaved);
			model.addAttribute("annees", annee);
			model.addAttribute("listEcole", listEcole);
			model.addAttribute("listeDiploma", listeDiploma);
			model.addAttribute("arreteEqRefForm", arreteEqRefForm);
			model.addAttribute("idArrete", arreteEqRefForm!=null ? arreteEqRefForm.getId().toString() : "");
			model.addAttribute("enteteArreteForm", new EnteteArreteForm());
			
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
	/*@PostMapping("/saveEntete", consumes = MediaType.TEXT_HTML_VALUE)
	   public @ResponseBody String saveSong(@Valid  @ModelAttribute EnteteArreteForm enteteArreteForm, @RequestBody String body, BindingResult bindingResult, Model model){
	       //ResponseModel response = new ResponseModel();
	       
	        
	       return response;
	   }*/
	@PostMapping("/saveEntete/{id}")
	public String enteteArrete(@PathVariable String id, @Valid  @ModelAttribute EnteteArreteForm enteteArreteForm , BindingResult bindingResult, Model model) {
		EnteteArrete listesSaved = null;
		if(bindingResult.hasErrors()){
			return "/error505";
			//return "pages/equivalence/listArrete";
		}
		System.out.println("\n idRef = " + id);
				ArreteEqRef temp = arreteEqRefService.getById(Long.valueOf(id));		//add arreteEqRef to entete foreign key
		enteteArreteForm.setArreteEqRef(temp);
		System.out.println("\n LOGO = " + enteteArreteForm.getTitreDroite());
		System.out.println("\n LOGO = " + enteteArreteForm.getTitreGauche());
		try {
		
			listesSaved = enteteArreteService.saveOrUpdateEnteteArreteForm(enteteArreteForm);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "redirect:/newArrete/" + listesSaved.getId();		
	}
	

}
