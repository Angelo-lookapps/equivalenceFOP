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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.testHibernate.converts.demande.DemandeDetailToDemandeDetailForm;
import com.testHibernate.converts.demande.DemandeToDemandeForm;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.helpers.Tag;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.demande.FicheDemandeDetail;
import com.testHibernate.model.demande.FicheDemandeDetailForm;
import com.testHibernate.model.demande.FicheDemandeForm;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.NiveauDiplome;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.service.cin.CINService;
import com.testHibernate.service.demande.FicheDemandeDetailService;
import com.testHibernate.service.demande.FicheDemandeService;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.diplome.NiveauDiplomeService;
import com.testHibernate.service.historique.ActiviteRecentService;
import com.testHibernate.service.listePromotion.ListePromotionDetailService;
import com.testHibernate.service.listePromotion.ListePromotionService;

@Controller
public class FicheDemandeController {
	
	 ///SERVICES
	 private FicheDemandeService ficheDemandeService;
	 private CINService cinService;
	 private ListesDiplomeService listesDiplomeService;
	 private FicheDemandeDetailService ficheDemandeDetailService;
	 private ActiviteRecentService activiteRecentService;
	 private ListePromotionDetailService listePromotionDetailService;
	 private ListePromotionService listePromotionService;
	 private DemandeDetailToDemandeDetailForm ficheDemandeDetailToficheDemandeDetailForm;
	 private NiveauDiplomeService niveauDiplomeService;
	 @Autowired
	 public void setFicheDemandeDetailToficheDemandeDetailForm(
			DemandeDetailToDemandeDetailForm ficheDemandeDetailToficheDemandeDetailForm) {
		this.ficheDemandeDetailToficheDemandeDetailForm = ficheDemandeDetailToficheDemandeDetailForm;
	 }

	 @Autowired
	 public void setListePromotionService(ListePromotionService listePromotionService) {
		this.listePromotionService = listePromotionService;
	 }

	@Autowired
	 public void setListePromotionDetailService(ListePromotionDetailService listePromotionDetailService) {
		this.listePromotionDetailService = listePromotionDetailService;
	 }
	//Tags
	 private HttpSession session;
	 private List<Tag> data = new ArrayList<Tag>();
	 int nombreLigneMax = 5;
	 private List<FicheDemande> fiches;
	 
	 void setNombreLigneMax(int nombre) {
		 this.nombreLigneMax = nombre;
	 }
	 public FicheDemandeController() {}
	
	 @Autowired
	 public void setSession(HttpSession session) {
		this.session = session;
	 }
	 @Autowired
	 public void setActiviteRecentService(ActiviteRecentService activiteRecentService) {
		this.activiteRecentService = activiteRecentService;
	 }
	 ///CONVERTS
	 private DemandeToDemandeForm demandeToDemandeForm;
	 
	 private List<ListesDiplome> listeDiplomes;
	 
	 
	 @Autowired
	 public void setNiveauDiplomeService(NiveauDiplomeService niveauDiplomeService) {
		 this.niveauDiplomeService = niveauDiplomeService;
	 }
	 
	 @Autowired
	 public void setFicheDemandeDetailService(FicheDemandeDetailService ficheDemandeDetailService) {
		this.ficheDemandeDetailService = ficheDemandeDetailService;
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
 
	 @GetMapping({"/requestList", "/requestList/page-{page}"})
	 public String listDemande(Model model, @PathVariable(required=false) Optional<Integer> page){
	 
		 initialListeFiche();
		 List<FicheDemande> ret = ficheDemandeService.pagination(1, nombreLigneMax);
			if(page.isPresent()) {
				ret = ficheDemandeService.pagination(page.get(), nombreLigneMax);
			}  
			try {
				Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.fiches.size(), nombreLigneMax);
				model.addAttribute("nombrePagination", nombrePagination);
			} catch (Exception e) { 
				e.printStackTrace();
			}
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
		 FicheDemandeDetail ficheDetail = ficheDemandeDetailService.getFicheDemandeByFiche(fiche.getId());
		 model.addAttribute("ficheDemandeDetail", ficheDetail);
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
			 return "redirect:/error404/RequestList";	
		 }	
        FicheDemandeForm ficheDemandeForm = demandeToDemandeForm.convert(fiche);
        FicheDemandeDetail ficheDemandeDetail  = ficheDemandeDetailService.getFicheDemandeByFiche(fiche.getId());
        FicheDemandeDetailForm ficheDemandeDetailForm = ficheDemandeDetailToficheDemandeDetailForm.convert(ficheDemandeDetail);
        
