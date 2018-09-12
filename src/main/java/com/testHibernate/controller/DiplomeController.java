package com.testHibernate.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.testHibernate.converts.diplome.DiplomeToDiplomeForm;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;
import com.testHibernate.service.diplome.ListesDiplomeService;

@Controller
public class DiplomeController {
	 private ListesDiplomeService listesDiplomeService;
	 
	 private DiplomeToDiplomeForm diplomeToDiplomeForm;
	 
	 @Autowired
	 public void setDiplomeToDiplomeForm(DiplomeToDiplomeForm diplomeToDiplomeForm) {
		this.diplomeToDiplomeForm = diplomeToDiplomeForm;
	 }

	 @Autowired
	 public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
        this.listesDiplomeService = listesDiplomeService;
	 }
	 
	 @GetMapping({"/diplomaList", "/diplomes"})
	 public String listDiplome(Model model){
        model.addAttribute("listesDiplome", listesDiplomeService.listAll());
        return "pages/enregistrement/diplomaList";
	 }	
	 
	 
	 @GetMapping("/diploma/show/{id}")
	 public String getDiploma(@PathVariable String id, Model model){
		 model.addAttribute("diploma", listesDiplomeService.getById(Long.valueOf(id)));
		 System.out.println("GEGE");
		 return "pages/enregistrement/showDiploma";
	 }

	 @GetMapping("diploma/edit/{id}")
	 public String edit(@PathVariable String id, Model model){
        ListesDiplome cin = listesDiplomeService.getById(Long.valueOf(id));
        ListesDiplomeForm listesDiplomeForm = diplomeToDiplomeForm.convert(cin);

        model.addAttribute("listesDiplomeForm", listesDiplomeForm);
        model.addAttribute("isEdit", "1");
        return "pages/enregistrement/newDiploma";
	 }
	
	 @GetMapping("/newDiploma")
	 public String ajouterDiplome(Model model) {
		 model.addAttribute("listDiploma", listesDiplomeService.listAll());
		 String[] niveaux = {"I","II","III","IV","V","VI","VII","VIII","IX","X"}; 
		 model.addAttribute("niveaux", niveaux);
		 model.addAttribute("listNiveauDiploma", listesDiplomeService.listAllNiveau());
		 model.addAttribute("listesDiplome", new ListesDiplomeForm());
		 return "pages/enregistrement/newDiploma";		
	 }
	
	 @PostMapping(value = "/saveDiploma")
	 public String saveOrUpdateDiploma(@Valid  @ModelAttribute ListesDiplomeForm listesDiplome, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){
			 return "pages/enregistrement/newDiploma";
		 }
	
		 ListesDiplome listesSaved = listesDiplomeService.saveOrUpdateListesDiplomeForm(listesDiplome);

		 return "redirect:diploma/show/" + listesSaved.getId();
	 }
	 
	@GetMapping("/diploma/delete/{id}")
	 public String delete(@PathVariable String id){
		listesDiplomeService.delete(Long.valueOf(id));
        return "redirect:/diplomaList";
	 }
	

}
