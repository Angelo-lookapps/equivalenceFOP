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
import org.springframework.web.bind.annotation.RequestParam;

import com.testHibernate.converts.diplome.DiplomeToDiplomeForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;
import com.testHibernate.model.diplome.NiveauDiplome;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;
import com.testHibernate.model.equivalence.ContentArrete;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.diplome.NiveauDiplomeService;
import com.testHibernate.service.equivalence.ArreteEqRefService;
import com.testHibernate.service.equivalence.ContentArreteService;
import com.testHibernate.service.historique.ActiviteRecentService;

@Controller
public class DiplomeController {
	 private ListesDiplomeService listesDiplomeService;
	 private NiveauDiplomeService niveauDiplomeService;
	 private DiplomeToDiplomeForm diplomeToDiplomeForm; 
	 private List<ListesDiplome> listeDiplomes;
	 private HttpSession session;
	 int nombreLigneMax = 5;
	 
	 private ArreteEqRefService arreteEqRefService;
	 @Autowired
	 public void setArreteEqRefService(ArreteEqRefService arreteEqRefService) {
		this.arreteEqRefService = arreteEqRefService;
	 }
	 private ContentArreteService contentArreteService;
	 @Autowired
	 public void setContentArreteService(ContentArreteService contentArreteService) {
		this.contentArreteService = contentArreteService;
	 } 
	 
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
	 public void setDiplomeToDiplomeForm(DiplomeToDiplomeForm diplomeToDiplomeForm) {
		this.diplomeToDiplomeForm = diplomeToDiplomeForm;
	 }  
	 
	 @Autowired
	 public void setNiveauDiplomeService(NiveauDiplomeService niveauDiplomeService) {
		this.niveauDiplomeService = niveauDiplomeService;
	 }