		List<FicheDemande> listeDemande = ficheDemandeService.listAll();
		List<CIN> listCIN = cinService.listAll();
		List<ListesDiplome> listesDiplome = listesDiplomeService.listAll();
		List<String> listLieuDelivrance = cinService.getAllLieuDelivrance();
		List<String> listEcole = listesDiplomeService.getAllEcole();
		List<String> mentions = GlobalHelper.getMentionList();
		
		 //Dispatch
		 model.addAttribute("listesDiplome", listesDiplome);
		 model.addAttribute("listEcole", listEcole);
		 model.addAttribute("listCIN", listCIN);
		 model.addAttribute("mentions", mentions);
		 model.addAttribute("listeDemande", listeDemande);
		 model.addAttribute("listLieuDelivrance", listLieuDelivrance);
		 model.addAttribute("listeDemande", listeDemande);
		 model.addAttribute("ficheDemandeForm", ficheDemandeForm);
		 model.addAttribute("ficheDemandeDetailForm", ficheDemandeDetailForm);
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
		 List<String> mentions = GlobalHelper.getMentionList();
		 List<NiveauDiplome> niveauxDiploma = niveauDiplomeService.listAll();
		 
		 //Dispatch
		 model.addAttribute("listesDiplome", listesDiplome);
		 model.addAttribute("listEcole", listEcole);
		 model.addAttribute("listNiveauDiploma", niveauxDiploma);
		 model.addAttribute("listCIN", listCIN);
		 model.addAttribute("mentions", mentions);
		 model.addAttribute("listeDemande", listeDemande);
		 model.addAttribute("listLieuDelivrance", listLieuDelivrance);
		 model.addAttribute("ficheDemandeForm", new FicheDemandeForm());
		 model.addAttribute("ficheDemandeDetailForm", new FicheDemandeDetailForm());
		 
