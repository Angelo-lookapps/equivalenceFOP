package com.testHibernate.controller;
 
import java.util.ArrayList;
import java.util.List;

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

import com.testHibernate.converts.demande.DemandeToDemandeForm;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.helpers.Tag;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.demande.FicheDemandeForm;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.service.cin.CINService;
import com.testHibernate.service.demande.FicheDemandeService;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.diplome.NiveauDiplomeService;
import com.testHibernate.service.historique.ActiviteRecentService;

@Controller
public class FicheDemandeController {
	
	 ///SERVICES
	 private FicheDemandeService ficheDemandeService;
	 private CINService cinService;
	 private ListesDiplomeService listesDiplomeService;
	 
	 private HttpSession session;
	 
	 private List<Tag> data = new ArrayList<Tag>();
	 
	 public FicheDemandeController() {}
	
	 @Autowired
	 public void setSession(HttpSession session) {
		this.session = session;
	 }
	 
	 private ActiviteRecentService activiteRecentService;
	 
	 @Autowired
	 public void setActiviteRecentService(ActiviteRecentService activiteRecentService) {
		this.activiteRecentService = activiteRecentService;
	 }
	 
	 ///CONVERTS
	 private DemandeToDemandeForm demandeToDemandeForm;
	 
	 @Autowired
	 public void setNiveauDiplomeService(NiveauDiplomeService niveauDiplomeService) {
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
        if(session.getAttribute("isConnected")!=null) {
        	return "pages/enregistrement/requestList";
        }
    	model.addAttribute("errorlogin", "4");
		return "pages/login";
        
	 }	
	 
	 @GetMapping("/showRequest/{id}")
	 public String getDemandeById(@PathVariable String id, Model model){
		 FicheDemande fiche = ficheDemandeService.getById(Long.valueOf(id));
		 model.addAttribute("ficheDemande", fiche);
		 if(fiche==null) {
			 return "redirect:/error404/requestList";	
		 }	
		// System.out.println("GEGE");
		
		 if(session.getAttribute("isConnected")!=null) {
			 return "pages/enregistrement/showRequest";	
		 }	
		 model.addAttribute("errorlogin", "4");
		 return "pages/login";
	 }

	 @GetMapping("/editRequest/{id}")
	 public String edit(@PathVariable String id, Model model){
        FicheDemande fiche = ficheDemandeService.getById(Long.valueOf(id));
        if(fiche==null) {
			 return "redirect:/error404";	
		 }	
        FicheDemandeForm ficheDemandeForm = demandeToDemandeForm.convert(fiche);

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
		 model.addAttribute("listeDemande", listeDemande);
		 model.addAttribute("ficheDemandeForm", ficheDemandeForm);
		 model.addAttribute("isEdit", "1");
       
    
    	if(session.getAttribute("isConnected")!=null) {
    	    return "pages/enregistrement/newRequest";
    	}	
    	model.addAttribute("errorlogin", "4");
    	return "pages/login";
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
		 
		 if(session.getAttribute("isConnected")!=null) {
			 return "pages/enregistrement/newRequest";
		 }	
		 model.addAttribute("errorlogin", "4");
		 return "pages/login";
		 		
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
			return "redirect:/error505";	 
		 }
		 
		 ficheDemandeForm.setDateAjout(GlobalHelper.getCurrentDate());
		 ficheDemandeForm.setStatusEnregistrement(false);
		 FicheDemande ficheSaved = ficheDemandeService.saveOrUpdateDemandeForm(ficheDemandeForm);
		 
		 //Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Une demande au nom de \""+ficheSaved.getCin().getNom().toUpperCase()+" "+ficheSaved.getCin().getPrenom()+"\""));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique	
		 	
		 return "redirect:/showRequest/" + ficheSaved.getId();
	 }
	 @PutMapping(value = "/updateRequest")
	 public String updateDemande(@Valid  @ModelAttribute FicheDemandeForm ficheDemandeForm, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){  
			return "redirect:/error505";	 
		 }
		 
		 FicheDemande ficheSaved = ficheDemandeService.saveOrUpdateDemandeForm(ficheDemandeForm);
		 //Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(3, "Une demande au NOM de \""+ficheSaved.getCin().getNom().toUpperCase()+" "+ficheSaved.getCin().getPrenom()+"\""));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique	
		 return "redirect:/showRequest/" + ficheSaved.getId();
	 }
	 
	 @GetMapping("/request/delete/{id}/{page}")
	 public String delete(@PathVariable String id, @PathVariable String page){
		 FicheDemande listesSaved = ficheDemandeService.getById(Long.valueOf(id));
		 ficheDemandeService.delete(Long.valueOf(id));
         
		 //Mis en historique
		 ActiviteRecent historique = new ActiviteRecent();
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(2, "La demande de "+listesSaved.getCin().getNom().toUpperCase()+" "+listesSaved.getCin().getPrenom()+" : "+listesSaved.getId() + "/" +listesSaved.getDateRetrait().getYear()));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	//fin historique
        return "redirect:/"+page;
	 }
	 
	 @GetMapping(value = "/getTags")
	 public @ResponseBody List<Tag> getTags(@RequestParam(required=true) String tagName) {
		 System.out.println("data == "+data.size());
		 return simulateSearchResult(tagName);

	 }
	 //My methods
	 public boolean isEncours(String idFiche) throws Exception {
		 
		 FicheDemande listesSaved = ficheDemandeService.getById(Long.valueOf(idFiche));
		 boolean ret = false;
		
		 /*	
		 try {
			 if(listesSaved == null) {
				throw new Exception("Error : the ListePromotion with id : "+idFiche+" is invalid!");	
			 } 
			 
			 List<ListePromotionDetail> listeAdmisChild = listePromotionDetailService.getDetailByIdListePromotion(listesSaved.getId());
			 if(listeAdmisChild.size()!=0) {
				 ret = true;
			 }
			 
		}catch(Exception e) {
			throw e;
		}*/
		return ret;
	}
	 
	 
	 
	 //My methods
	 private List<Tag> simulateSearchResult(String tagName) {
		 List<Tag> result = new ArrayList<Tag>();
		 try{
			 if(data.size()==0) {
				 data =  GlobalHelper.convertDiplomeToListTag(listesDiplomeService.listAll()); 
			 }
			 System.out.println("data == "+data.size());
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
	

}
