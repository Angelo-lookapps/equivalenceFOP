package com.testHibernate.controller;

import java.util.Date;
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
import com.testHibernate.model.equivalence.InfoArrete;
import com.testHibernate.model.equivalence.InfoArreteForm;
import com.testHibernate.model.equivalence.TypeArreteJasper;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.diplome.NiveauDiplomeService;
import com.testHibernate.service.equivalence.ArreteEqRefService;
import com.testHibernate.service.equivalence.ContentArreteService;
import com.testHibernate.service.equivalence.InfoArreteService;
import com.testHibernate.service.equivalence.TypeArreteJasperService;
import com.testHibernate.service.historique.ActiviteRecentService;
  
@Controller
public class DiplomeController {
	 private ListesDiplomeService listesDiplomeService;
	 private NiveauDiplomeService niveauDiplomeService;
	 private DiplomeToDiplomeForm diplomeToDiplomeForm; 
	 private List<ListesDiplome> listeDiplomes;
	 private HttpSession session;
	 int nombreLigneMax = 10;
	 
	 private ArreteEqRefService arreteEqRefService;
	 private InfoArreteService infoArreteService;
	 private TypeArreteJasperService typeArreteService;
	 
	 @Autowired
	 public void setInfoArreteService(InfoArreteService infoArreteService) {
		this.infoArreteService = infoArreteService;
	 }
	 
	 @Autowired
	 public void setArreteEqRefService(ArreteEqRefService arreteEqRefService) {
		this.arreteEqRefService = arreteEqRefService;
	 }
	 @Autowired
	 public void setTypeArreteJasperService(TypeArreteJasperService typeArreteService) {
		this.typeArreteService = typeArreteService;
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
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
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
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
		//	e.printStackTrace();
		}
		
		List<NiveauDiplome> nivaux = niveauDiplomeService.listAll();
		
        model.addAttribute("listesDiplome", ret);
        model.addAttribute("niveaux", nivaux); 
	       