		 if(session.getAttribute("isConnected")!=null) {
			 return "pages/enregistrement/newRequest";
		 }	
		 model.addAttribute("errorlogin", "4");
		 return "pages/login";
		 		
	 } 
	 
	 @PostMapping(value = "/saveRequest")
	 public String saveOrUpdateDemande(@Valid  @ModelAttribute FicheDemandeForm ficheDemandeForm,  @RequestParam String cin, @RequestParam String listeDiplome, @Valid  @ModelAttribute FicheDemandeDetailForm ficheDemandeDetailForm, BindingResult bindingResult){
		 
		 if(bindingResult.hasErrors()){  
			return "redirect:/error505";	 
		 }
		 System.out.println("\n\n TEST : cin = "+cin+ "\n listeDiplome = "+listeDiplome);
		 ficheDemandeForm.setDateAjout(GlobalHelper.getCurrentDate());
		 ficheDemandeForm.setStatusEnregistrement(false);
		 ficheDemandeForm.setCin(cinService.getById(Long.valueOf(cin)));
		 ficheDemandeForm.setListesDiplome(listesDiplomeService.getById(Long.valueOf(listeDiplome)));
		 FicheDemande ficheSaved = ficheDemandeService.saveOrUpdateDemandeForm(ficheDemandeForm);
		 
		 //Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Une demande au nom de \""+ficheSaved.getCin().getNom().toUpperCase()+" "+ficheSaved.getCin().getPrenom()+"\""));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique	

	 	try{
	 		
	 		FicheDemandeDetail ficheDetail = this.saveDemandeDetail(ficheSaved, ficheDemandeDetailForm);
	 	
	 	}catch(Exception e) {
	 		e.printStackTrace();
	 	} 
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
	 
	 @GetMapping(value = "/searchDiplome")
	 public @ResponseBody List<Tag> getTags(@RequestParam(required=true) String champ) {
		 System.out.println("data == "+data.size());
		 return simulateSearchResult(champ);

	 }
	 @GetMapping(value = "/searchCritereDiplome")
	 public @ResponseBody List<Tag> getListeDemandeByCriteres(@RequestParam(required=true) String champ,@RequestParam(required=true) String ecole,
			 @RequestParam(required=true) String filiere,@RequestParam(required=true) String option,@RequestParam(required=true) String niveau) {
		 //System.out.println("data == "+data.size());
		 return simulateSearchResult(champ);

	 }
	 
	 
	 @GetMapping("/traitement/{id}")
	 public String traitementFiche(@PathVariable String id, Model model){
		 	FicheDemande ficheSaved = ficheDemandeService.getById(Long.valueOf(id));
	 try {
			
		 if(ficheSaved==null) {
			 System.out.println("\n\n\n ficheSaved==null \n");
			 return "redirect:/error404/requestList";
		 }
		 	FicheDemandeDetail ficheDetail = ficheDemandeDetailService.getFicheDemandeByFiche(ficheSaved.getId());
		 if(ficheDetail==null) {
			 System.out.println("\n\n\n ficheDetail==null \n");
			 return "redirect:/error404/requestList";
		 }
		 	ListePromotion listePromotion = listePromotionService.getPromotionByIdListeDiplome(ficheSaved.getListesDiplome().getId());
		 if(listePromotion==null) {
			 System.out.println("\n\n\n listePromotion==null \n");
			 return "redirect:/error404/requestList";
		 }
			 //System.out.println("\n\n\n listePromotion = "+listePromotion.getId()+" \n");
			 List<ListePromotionDetail> listePromotionDetail = listePromotionDetailService.getDetailByIdListePromotion(listePromotion.getId());
			 String[] search = {ficheSaved.getCin().getNom(), ficheSaved.getCin().getPrenom(),
					 			ficheSaved.getCin().getDateNaissance()+" - "+ficheSaved.getCin().getLieuNaissance()}; 
			 
			 List<ListePromotionDetail> suggestion = GlobalHelper.searchAtListe(listePromotionDetail, search);
			 List<ListePromotionDetail> autreQueSuggestion = suggestion!=null ? GlobalHelper.listAnotherSuggestion(suggestion, listePromotionDetail) : listePromotionDetail;
			 model.addAttribute("listePromotion", listePromotion);
			 if(suggestion!=null) {
				 model.addAttribute("suggestion", suggestion);
			 } 
			 model.addAttribute("listePromotionDetail", autreQueSuggestion);
			 model.addAttribute("ficheDemande", ficheSaved);
			 model.addAttribute("ficheDemandeDetail", ficheDetail); 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return "pages/traitement/traitement";
	 }
	  
	 
	 //My methods
	 private List<Tag> simulateSearchResult(String tagName) {
		 List<Tag> result = new ArrayList<Tag>();
		 initialListeFiche();
		 try{
			 if(data.size()==0) {
				 data =  GlobalHelper.convertDiplomeToListTag(listeDiplomes); 
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
	 public FicheDemandeDetail saveDemandeDetail(FicheDemande fiche, FicheDemandeDetailForm ficheDemandeDetailForm) {
		 FicheDemandeDetail ficheDetail = null;	
		 try {
			
			 	FicheDemandeDetailForm saved = ficheDemandeDetailForm;
			 	saved.setFicheDemande(fiche); 
			 	ficheDetail = ficheDemandeDetailService.saveOrUpdateDemandeFormDetail(saved);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		 return ficheDetail;
	 }
	 public void initialListeFiche() {
		if(this.fiches==null || this.listeDiplomes==null){
			this.fiches = ficheDemandeService.listAll();
			this.listeDiplomes = listesDiplomeService.listAll();
		}
	 } 
	 
	

}
