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

import com.testHibernate.converts.demande.DemandeDetailToDemandeDetailForm;
import com.testHibernate.converts.demande.DemandeFormToDemande;
import com.testHibernate.converts.demande.DemandeToDemandeForm;
import com.testHibernate.converts.equivalence.ArreteEqRefToArreteEqRefForm;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.helpers.Tag;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.demande.FicheDemandeDetail;
import com.testHibernate.model.demande.FicheDemandeDetailForm;
import com.testHibernate.model.demande.FicheDemandeForm;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.NiveauDiplome;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;
import com.testHibernate.model.equivalence.ContentArrete;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.service.cin.CINService;
import com.testHibernate.service.demande.FicheDemandeDetailService;
import com.testHibernate.service.demande.FicheDemandeService;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.diplome.NiveauDiplomeService;
import com.testHibernate.service.equivalence.ArreteEqRefService;
import com.testHibernate.service.equivalence.ContentArreteService;
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
	 private ArreteEqRefService arreteEqRefService;
	 private ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm;
	 
	 private DemandeFormToDemande demandeFormToDemande;
	 
	 @Autowired
	 public void setDemandeFormToDemande(DemandeFormToDemande demandeFormToDemande) {
		this.demandeFormToDemande = demandeFormToDemande;
	 }
	 
	 @Autowired
	 public void setArreteEqRefToArreteEqRefForm(ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm) {
		this.arreteEqRefToArreteEqRefForm = arreteEqRefToArreteEqRefForm;
	 }
	 
	 @Autowired
	 public void setArreteEqRefService(ArreteEqRefService arreteEqRefService) {
		this.arreteEqRefService = arreteEqRefService;
	 }
	 
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
	 int nombreLigneMax = 10;
	 private List<FicheDemande> fiches;
	 GlobalHelper gh = new GlobalHelper();
	 
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
	 
	 private ContentArreteService contentArreteService;
	 @Autowired
	 public void setContentArreteService(ContentArreteService contentArreteService) {
		this.contentArreteService = contentArreteService;
		
	 } 
	 
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
	 
	 @GetMapping({"/requestList", "/requestList/refresh/{compteur}", "/requestList/page-{page}", "/requestList/filter/{filter}", "/requestList/page-{page}/filter/{filter}"})
	 public String listDemande(Model model, @PathVariable(required=false) Integer compteur, @PathVariable(required=false) Optional<Integer> page, @PathVariable(required=false) Integer filter){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 initialListeFiche();
		 
		 List<FicheDemande> ret = ficheDemandeService.pagination(1, nombreLigneMax);
			if(page.isPresent()) {
				ret = ficheDemandeService.pagination(page.get(), nombreLigneMax);
				model.addAttribute("pageActuel", page.get());
			}  
			try {
				if(filter!=null && filter!=0) { 
					ret = ficheDemandeService.selectByRejet(ret, filter==1 ? false : true);
					model.addAttribute("filter", filter);
					model.addAttribute("filtrer", filter==1 ? "En-cours" : "Rejeté");
				}else if(filter!=null && filter==0) {
					model.addAttribute("filter", filter);
					model.addAttribute("filtrer", filter==1 ? "En-cours" : "Rejeté");
				}
				
				Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.fiches.size(), nombreLigneMax);
				model.addAttribute("nombrePagination", nombrePagination);
				
				if(compteur!=null) {
					model.addAttribute("compteurFiche", compteur);
				}
			} catch (Exception e) { 
				model.addAttribute("error", e);
	 			return "pages/erreur/505"; 
				//System.out.println("ERROR = "+e);
			}
        model.addAttribute("listeDemande", ret);
         
    	return "pages/enregistrement/requestList";
       
        
	 }	
	 
	 @GetMapping({"/showRequest/{id}", "/showRequest/{id}/statusRejet/{statusRejet}", "/showRequest/{id}/notification-{size}"})
	 public String afficherDemande(@PathVariable String id, @PathVariable(required=false) String statusRejet , @PathVariable(required=false) String size, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 FicheDemande fiche = ficheDemandeService.getById(Long.valueOf(id));
		  
		 if(fiche==null) {
			 return "redirect:/error404/requestList";	
		 }	
		 fiche.setStatusRejet(fiche.getStatusRejet() ? false : true);
		 model.addAttribute("ficheDemande", fiche);
		 FicheDemandeDetail ficheDetail = ficheDemandeDetailService.getFicheDemandeByFiche(fiche.getId());
		 model.addAttribute("ficheDemandeDetail", ficheDetail);
		 if(size!=null) {
			 model.addAttribute("size", size );
		 }
		 if(statusRejet!=null && !statusRejet.equals("")) {
			 System.out.println("HELLO statusRejet == "+statusRejet);
			 if(statusRejet.equals("1")) {
				 model.addAttribute("statusRejet", statusRejet);
			 }else if(statusRejet.equals("2")){
				 ArreteEqRef arrete = arreteEqRefService.getArreteByIdDiplome(fiche.getListesDiplome().getId());
				 model.addAttribute("statusRejet", statusRejet);
				 model.addAttribute("arreteRejet", arrete!=null ? arrete : null);
			 }
			
		 }
	  
		 return "pages/enregistrement/showRequest";	
		 
	 }

	 @GetMapping("/editRequest/{id}")
	 public String edit(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
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
       
     
    	    return "pages/enregistrement/newRequest";
     
	 }
	 
	 @GetMapping({"/newRequest","/newRequest/needAddListe/cin-{cin}/diplome-{prom}-{sessionDeb}","/newRequest/isDuplicated/{idFiche}", "/newRequest/page-{page}"})
	 public String ajouterDemande(Model model , @PathVariable(required=false) Optional<Long> cin, @PathVariable(required=false) Optional<Long> prom,
			 @PathVariable(required=false) String sessionDeb,
			 @PathVariable(required=false) Optional<Long> idFiche,
			 @PathVariable(required=false) Optional<Integer> page) {
		 
		 //initial
		 initialListeFiche();
		 //Get Lists 
		 List<CIN> listCIN = cinService.listAll();
		 List<FicheDemande> listeDemande = ficheDemandeService.pagination(1, nombreLigneMax);
		 
		if(page.isPresent()) {
			listeDemande = ficheDemandeService.pagination(page.get(), nombreLigneMax);
		}
		try {
			listeDemande = ficheDemandeService.selectByRejet(listeDemande, false);
			Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.fiches.size(), nombreLigneMax);
			model.addAttribute("nombrePagination", nombrePagination);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	 if(session.getAttribute("isConnected")==null) {
		 model.addAttribute("errorlogin", "4");
		 return "pages/login";
	 }	

		 //List<ListesDiplome> listesDiplome = listesDiplomeService.listAll();
		 List<String> listLieuDelivrance = cinService.getAllLieuDelivrance();
		 List<String> listEcole = listesDiplomeService.getAllEcole();
		 List<String> mentions = GlobalHelper.getMentionList();
		 List<NiveauDiplome> niveauxDiploma = niveauDiplomeService.listAll();
		 
		 //Dispatch
		 //model.addAttribute("listesDiplome", listesDiplome);
		 model.addAttribute("listEcole", listEcole);
		 model.addAttribute("listNiveauDiploma", niveauxDiploma);
		 model.addAttribute("listCIN", listCIN);
		 model.addAttribute("mentions", mentions);
		 model.addAttribute("listeDemande", listeDemande);
		 model.addAttribute("listLieuDelivrance", listLieuDelivrance);
		 model.addAttribute("ficheDemandeForm", new FicheDemandeForm());
		 model.addAttribute("ficheDemandeDetailForm", new FicheDemandeDetailForm());
		 	
		 if(cin.isPresent() && prom.isPresent() ) {
			 ListePromotion proms = null;
			 ListePromotion temp = listePromotionService.getPromotionByIdListeDiplome(Long.valueOf(prom.get()));
			 proms = listePromotionService.getByIdDiplomeAndSession(Long.valueOf(prom.get()), sessionDeb );
			 CIN need = cinService.getById(cin.get());
			 model.addAttribute("needProm", need);  
			 if(proms!=null) {
				 model.addAttribute("proms", proms);
			 }else if(proms==null && temp!=null){
				 
				 model.addAttribute("ecole", temp.getListesDiplome().getEcole() + " EN " + temp.getListesDiplome().getFiliere());
				 model.addAttribute("sessionDeb", sessionDeb );
			 }
			
		 }
		 if(idFiche.isPresent()) {
			 FicheDemande duplicate = ficheDemandeService.getById(idFiche.get());
			 model.addAttribute("isDuplicated", duplicate);
		 } 
		 
		 
		 return "pages/enregistrement/newRequest";
		 
		 		
	 } 
	 
	 @PostMapping(value = "/saveRequest")
	 public String saveOrUpdateDemande(@Valid  @ModelAttribute FicheDemandeForm ficheDemandeForm,  @RequestParam String cin, @RequestParam String listeDiplome, @Valid  @ModelAttribute FicheDemandeDetailForm ficheDemandeDetailForm, BindingResult bindingResult, Model model){
		 
		 if(bindingResult.hasErrors()){  
			return "redirect:/error505";	 
		 }
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 
		 List<ListePromotionDetail> leTraiter = null;
		 FicheDemande ficheSaved = null;
		 FicheDemande isDuplicated = null;
		 int size=-1;
		 int rejete = -1;
	 try { 
		 isDuplicated = this.hasDuplicated(cin, listeDiplome);
		 if(isDuplicated==null) {
				 
			 leTraiter = listePromotionDetailService.getAllAdmisByCIN(Long.valueOf(cin));
			 ficheDemandeForm.setStatusRejet(false);
			 ficheDemandeForm.setDateAjout(GlobalHelper.getCurrentDate());
			 ficheDemandeForm.setStatusEnregistrement(false);
			 ficheDemandeForm.setCin(cinService.getById(Long.valueOf(cin)));
			 ficheDemandeForm.setListesDiplome(listesDiplomeService.getById(Long.valueOf(listeDiplome)));
			  
			 if(leTraiter.size()!=0 && checkAdmisOK(leTraiter, demandeFormToDemande.convert(ficheDemandeForm), ficheDemandeDetailForm.getAnneeDeb())==true) {
				 
				 size = leTraiter.size();
			 }else if(leTraiter.size()==0 || checkIfArreteDiplomeExist(listesDiplomeService.getById(Long.valueOf(listeDiplome)))==false || checkAdmisOK(leTraiter, demandeFormToDemande.convert(ficheDemandeForm), ficheDemandeDetailForm.getAnneeDeb())==false){
				
				 ficheDemandeForm.setStatusRejet(true);
				 rejete = 1;
				 if(checkIfArreteDiplomeExist(listesDiplomeService.getById(Long.valueOf(listeDiplome)))==false ) {
					 rejete = 2;
				 }
				 //return "redirect:/newRequest/needAddListe/cin-"+Long.valueOf(cin)+"/diplome-"+Long.valueOf(listeDiplome)+"-"+ficheDemandeDetailForm.getAnneeDeb();
			 }
			 // System.out.println("\n\n TEST : cin = "+cin+ "\n listeDiplome = "+listeDiplome); 
	
			 ficheSaved = ficheDemandeService.saveOrUpdateDemandeForm(ficheDemandeForm);
			 
				 //Mis en historique
				 ActiviteRecent historique = new ActiviteRecent(); 
				 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Une demande au nom de \""+ficheSaved.getCin().getNom().toUpperCase()+" "+ficheSaved.getCin().getPrenom()+"\""));
				 	historique.setDateAjout(GlobalHelper.getCurrentDate());
				 	activiteRecentService.saveOrUpdate(historique);
			 	 //fin historique	
		 
		 	FicheDemandeDetail ficheDetail = this.saveDemandeDetail(ficheSaved, ficheDemandeDetailForm);
		}else {
			return "redirect:/newRequest/isDuplicated/"+isDuplicated.getId();
		}
		 
	 	}catch(Exception e) {
	 		e.printStackTrace();
	 		model.addAttribute("error", e);
 			return "pages/erreur/505"; 
	 		//
	 	} 
	 	if(size!=-1) {
	 		return "redirect:/showRequest/" + ficheSaved.getId() + "/notification-"+size;
	 	}
	 	if(rejete!=-1) {
	 		return "redirect:/showRequest/" + ficheSaved.getId()+"/statusRejet/"+rejete;
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
	 
	 @SuppressWarnings("deprecation")
	 @GetMapping("/request/rejete/{id}/{page}")
	 public String delete(@PathVariable String id, @PathVariable String page, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 FicheDemande listesSaved = new FicheDemande();
		 System.out.println("\n\n Error liste id = "+id);
		 listesSaved = ficheDemandeService.getById(Long.valueOf(id));
		 
		 if(listesSaved==null) {
			 //System.out.println("\n\n Error listesSaved  = "+listesSaved.getDiplome());
			 return "redirect:/error404/"+page;
		 }
		 listesSaved.setStatusRejet(true);
		 listesSaved = ficheDemandeService.saveOrUpdate(listesSaved);
		 //ficheDemandeService.delete(Long.valueOf(id));
         
		 //Mis en historique
		 ActiviteRecent historique = new ActiviteRecent();
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(4, "La demande de "+listesSaved.getCin().getNom().toUpperCase()+" "+listesSaved.getCin().getPrenom()+" : "+listesSaved.getId() + "/" +listesSaved.getDateRetrait().getYear()));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	//fin historique
        return "redirect:/"+page;
	 }
	 
	 @GetMapping("/searchDiplome")
	 public @ResponseBody List<Tag> getTags(@RequestParam(required=true) String champ ) {
		 
		 //System.out.println("\n\n\n CHAMP == "+champ);
		 return simulateSearchResult(champ);

	 }
	 @GetMapping(value = "/searchCritereDiplome")
	 public @ResponseBody List<Tag> getListeDemandeByCriteres(@RequestParam(required=true) String ecole,
			 @RequestParam(required=true) String filiere,@RequestParam(required=true) String option,@RequestParam(required=true, defaultValue="") String niveau) {
		
		 return this.simulateSearchResultByCritere( ecole, filiere, option, niveau);

	 }
	 
	 
	 @GetMapping({"/traitement/{id}", "/traitement/{id}/statusRejet/{status}"})
	 public String traitementFiche(@PathVariable String id,@PathVariable Integer status, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	 
		FicheDemande ficheSaved = ficheDemandeService.getById(Long.valueOf(id));
		ContentArrete contentArrete = null;
		String contentArticle = GlobalHelper._ArticleContent;
	 	if(ficheSaved==null) { 
	 		return "redirect:/error404/requestList";
	 	}  
	 	if(ficheSaved.getStatusRejet() && status==1) {
	 		return "redirect:/showRequest/"+ficheSaved.getId()+"/statusRejet/1";
	 	}else if(ficheSaved.getStatusRejet() && status!=1) {
	 		return "redirect:/home/statusRejet/1" ;
	 	}
	 	
	 	List<ListePromotionDetail> listeTraiter = null;
	 	ListePromotionDetail leTraiter = null;
 	try {
 		listeTraiter = listePromotionDetailService.getAllAdmisByCIN(ficheSaved.getCin().getId()); 
 		leTraiter = admisTraiter(listeTraiter, ficheSaved);
	 	FicheDemandeDetail ficheDetail = ficheDemandeDetailService.getFicheDemandeByFiche(ficheSaved.getId());
 		if(ficheDetail==null) {
			// System.out.println("\n\n\n ficheDetail==null \n");
 			model.addAttribute("pageRedirect", "requestList");
 			return "pages/erreur/404"; 
 		} 
 		ArreteEqRef ref = arreteEqRefService.getArreteByIdDiplome(ficheSaved.getListesDiplome().getId());
 		contentArrete = contentArreteService.getContentByArrete(ref.getId())!=null ? contentArreteService.getContentByArrete(ref.getId()) : null;
 		ArreteEqRefForm	arreteEqRefForm = arreteEqRefToArreteEqRefForm.convert(ref);
 		ListesDiplome diplome = ficheSaved.getListesDiplome();
 		
 			model.addAttribute("contentArrete",  contentArrete);
 			model.addAttribute("contentArticle", contentArticle);
 			model.addAttribute("arreteEqRefForm", arreteEqRefForm);
			model.addAttribute("leTraiter", leTraiter);
			model.addAttribute("diplome", diplome);
			model.addAttribute("ficheDemande", ficheSaved);
			model.addAttribute("ficheDemandeDetail", ficheDetail);
			
			//Verification cas demande rejeté
		 	
	 	}catch(Exception e) {
	 		model.addAttribute("error", e);
 			return "pages/erreur/505"; 
	 	}
	 	return "pages/traitement/traitement";
	 }
	  
	 
	 //My methods
	 private List<Tag> simulateSearchResult(String tagName) {
		 List<Tag> result = new ArrayList<Tag>();
		 initialListeFiche();
		 try{
			 if(data.size()==0) {
				 data =  gh.convertDiplomeToListTag(this.listeDiplomes); 
			 }
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
	//My methods
	 private List<Tag> simulateSearchResultByCritere(String ecole, String filiere, String option, String idNiveau) {
			 List<Tag> result = new ArrayList<Tag>();
			 initialListeFiche();
			 
			 try{ 
				 result =  gh.convertMultiple(listesDiplomeService.searchMultiple(ecole, filiere, option), idNiveau); 
				  
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
			 return result;
		 }
	 public FicheDemandeDetail saveDemandeDetail(FicheDemande fiche, FicheDemandeDetailForm ficheDemandeDetailForm) {
		 FicheDemandeDetail ficheDetail = null;	
		 try {
			 	int anneeFin = Integer.parseInt(ficheDemandeDetailForm.getAnneeDeb())+(int)1;
			 	FicheDemandeDetailForm saved = ficheDemandeDetailForm;
			 	saved.setAnneeFin(anneeFin+"");
			 	saved.setFicheDemande(fiche); 
			 	ficheDetail = ficheDemandeDetailService.saveOrUpdateDemandeFormDetail(saved);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		 return ficheDetail;
	 }
	 public void initialListeFiche() {
			this.fiches = ficheDemandeService.listAll();
			this.listeDiplomes = listesDiplomeService.listAll();
	 } 
	 
	 public ListePromotionDetail admisTraiter(List<ListePromotionDetail> listeTraiter, FicheDemande fiche) {
		 ListePromotionDetail ret = null;
		 try {
			 if(listeTraiter.size()==0) {
				 throw new Exception("Error method admisTrater: the field listeTraiter is invalid size 0.");
			 }
			 for(ListePromotionDetail temp : listeTraiter) {
				 if(temp.getListePromotion().getListesDiplome().getId().equals(fiche.getListesDiplome().getId())) {
					 ret = temp;
					 break;
				 }
			 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return ret;
	 }
	 public FicheDemande hasDuplicated(String cin, String listeDiplome) {
		 FicheDemande ret = null;
		 List<FicheDemande> listeDemande = null;
		 try {
			 listeDemande = ficheDemandeService.getFicheDemandeByCIN(Long.valueOf(cin));
			 if(listeDemande.size()==0) {
				 return ret;
			 }
			 for(FicheDemande temp : listeDemande) {
				 if(temp.getListesDiplome().getId().equals(Long.valueOf(listeDiplome))) {
					 ret = temp;
					 break;
				 }
			 }
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 return ret;
	 }
	 
	 
	 public Boolean checkAdmisOK(List<ListePromotionDetail> listeAdmis, FicheDemande fiche, String anneeDeb) {
		 Boolean ret = false;
		 try {
			 if(listeAdmis.size()!=0 && fiche!=null) {  
				 for(ListePromotionDetail admis : listeAdmis) {   
					 if(admis.getListePromotion().getSessionSortie().equals(anneeDeb)) {
						 if( admis.getListePromotion().getListesDiplome().getId() == fiche.getListesDiplome().getId()) {
							 System.out.println(">>>>>>>>>>>>>>>>>>>>>HELLO<<<<<<<<<<<<<<<<<<<< ");
							 ret = true;break;
						 }
					 }
				 }
			 }
		 }catch(Exception e) {
			 throw e;
			 //e.printStackTrace();
		 }
		 System.out.println("RET === "+ret);
		 return ret;
	 }
	 public boolean checkIfArreteDiplomeExist(ListesDiplome diplome)throws Exception {
		 boolean ret = false;
		 ArreteEqRef arrete = null;
		 
		 try {
			 arrete = this.arreteEqRefService.getArreteByIdDiplome(diplome.getId());
			 if(diplome!=null && arrete!=null && arrete.getStatus()==true) {
				 ret = true;
			 }
		 } catch(Exception e) {
			 throw e;
		 }
		 return ret;
	 }
	

}
