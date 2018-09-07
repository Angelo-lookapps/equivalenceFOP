package com.testHibernate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.testHibernate.model.Product;
import com.testHibernate.model.ProductForm;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.service.CINService;
import com.testHibernate.service.ProductService;

@Controller
public class EnregistrementController {
	 private CINService cinService;
	 
	 @Autowired
	 public void setProductService(CINService cinService) {
        this.cinService = cinService;
	 }
	 
	 @RequestMapping({"/CINList", "/CIN"})
	 public String listCIN(Model model){
        model.addAttribute("listCIN", cinService.listAll());
        return "pages/enregistrement/CINList";
	 }	
	 @GetMapping("/newCIN")
	 public String ajouterCIN() {
		 return "pages/enregistrement/newCIN";		
	 }
	 @RequestMapping(value = "/saveCIN", method = RequestMethod.POST)
	 public String saveOrUpdateCIN(@Valid CIN cin, BindingResult bindingResult){

		 CIN savedCIN = cinService.saveOrUpdate(cin);

		 return "redirect:enregistrement/CIN/show/" + savedCIN.getId();
	 }
	

}
