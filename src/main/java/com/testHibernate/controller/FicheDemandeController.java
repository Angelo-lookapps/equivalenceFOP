package com.testHibernate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.testHibernate.converts.demande.DemandeToDemandeForm;
import com.testHibernate.converts.diplome.DiplomeToDiplomeForm;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.demande.FicheDemandeForm;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;
import com.testHibernate.model.diplome.NiveauDiplome;
import com.testHibernate.service.cin.CINService;
import com.testHibernate.service.demande.FicheDemandeService;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.diplome.NiveauDiplomeService;

@Controller
public class FicheDemandeController {
	
	 ///SERVICES
	 private FicheDemandeService ficheDemandeService;
	 private NiveauDiplomeService niveauDiplomeService;
	 private CINService cinService;
	 private ListesDiplomeService listesDiplomeService;
	 
	 ///CONVERTS
	 private DemandeToDemandeForm demandeToDemandeForm;
	 
	 @Autowired
	 public void setNiveauDiplomeService(NiveauDiplomeService niveauDiplomeService) {
		this.niveauDiplomeService = niveauDiplomeService;
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
 
	 @GetMapping({"/requestList", "/requests"})
	 public String listDemande(Model model){
		List<FicheDemande> ret = ficheDemandeService.listAll();

        model.addAttribute("listeDemande", ret);
       // System.out.println("\n ret.Length = " + ret.size());
        return "pages/enregistrement/requestList";
	 }	
	 
	 @GetMapping("/showRequest/{id}")
	 public String getDemandeById(@PathVariable String id, Model model){
		 model.addAttribute("ficheDemande", ficheDemandeService.getById(Long.valueOf(id)));
		// System.out.println("GEGE");
		 return "pages/enregistrement/showRequest";
	 }

	 @GetMapping("/editRequest/{id}")
	 public String edit(@PathVariable String id, Model model){
        FicheDemande fiche = ficheDemandeService.getById(Long.valueOf(id));
        FicheDemandeForm ficheForm = demandeToDemandeForm.convert(fiche);

		List<FicheDemande> listeDemande = ficheDemandeService.listAll();
      
        model.addAttribute("listeDemande", listeDemande);
        model.addAttribute("ficheForm", ficheForm);
        model.addAttribute("isEdit", "1");
       
        return "pages/enregistrement/newRequest";
	 }
	 
	 @GetMapping("/newRequest")
	 public String ajouterDemande(Model model) {
		 //initial
		 
		 //Get Lists
		 List<FicheDemande> listeDemande = ficheDemandeService.listAll();
		 List<CIN> listCIN = cinService.listAll();
		 List<ListesDiplome> listesDiplome = listesDiplomeService.listAll();
		 List<String> listLieuDelivrance = cinService.getAllLieuDelivrance();
		 List<String> listEcole = listesDiplomeService.getAllEcole();
			
		 //Dispatch
		 model.addAttribute("listesDiplome", listesDiplome);
		 model.addAttribute("listEcole", listEcole);
		 model.addAttribute("listCIN", listCIN);
		 model.addAttribute("listeDemande", listeDemande);
		 model.addAttribute("listLieuDelivrance", listLieuDelivrance);
		 model.addAttribute("ficheDemandeForm", new FicheDemandeForm());
		 
		 return "pages/enregistrement/newRequest";		
	 }
	
	 public List<FicheDemande> getDemandeByCIN(String idCin){
		 return this.ficheDemandeService.getFicheDemandeByCIN(Long.valueOf(idCin));
	 }
	 public List<FicheDemande> getDemandeByDate(String dateRetrait){
		 return this.ficheDemandeService.getFicheDemandeByDate(dateRetrait);
	 }
	 
	 
	 @PostMapping(value = "/saveRequest")
	 public String saveOrUpdateDemande(@Valid  @ModelAttribute FicheDemandeForm ficheDemandeForm, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){
			 return "pages/enregistrement/newRequest";
		 }
		 
		 FicheDemande ficheSaved = ficheDemandeService.saveOrUpdateDemandeForm(ficheDemandeForm);

		 return "redirect:/showRequest/" + ficheSaved.getId();
	 }
	 
	@GetMapping("/request/delete/{id}")
	 public String delete(@PathVariable String id){
		ficheDemandeService.delete(Long.valueOf(id));
        return "redirect:/requestList";
	 }
	

}