        return "pages/enregistrement/diplomaList";
        
	 }	
	 
	 @GetMapping( "/showDiploma/{id}" )
	 public String getDiploma(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 ListesDiplome list = listesDiplomeService.getById(Long.valueOf(id));
		 model.addAttribute("diploma", list);
		 if(list==null) {
			return "redirect:/error404/diplomaList";	
		 }
		 // System.out.println("GEGE"); 
 		 
		 return "pages/enregistrement/showDiploma";
		 
	 }

	 @GetMapping("/editDiploma/{id}" )
	 public String edit(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 ListesDiplome liste = listesDiplomeService.getById(Long.valueOf(id));
        if(liste==null) {
        	return "redirect:/error404";	
        }
        initialListeDiploma();
        List<ListesDiplome> listeDiploma = listesDiplomeService.pagination(1, nombreLigneMax);
   	 	List<Integer> annee = null;
		 List<TypeArreteJasper> typesArrete = null;
		 typesArrete = typeArreteService.listAll();
   	 	try {
			annee = DateHelper.getAnneeList(1999, 2022);
		} catch (Exception e) { 
			//e.printStackTrace();
		}
        ListesDiplomeForm listesDiplome = diplomeToDiplomeForm.convert(liste);
 
        List<NiveauDiplome> niveauxDiploma = niveauDiplomeService.listAll();
        
        model.addAttribute("annees", annee);
        model.addAttribute("listNiveauDiploma", niveauxDiploma);
        model.addAttribute("listDiploma", listeDiploma);
        model.addAttribute("typesArrete", typesArrete);
        model.addAttribute("listesDiplome", listesDiplome);
        model.addAttribute("isEdit", "1");
        
        return "pages/enregistrement/newDiploma";
		  
        
	 }
	
	 @GetMapping({"/newDiploma" ,"/newDiploma/page-{page} "})
	 public String ajouterDiplome(@PathVariable(required=false) Optional<Integer> page , Model model) {
		 //initial
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 // Map<String, String> listNiveau = new HashMap<String, String>();
		 
		 //Get Lists 
		 List<NiveauDiplome> niveauxDiploma = niveauDiplomeService.listAll();
		 initialListeDiploma();
		 List<ListesDiplome> listeDiploma = listesDiplomeService.pagination(1, nombreLigneMax);
		 List<Integer> annee = null;
		 List<TypeArreteJasper> typesArrete = null;
		 typesArrete = typeArreteService.listAll();
			if(page.isPresent()) {
				listeDiploma = listesDiplomeService.pagination(page.get(), nombreLigneMax);
			}  
			try {
				annee = DateHelper.getAnneeList(1999, 2022);
				Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.listeDiplomes.size(), nombreLigneMax);
				model.addAttribute("nombrePagination", nombrePagination);
			} catch (Exception e) { 
				model.addAttribute("error", e);
	 			return "pages/erreur/505"; 
				//e.printStackTrace();
			}
		 
		 model.addAttribute("annees", annee);
		 model.addAttribute("listDiploma", listeDiploma);
		 model.addAttribute("typesArrete", typesArrete);
		 model.addAttribute("listNiveauDiploma", niveauxDiploma);
		 model.addAttribute("listesDiplome", new ListesDiplomeForm());
 
		 
		 return "pages/enregistrement/newDiploma";	
		 
	 }
	
	
	 public List<NiveauDiplome> getNiveauByCateg(String categ){
		 return this.niveauDiplomeService.findNiveauByCategorie(categ);
	 }
	 
	 @PostMapping(value = "/saveDiploma")
	 public String saveOrUpdateDiploma(@Valid  @ModelAttribute ListesDiplomeForm listesDiplome, @RequestParam(required=false) String anneeSortie, @RequestParam(required=false) String typeArrete, BindingResult bindingResult, Model model){
		 
		 if(bindingResult.hasErrors()){
			 return "redirect:/error505";
			 //return "pages/enregistrement/newDiploma";
		 }
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 ListesDiplome listesSaved = null;
		 ArreteEqRef arrete = null;
		 InfoArrete infoArrete = null;
		 
		 try{ 
			 listesDiplome.setDateAjout(GlobalHelper.getCurrentDate());
			 listesSaved = listesDiplomeService.saveOrUpdateListesDiplomeForm(listesDiplome);
			 
			 //Mis en historique
			 ActiviteRecent historique = new ActiviteRecent(); 
			 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Un diplome \""+listesSaved.getFiliere())+"\"");
			 	historique.setDateAjout(GlobalHelper.getCurrentDate());
			 	activiteRecentService.saveOrUpdate(historique);
		 	 //fin historique
		 	if(anneeSortie!=null && !anneeSortie.equals("vide")) { 
				 arrete = this.insertArreteLink(listesSaved, anneeSortie, typeArrete);
			 	 infoArrete = GlobalHelper.getInitialInfoArrete(null, arrete);
			 	 infoArrete = infoArreteService.saveOrUpdate(infoArrete);
		 	}
		 	
		 }catch(Exception e) {
				model.addAttribute("error", e);
	 			return "pages/erreur/505"; 
			// e.printStackTrace();
		 }
		 if(arrete!=null) {
			 return "redirect:/diplomaList/newArrete-"+arrete.getId(); 
		 }
		 return "redirect:/diplomaList" ;
		
	 }
	 
	 @PutMapping(value = "/updateDiploma")
	 public String updateDiploma(@Valid  @ModelAttribute ListesDiplomeForm listesDiplome, @PathVariable String id, BindingResult bindingResult, Model model ){
		 
		 if(bindingResult.hasErrors()){
			 return "redirect:/error505";
		 }
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
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
	 public String delete(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
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
	 
	
	public ArreteEqRef insertArreteLink(ListesDiplome listeDiplomeTemp, String anneeSortie, String typeArrete) throws Exception {
		ArreteEqRef ret = null;
		ListesDiplome listeDiplome = listeDiplomeTemp; 
		ArreteEqRefForm arreteEqRefForm = new ArreteEqRefForm();
		TypeArreteJasper type = null;
		if(listeDiplome==null) {
			throw new Exception("Error : listeDiplomeTemp is invalid !!");
		}
		try {  
			arreteEqRefForm.setListesDiplome(listeDiplome);
			arreteEqRefForm.setAnneeSortie(anneeSortie);
			type = typeArreteService.getById(Long.valueOf(typeArrete));
			arreteEqRefForm.setTypeArreteJasper(type);
			arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate());
			arreteEqRefForm.setTitre(listeDiplome.getEcole()+" - "+listeDiplome.getFiliere()+" - "+listeDiplome.getOption());
			arreteEqRefForm.setStatus(false); //status si l'arrete a été mise à jour.
			
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
		content.setContenu(GlobalHelper._ArticleContent+" "+GlobalHelper._contentChamp);
		content.setDateAjout(GlobalHelper.getCurrentDate());
		ContentArrete temp = this.contentArreteService.saveOrUpdate(content);
		arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate());
		
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	
	

}
