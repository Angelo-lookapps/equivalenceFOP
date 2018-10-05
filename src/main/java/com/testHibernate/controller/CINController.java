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

import com.testHibernate.converts.cin.CINToCINForm;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.service.cin.CINService;
import com.testHibernate.service.historique.ActiviteRecentService;

@Controller
public class CINController {
	 private CINService cinService; 
	 
	 private CINToCINForm cinToCINForm;
	 
	 private List<CIN> cins;
	 
	 private HttpSession session;
	 
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
		 CIN listesSaved = cinService.getById(Long.valueOf(id));
		 if(listesSaved==null) {
			 return "redirect:/error404/CINList";	
		 }
		 model.addAttribute("cin", listesSaved);
		 if(session.getAttribute("isConnected")!=null) {
			 return "pages/enregistrement/showCIN";
		 }	
		 model.addAttribute("errorlogin", "4");
		 return "pages/login";
		
	 }
	 
	 @GetMapping(value={"/CINList", "/CINList/{deleteError}", "/CINList/page-{page}"})
	 public String listCIN(Model model, @PathVariable(required=false) Optional<String> deleteError,@PathVariable(required=false) Optional<Integer> page){
		 
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
        if(session.getAttribute("isConnected")!=null) {
        	if(deleteError.isPresent()) {
        		model.addAttribute("deleteError", deleteError.get());
            }
        	return "pages/enregistrement/CINList";
        }
    	
    	model.addAttribute("errorlogin", "4");
		return "pages/login";
	 }	
	 
	 @GetMapping("cin/edit/{id}")
	 public String edit(@PathVariable String id, Model model){
        CIN cin = cinService.getById(Long.valueOf(id));
        if(cin==null) {
			return "redirect:/error404";	
		}
        CINForm cinForm = cinToCINForm.convert(cin);
        
        model.addAttribute("listCIN", cinService.listAll());
        model.addAttribute("cinForm", cinForm);
        model.addAttribute("isEdit", "1");
        
        if(session.getAttribute("isConnected")!=null) {
        	return "pages/enregistrement/newCIN";
		}	
		model.addAttribute("errorlogin", "4");
		return "pages/login";
        
	 }
	 
	 @GetMapping("/newCIN")
	 public String ajouterCIN(Model model) {
		 model.addAttribute("listCIN", cinService.listAll());
		 model.addAttribute("cinForm", new CINForm());
		 
		 if(session.getAttribute("isConnected")!=null) {
			 return "pages/enregistrement/newCIN";	
		 }	
		 model.addAttribute("errorlogin", "4");
		 return "pages/login";
		 	
	 }
	
	 @PostMapping(value = "/saveCIN")
	 public String saveOrUpdateCIN(@Valid  @ModelAttribute CINForm cinForm, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){
			 return "redirect:/error505";
		 } 	
			 	
		 cinForm.setDateAjout(GlobalHelper.getCurrentDate());
		 CIN savedCIN = cinService.saveOrUpdateCINForm(cinForm); 
		 //Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Un CIN "+savedCIN.getNom().toUpperCase()+" "+savedCIN.getPrenom()));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique0
		 	
		 return "redirect:cin/show/" + savedCIN.getId();
	 }
	 
	 @PutMapping(value = "/updatesCIN")
	 public String updatesCIN(@Valid  @ModelAttribute CINForm cinForm, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){
			 return "redirect:/error505";
		 }
	
		 CIN savedCIN = cinService.saveOrUpdateCINForm(cinForm);

		 return "redirect:cin/show/" + savedCIN.getId();
	 }
	 
	 @GetMapping("/cin/delete/{id}")
	 public String delete(@PathVariable String id, Model model){
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
	 
	 public void initialListeCIN() {
		if(this.cins==null){
			this.cins = cinService.listAll();
		}
	 }
}
