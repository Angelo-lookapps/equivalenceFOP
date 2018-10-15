package com.testHibernate.controller;

  
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.testHibernate.converts.cin.CINFormToCIN;
import com.testHibernate.converts.cin.CINToCINForm;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.helpers.Tag;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.service.cin.CINService;
import com.testHibernate.service.historique.ActiviteRecentService;

@Controller
public class CINController {
	 private CINService cinService; 
	 
	 private CINToCINForm cinToCINForm;
	 
	 private List<Tag> data = new ArrayList<Tag>();
	 
	 private List<CIN> cins;
	 
	 private HttpSession session;
	 
	 private CINFormToCIN cinFormToCIN;

	 @Autowired
	 public void setCINFormToCIN(CINFormToCIN cinFormToCIN) {
		this.cinFormToCIN = cinFormToCIN;
	 }
	 
	 GlobalHelper gh = new GlobalHelper();
	 
	 int nombreLigneMax = 5;
	 
	 void setNombreLigneMax(int nombre) {
		 this.nombreLigneMax = nombre;
	 }
	 @Autowired
	 public void setSession(HttpSession session) {
		this.session = session;
	 }
	 
	 private ActiviteRecentService activiteRecentService;
	 
	 @Autowired
	 public void setActiviteRecentService(ActiviteRecentService activiteRecentService) {
		this.activiteRecentService = activiteRecentService;
	 }
	 
	 @Autowired
	 public void setCinToCINForm(CINToCINForm cinToCINForm) {
		this.cinToCINForm = cinToCINForm;
	 }

	 @Autowired
	 public void setCINService(CINService cinService) {
        this.cinService = cinService;
	 }
	 
	 @GetMapping("/cin/show/{id}")
	 public String getCIN(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 CIN listesSaved = cinService.getById(Long.valueOf(id));
		 if(listesSaved==null) {
			 return "redirect:/error404/CINList";	
		 }
		 model.addAttribute("cin", listesSaved);

		 return "pages/enregistrement/showCIN";
		  
		
	 }
	 
	 @GetMapping(value={"/CINList", "/CINList/{deleteError}", "/CINList/page-{page}"})
	 public String listCIN(Model model, @PathVariable(required=false) Optional<String> deleteError,@PathVariable(required=false) Optional<Integer> page){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 initialListeCIN();
		 List<CIN> listCIN = cinService.pagination(1, nombreLigneMax);
			if(page.isPresent()) {
				listCIN = cinService.pagination(page.get(), nombreLigneMax);
			}  
			try {
				Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.cins.size(), nombreLigneMax);
				model.addAttribute("nombrePagination", nombrePagination);
			} catch (Exception e) { 
				e.printStackTrace();
			}
		