	 @Autowired
	 public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
        this.listesDiplomeService = listesDiplomeService;
	 }
	 
	 
	 @GetMapping({"/diplomaList", "/diplomaList/page-{page}", "/diplomaList/newArrete-{idArrete}"})
	 public String listDiplome(@PathVariable(required=false) Optional<Integer> page, @PathVariable(required=false) Optional<Integer> idArrete, Model model){
		
		initialListeDiploma();
		List<ListesDiplome> ret = listesDiplomeService.pagination(1, nombreLigneMax);
		
		if(page.isPresent()) {
			ret = listesDiplomeService.pagination(page.get(), nombreLigneMax);
		} 
		
		try {
			Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.listeDiplomes.size(), nombreLigneMax);
			if(idArrete.isPresent()) {
				 model.addAttribute("newArrete", idArrete.get());
			}
			model.addAttribute("nombrePagination", nombrePagination);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		List<NiveauDiplome> nivaux = niveauDiplomeService.listAll();
		
        model.addAttribute("listesDiplome", ret);
        model.addAttribute("niveaux", nivaux); 
        if(session.getAttribute("isConnected")!=null) {
	    	return "pages/enregistrement/diplomaList";
        }
    	model.addAttribute("errorlogin", "4");
		return "pages/login";
	 }	
	 
	 @GetMapping( "/showDiploma/{id}" )
	 public String getDiploma(@PathVariable String id, Model model){
		 ListesDiplome list = listesDiplomeService.getById(Long.valueOf(id));
		 model.addAttribute("diploma", list);
		 if(list==null) {
			return "redirect:/error404/diplomaList";	
		 }
		 // System.out.println("GEGE"); 
 		 
		 if(session.getAttribute("isConnected")!=null) {
	    	return "pages/enregistrement/showDiploma";
		 }	
		 model.addAttribute("errorlogin", "4");
		 return "pages/login";
	 }

	 @GetMapping("/editDiploma/{id}" )
	 public String edit(@PathVariable String id, Model model){
        ListesDiplome liste = listesDiplomeService.getById(Long.valueOf(id));
        if(liste==null) {
        	return "redirect:/error404";	
        }
        initialListeDiploma();
        List<ListesDiplome> listeDiploma = listesDiplomeService.pagination(1, nombreLigneMax);
		
	
        ListesDiplomeForm listesDiplome = diplomeToDiplomeForm.convert(liste);
 
        List<NiveauDiplome> niveauxDiploma = niveauDiplomeService.listAll();
        model.addAttribute("listNiveauDiploma", niveauxDiploma);
        model.addAttribute("listDiploma", listeDiploma);
        model.addAttribute("listesDiplome", listesDiplome);
        model.addAttribute("isEdit", "1");
        if(session.getAttribute("isConnected")!=null) {
        	return "pages/enregistrement/newDiploma";
		 }	
		 model.addAttribute("errorlogin", "4");
		 return "pages/login";
        
	 }
	
	 @GetMapping({"/newDiploma" ,"/newDiploma/page-{page} "})
	 public String ajouterDiplome(@PathVariable(required=false) Optional<Integer> page , Model model) {
		 //initial
		// Map<String, String> listNiveau = new HashMap<String, String>();
		 
		 //Get Lists 
		 List<NiveauDiplome> niveauxDiploma = niveauDiplomeService.listAll();
		 initialListeDiploma();
		 List<ListesDiplome> listeDiploma = listesDiplomeService.pagination(1, nombreLigneMax);
		 List<Integer> annee = null;
			if(page.isPresent()) {
				listeDiploma = listesDiplomeService.pagination(page.get(), nombreLigneMax);
			}  
			try {
				annee = DateHelper.getAnneeList(1999, 2022);
				Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.listeDiplomes.size(), nombreLigneMax);
				model.addAttribute("nombrePagination", nombrePagination);
			} catch (Exception e) { 
				e.printStackTrace();
			}
		 
		 model.addAttribute("annees", annee);
		 model.addAttribute("listDiploma", listeDiploma);
		 model.addAttribute("listNiveauDiploma", niveauxDiploma);
		 model.addAttribute("listesDiplome", new ListesDiplomeForm());
		 
		
		 if(session.getAttribute("isConnected")!=null) {
			 return "pages/enregistrement/newDiploma";	
		 }	
		 model.addAttribute("errorlogin", "4");
		 return "pages/login";
	 }
	
	
	 public List<NiveauDiplome> getNiveauByCateg(String categ){
		 return this.niveauDiplomeService.findNiveauByCategorie(categ);
	 }
	 
	 @PostMapping(value = "/saveDiploma")
	 public String saveOrUpdateDiploma(@Valid  @ModelAttribute ListesDiplomeForm listesDiplome, @RequestParam String anneeSortie, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){
			 return "redirect:/error505";
			 //return "pages/enregistrement/newDiploma";
		 }
		 ListesDiplome listesSaved = null;
		 ArreteEqRef arrete = null;
		 try{
			  
			 listesSaved = listesDiplomeService.saveOrUpdateListesDiplomeForm(listesDiplome);
			 
			 //Mis en historique
			 ActiviteRecent historique = new ActiviteRecent(); 
			 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Un diplome \""+listesSaved.getFiliere())+"\"");
			 	historique.setDateAjout(GlobalHelper.getCurrentDate());
			 	activiteRecentService.saveOrUpdate(historique);
		 	 //fin historique
		 	if(!anneeSortie.equals("vide")) { 
				 arrete = this.insertArreteLink(listesSaved, anneeSortie);
		 	}
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 if(arrete!=null) {
			 return "redirect:/diplomaList/newArrete-"+arrete.getId(); 
		 }
		 return "redirect:/diplomaList" ;
		
	 }
	 
	 @PutMapping(value = "/updateDiploma")
	 public String updateDiploma(@Valid  @ModelAttribute ListesDiplomeForm listesDiplome, @PathVariable String id, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){
			 return "redirect:/error505";
		 }
		 
		 ListesDiplome listesSaved = listesDiplomeService.saveOrUpdateListesDiplomeForm(listesDiplome);
		//Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(3, "Un diplome \""+listesSaved.getFiliere()+" "+listesSaved.getOption()+"\""));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique
		 return "redirect:/showDiploma/" + listesSaved.getId();
	 }
	 
	@GetMapping("/diploma/delete/{id}")
	 public String delete(@PathVariable String id){
		ListesDiplome listesSaved = listesDiplomeService.getById(Long.valueOf(id));
		listesDiplomeService.delete(Long.valueOf(id));
		//Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(2, "Le diplome \""+listesSaved.getFiliere()+" "+listesSaved.getOption()+"\""));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique
        return "redirect:/diplomaList";
	 }
	
	public void initialListeDiploma() {
			this.listeDiplomes = listesDiplomeService.listAll();
	}
	 
	public ArreteEqRef insertArreteLink(ListesDiplome listeDiplomeTemp, String anneeSortie) throws Exception {
		ArreteEqRef ret = null;
		ListesDiplome listeDiplome = listeDiplomeTemp; 
		ArreteEqRefForm arreteEqRefForm = new ArreteEqRefForm();
		if(listeDiplome==null) {
			throw new Exception("Error : listeDiplomeTemp is invalid line 246!!");
		}
		try {  
			arreteEqRefForm.setListesDiplome(listeDiplome);
			arreteEqRefForm.setAnneeSortie(anneeSortie);
			arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate());
			arreteEqRefForm.setTitre(listeDiplome.getEcole()+" - "+listeDiplome.getFiliere()+" - "+listeDiplome.getOption());
			arreteEqRefForm.setStatus(false);
			
			ret = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
			
			//Mis en historique
			 ActiviteRecent historique = new ActiviteRecent(); 
			 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "une nouvelle arrêté avec comme titre : \""+ret.getTitre()+" année sortie: "+ret.getAnneeSortie()+"\""));
			 	historique.setDateAjout(GlobalHelper.getCurrentDate());
			 	activiteRecentService.saveOrUpdate(historique);
		 	 //fin historique
		 	
		//initialisation ContentArrete
		ContentArrete content = new ContentArrete(); 
		content.setArreteEqRef(ret);
		content.setDateAjout(GlobalHelper.getCurrentDate());
		ContentArrete temp = this.contentArreteService.saveOrUpdate(content);
		arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate());
		
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	
	

}
