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

import com.testHibernate.converts.cin.CINToCINForm;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;
import com.testHibernate.service.cin.CINService;

@Controller
public class CINController {
	 private CINService cinService;
	 
	 private CINToCINForm cinToCINForm;

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
			 return "redirect:/error404";	
		 }
		 model.addAttribute("cin", listesSaved);
		 
		 return "pages/enregistrement/showCIN";
	 }
	 
	 @GetMapping({"/CINList", "/CIN"})
	 public String listCIN(Model model){
        model.addAttribute("listCIN", cinService.listAll());
        return "pages/enregistrement/CINList";
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
        return "pages/enregistrement/newCIN";
	 }
	 
	 @GetMapping("/newCIN")
	 public String ajouterCIN(Model model) {
		 model.addAttribute("listCIN", cinService.listAll());
		 model.addAttribute("cinForm", new CINForm());
		 return "pages/enregistrement/newCIN";		
	 }
	
	 @PostMapping(value = "/saveCIN")
	 public String saveOrUpdateCIN(@Valid  @ModelAttribute CINForm cinForm, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){
			 return "redirect:/error505";
			 //return "pages/enregistrement/newCIN";
		 }
	
		 CIN savedCIN = cinService.saveOrUpdateCINForm(cinForm);

		 return "redirect:cin/show/" + savedCIN.getId();
	 }
	 
	 @GetMapping("/cin/delete/{id}")
	 public String delete(@PathVariable String id){
        cinService.delete(Long.valueOf(id));
        return "redirect:/CINList";
	 }
}