		model.addAttribute("listCIN", listCIN);
        if(deleteError.isPresent()) {
    		model.addAttribute("deleteError", deleteError.get());
        }
    	return "pages/enregistrement/CINList";
         
	 }	
	 
	 @GetMapping("cin/edit/{id}")
	 public String edit(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 CIN cin = cinService.getById(Long.valueOf(id));
        if(cin==null) {
			return "redirect:/error404";	
		}
        CINForm cinForm = cinToCINForm.convert(cin);
        
        model.addAttribute("listCIN", cinService.listAll());
        model.addAttribute("cinForm", cinForm);
        model.addAttribute("isEdit", "1");
        
        return "pages/enregistrement/newCIN";
	 
        
	 }
	 
	 @GetMapping({"/newCIN", "/newCIN/page-{page}"})
	 public String ajouterCIN(Model model, @PathVariable(required=false) Optional<Integer> page) {
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 model.addAttribute("listCIN", cinService.listAll());
		 model.addAttribute("cinForm", new CINForm());
		 initialListeCIN();
		 List<CIN> listCIN = cinService.pagination(1, nombreLigneMax);
			if(page.isPresent()) {
				listCIN = cinService.pagination(page.get(), nombreLigneMax);
			}  
			try {
				Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.cins.size(), nombreLigneMax);
				model.addAttribute("nombrePagination", nombrePagination);
			} catch (Exception e) { 
				model.addAttribute("error", e);
	 			return "pages/erreur/505"; 
				//e.printStackTrace();
			}
		 model.addAttribute("listCIN", listCIN);
		 
		 return "pages/enregistrement/newCIN";	
		 
		 	
	 }
	
	 @PostMapping(value = "/saveCIN")
	 public String saveOrUpdateCIN(@Valid  @ModelAttribute CINForm cinForm, @RequestParam(required=false) String id, BindingResult bindingResult, Model model){
		 
		 if(bindingResult.hasErrors()){
			 return "redirect:/error505";
		 }
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	 	
			 	
		 cinForm.setDateAjout(GlobalHelper.getCurrentDate());
		/* CIN temp = cinFormToCIN.convert(cinForm);
		 if(cinForm.getId()!=null) {
			 System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			 System.out.println("cinForm = "+cinForm.getId());
			 temp.setId(cinForm.getId());
		 }*/
		 System.out.println("\n\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		 System.out.println("cinForm = "+cinForm.getId());
		 CIN savedCIN = cinService.saveOrUpdateCINForm(cinForm); 
		 System.out.println("savedCIN = "+savedCIN.getId());
		 //Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Un CIN "+savedCIN.getNom().toUpperCase()+" "+savedCIN.getPrenom()));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique0
		 	
		 return "redirect:cin/show/" + savedCIN.getId();
	 }
	 
	 @PutMapping(value = "/updatesCIN")
	 public String updatesCIN(@Valid  @ModelAttribute CINForm cinForm, BindingResult bindingResult, Model model){
		 
		 if(bindingResult.hasErrors()){
			 return "redirect:/error505";
		 }
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
	
		 CIN savedCIN = cinService.saveOrUpdateCINForm(cinForm);

		 return "redirect:cin/show/" + savedCIN.getId();
	 }
	 
	 @GetMapping("/cin/delete/{id}")
	 public String delete(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 CIN listesSaved = cinService.getById(Long.valueOf(id));
        String deleteError = "";
		try {
        	cinService.delete(Long.valueOf(id));
        }catch(Exception e) {
        	deleteError = "1";
        	
        }
        //Mis en historique
		ActiviteRecent historique = new ActiviteRecent();
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(2, "Le CIN de "+listesSaved.getNom().toUpperCase()+" "+listesSaved.getPrenom()));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	//fin historique
        return "redirect:/CINList/" + deleteError;
	 }
	 
	 @GetMapping(value = "/searchCIN")
	 public @ResponseBody List<Tag> getTags(@RequestParam(required=true) String champ) {
		 //System.out.println("data == "+data.size());
		 return simulateSearchResult(champ);

	 }
	 @GetMapping(value = "/searchCritereCIN")
	 public @ResponseBody List<Tag> getListeDemandeByCriteres(@RequestParam(required=true) String nomCIN, @RequestParam(required=true) String prenom,
				@RequestParam(required=true) String numeroCIN, @RequestParam(required=true) String adresseActuelle, 
				@RequestParam(required=true) String fonction, @RequestParam(required=true) String lieuTravail) { 
		//System.out.println("\n\n nomCIN = "+nomCIN);
		 return this.simulateSearchResultByCritere(nomCIN, prenom,  numeroCIN, adresseActuelle, 
				 fonction, lieuTravail);

	 }
	 private List<Tag> simulateSearchResultByCritere(String nom, String prenom, 
				String numeroCIN, String adresseActuelle, 
				String fonction, String lieuTravail) {
		 List<Tag> result = new ArrayList<Tag>();
		 initialListeCIN();
		 
		 try{ 
			 result =  gh.convertCINToListTag(cinService.searchMultiple(nom, prenom , numeroCIN, 
					 adresseActuelle, fonction, lieuTravail)); 
			  
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return result;
	 }
	 private List<Tag> simulateSearchResult(String tagName) {
		 List<Tag> result = new ArrayList<Tag>();
		 try{
			 initialListeCIN();
			 data =  gh.convertCINToListTag(cins);  
			 //System.out.println("data == "+data.size());
			// iterate a list and filter by tagName
			 for (Tag tag : data) {
				if (tag.getTagName().toUpperCase().contains(tagName.toUpperCase())) {
					result.add(tag);
				}
			}
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return result;
	 }
	 
	 public void initialListeCIN() {
			this.cins = cinService.listAll();
	 }
}
