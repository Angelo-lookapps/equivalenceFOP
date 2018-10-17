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
import org.springframework.web.bind.annotation.RequestParam;

import com.testHibernate.converts.listePromotion.ListePromotionDetailToListePromotionDetailForm;
import com.testHibernate.converts.listePromotion.ListePromotionToListePromotionForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.helpers.TempListePromotion;
import com.testHibernate.importFile.excel.MyReaderExcel;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.demande.FicheDemandeDetail;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;
import com.testHibernate.model.listePromotion.ListePromotionForm;
import com.testHibernate.service.cin.CINService;
import com.testHibernate.service.demande.FicheDemandeDetailService;
import com.testHibernate.service.demande.FicheDemandeService;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.historique.ActiviteRecentService;
import com.testHibernate.service.listePromotion.ListePromotionDetailService;
import com.testHibernate.service.listePromotion.ListePromotionService;

@Controller
public class ListePromotionController {
	
	 ///SERVICES
	 private ListePromotionService listePromotionService;
	 private ListePromotionDetailService listePromotionDetailService;
	 private ListePromotionToListePromotionForm listePromotionToListePromotionForm;
	 private ListesDiplomeService listesDiplomeService;
	 private ListePromotionDetailToListePromotionDetailForm listePromotionDetailToListePromotionDetailForm;
	 private CINService cinService;
	 private HttpSession session;
	 private List<ListePromotion> listeProms;
	 private FicheDemandeDetailService ficheDemandeDetailService;
	 private FicheDemandeService ficheDemandeService;
	 int nombreLigneMax = 5;
	 @Autowired
	 public void setSession(HttpSession session) {
		this.session = session;
	 }
	 
	 @Autowired
	 public void setFicheDemandeService(FicheDemandeService ficheDemandeService) {
		this.ficheDemandeService = ficheDemandeService;
	 }
	 
	 @Autowired
	 public void setFicheDemandeDetailService(FicheDemandeDetailService ficheDemandeDetailService) {
		this.ficheDemandeDetailService = ficheDemandeDetailService;
	 }
	 
	 @Autowired
	 public void setCINService(CINService cinService) {
		this.cinService = cinService;
	 }
	 
	 @Autowired
	 public void setListePromotionToListePromotionForm(ListePromotionToListePromotionForm listePromotionToListePromotionForm) {
		this.listePromotionToListePromotionForm = listePromotionToListePromotionForm;
	 }
	 
	 @Autowired
	 public void setListePromotionDetailToListePromotionDetailForm(ListePromotionDetailToListePromotionDetailForm listePromotionDetailToListePromotionDetailForm) {
		this.listePromotionDetailToListePromotionDetailForm = listePromotionDetailToListePromotionDetailForm;
	 }
	 
	 private ActiviteRecentService activiteRecentService;
	 
	 @Autowired
	 public void setActiviteRecentService(ActiviteRecentService activiteRecentService) {
		this.activiteRecentService = activiteRecentService;
	 }
	  
	 
	 @Autowired
	 public void setListePromotionService(ListePromotionService tousListeDiplomeService) {
		this.listePromotionService = tousListeDiplomeService;
	 }

	 @Autowired
	 public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
		this.listesDiplomeService = listesDiplomeService;
	 }	

	@Autowired
	 public void setListesDiplomeDetailService(ListePromotionDetailService listesDiplomeDetailService) {
		 this.listePromotionDetailService = listesDiplomeDetailService;
	 }
	
	@GetMapping("/refreshAllRejet")
	public String actualiserFicheDemande(Model model) {
		List<FicheDemande> ficheRejetRecent = null;
		int compteurFiche = 0;
		try {
			ficheRejetRecent = refreshAllStatusRejet(); 
			if(ficheRejetRecent!=null && ficheRejetRecent.size()!=0) {
				/*System.out.println("/////////////////////////////////////////////////////////////////////////"); 
				System.out.println("Fiche Rejete to EnCour = "+ficheRejetRecent.size()); */
				for(FicheDemande cible : ficheRejetRecent) {
			    	cible.setStatusRejet(false);
			    	
			    	FicheDemande temp = ficheDemandeService.saveOrUpdate(cible);
					//Mis en historique
					ActiviteRecent historique = new ActiviteRecent(); 
					 	historique.setDefinition( GlobalHelper.getQueryStringActivities(3, " la demande \""+temp.getListesDiplome().getEcole()+" "+temp.getListesDiplome().getFiliere()+" "
					 			+ ""+temp.getListesDiplome().getOption()+" : de "+temp.getCin().getNom()+" "+temp.getCin().getPrenom()+" rendez-vous le "+temp.getDateRetrait()+"\""));
					 	historique.setDateAjout(GlobalHelper.getCurrentDate());
					 	activiteRecentService.saveOrUpdate(historique);
				 	 //fin historique
					 if(temp!=null) {
						 compteurFiche++;
					 }
			    }
			 }
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
		}

		return "redirect:/requestList/refresh/"+compteurFiche;
		
	}
	 

	@GetMapping({"/listProm", "/listProm/warning/{id}", "/listProm/page-{page}"})
	 public String listPromo(Model model, @PathVariable(required=false) Optional<String> id,  @PathVariable(required=false) Optional<Integer> page){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		List<ListePromotion> ret = new ArrayList<ListePromotion>();
		List<TempListePromotion> retour = new ArrayList<TempListePromotion>();
		try {
			if(id.isPresent() && !id.get().equals("0")) {
        		model.addAttribute("deleteWarning", id.get()); 
            }else if(id.isPresent() && id.get().equals("0")) {
        		model.addAttribute("deleteWarning2", id.get()); 
            }
			
			initialListePromotion();
			ret = this.listePromotionService.pagination(1, nombreLigneMax);
				if(page.isPresent()) {
					ret = listePromotionService.pagination(page.get(), nombreLigneMax);
				}  
				try {
					Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.listeProms.size(), nombreLigneMax);
					model.addAttribute("nombrePagination", nombrePagination);
					
					retour = GlobalHelper.getTempListPromotion(ret, this.getEffectifTotal(ret));
							
				} catch (Exception e) { 
					model.addAttribute("error", e);
		 			return "pages/erreur/505"; 
					//e.printStackTrace();
				}
			List<ListesDiplome> listeDiploma = listesDiplomeService.listAll(); 
			List<String> listEcole = listesDiplomeService.getAllEcole();
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
			model.addAttribute("annees", annee);
			model.addAttribute("listEcole", listEcole);
			model.addAttribute("listeDiploma", listeDiploma);
		    model.addAttribute("promos", retour);
			model.addAttribute("listPromotionForm", new ListePromotionForm());
		
		} catch (Exception e) {
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}
		
		 return "pages/listePromotion/listProm";
       
       // System.out.println("\n ret.Length = " + ret.size());
      
        
	 }	
	
	@PostMapping("/savePromo")
	public String ajoutPromo(@Valid  @ModelAttribute ListePromotionForm listePromotionForm , @RequestParam(required=false) String listeDiplome, BindingResult bindingResult, Model model) {
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		ListePromotion listesSaved = null;
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		} 
		listePromotionForm.setDateAjout(GlobalHelper.getCurrentDate()); 
		if(listeDiplome!=null)
			listePromotionForm.setListesDiplome(listesDiplomeService.getById(Long.valueOf(listeDiplome)));
		
		listesSaved = listePromotionService.saveOrUpdateListePromotionForm(listePromotionForm);
		//Mis en historique
		ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Une nouvelle promotion \""+listesSaved.getNomPromotion()+" "+listesSaved.getListesDiplome().getFiliere()+" "+listesSaved.getListesDiplome().getOption()+" "+listesSaved.getListesDiplome().getEcole()+"\""));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique
		return "redirect:/listProm/";		
	}
	
	@PostMapping("/saveAdmis/{id}")
	public String ajoutAdmisDetail(@PathVariable String id,@Valid  @ModelAttribute ListePromotionDetailForm listePromotionDetailForm, @RequestParam(required=true) String idCin, @RequestParam(required=false) String adresseActuelle , BindingResult bindingResult, Model model) {
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		ListePromotionDetail listesSaved = null;
		ListePromotion listePromotion1 = listePromotionService.getById(Long.valueOf(id));
		if(listePromotion1==null) {
			 return "redirect:/error404/listProm";	
		}
		Long newCIN = (long) 0;  
		if(bindingResult.hasErrors()){
			return "redirect:/error505";
		}  
		CIN getit = null;
		 System.out.println("\n\n .......................................................\n"
			 		+ " listePromotionDetailForm = "+listePromotionDetailForm.getId());
		try {
			if(!listePromotionDetailForm.getNomComplet().equals("") || !listePromotionDetailForm.getLieuNaissance().equals("") ) {
				
				listePromotionDetailForm.setDateAjout(GlobalHelper.getCurrentDate());
				  getit = insertNewCIN(listePromotionDetailForm.getNomComplet(), listePromotionDetailForm.getDateNaissance(), listePromotionDetailForm.getLieuNaissance(), adresseActuelle); 
				  if(getit!=null){
					  System.out.println("getit = "+getit.getId());
					  newCIN = getit.getId(); 
				  }
				
			}else {
				System.out.println("999999999999999999999999999999999999999999999999999999999999999"); 
				if(!idCin.equals("")) {
					getit = cinService.getById(Long.valueOf(idCin));
					System.out.println("getit = "+getit.getId()); 
				}
				//System.out.println("\n\n getIt = "+getit.getNom()+" "+getit.getPrenom());
				String nomComplet = getit.getNom()+" "+getit.getPrenom();
				System.out.println("\n\n convertToStringDate === " + getit.getDateNaissance());
				String dateNaissance = GlobalHelper.convertToStringDate(getit.getDateNaissance()); 
				
				listePromotionDetailForm.setDateAjout(GlobalHelper.getCurrentDate());
				listePromotionDetailForm.setNomComplet(nomComplet);
				listePromotionDetailForm.setDateNaissance(dateNaissance);
				listePromotionDetailForm.setLieuNaissance(getit.getLieuNaissance()); 
			}
		
		listePromotionDetailForm.setCin(getit); 
		listePromotionDetailForm.setListePromotion(listePromotion1);
		 System.out.println("\n\n .......................................................\n"
		 		+ " listePromotionDetailForm = "+listePromotionDetailForm.getId());
		listesSaved = listePromotionDetailService.saveOrUpdateListePromotionDetailForm(listePromotionDetailForm);
		//System.out.println("\n\n\n GEGE ===== " + listesSaved.getNomComplet());
		
		//Mis en historique
		ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Un étudiant admis \"" + listesSaved.getNomComplet().toUpperCase() + " à la "+listesSaved.getListePromotion().getNomPromotion() + " de " + listesSaved.getListePromotion().getListesDiplome().getEcole()+"\""));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	//fin historique	

		}catch(Exception er) {
			model.addAttribute("error", er);
			er.printStackTrace();
			return "pages/erreur/505"; 
			//er.printStackTrace();
		}
		if(newCIN!=0) {
			System.out.println("\n\n String URL = redirect:/showPromoDetail/"+listesSaved.getListePromotion().getSessionSortie()+"/"+listesSaved.getListePromotion().getListesDiplome().getId()+"/newCIN-"+newCIN);
			return "redirect:/showPromoDetail/"+listesSaved.getListePromotion().getSessionSortie()+"/"+listesSaved.getListePromotion().getListesDiplome().getId()+"/newCIN-"+newCIN;	
		}
		System.out.println("\n\n String URL 2  = redirect:/showPromoDetail/"+listesSaved.getListePromotion().getSessionSortie()+"/"+listesSaved.getListePromotion().getListesDiplome().getId());
		return "redirect:/showPromoDetail/"+listesSaved.getListePromotion().getSessionSortie()+"/"+listesSaved.getListePromotion().getListesDiplome().getId();		
	}
	
	@GetMapping({"/showPromoDetail/{session2}/{id}", "/showPromoDetail/{session2}/{id}/newCIN-{newCIN}"})
	public String ajoutPromo(@PathVariable String session2, @PathVariable String id, Model model, @PathVariable(required=false) Long newCIN) {
		//modication
		if(session.getAttribute("isConnected")==null) {
			model.addAttribute("errorlogin", "4");
			return "pages/login";
		}	
		ListePromotion listePromotion = listePromotionService.getByIdDiplomeAndSession(Long.valueOf(id), session2);
		if(listePromotion==null) {
			return "redirect:/error404/listProm";	
		}
		List<ListePromotionDetail> listePromotionDetails = listePromotionDetailService.getDetailByIdListePromotion(listePromotion.getId()); ///Listes des admis by idPromotion
		List<String> mentions = GlobalHelper.getMentionList(); 
		String ecole = listePromotion.getListesDiplome().getEcole();
		try {
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
			List<ListesDiplome> listeDiploma = listesDiplomeService.findDiplomeByEcole(ecole);
			
			if(newCIN!=null ) {
				model.addAttribute("newCIN", newCIN);
			}
			model.addAttribute("listeDiploma", listeDiploma);
			model.addAttribute("annees", annee);
			model.addAttribute("ecole", ecole);
			model.addAttribute("mentions", mentions);
			model.addAttribute("listePromotion", listePromotion);
			model.addAttribute("listePromotionDetails", listePromotionDetails);
			model.addAttribute("listePromotionDetailForm", new ListePromotionDetailForm());
			model.addAttribute("listePromotionForm", this.listePromotionToListePromotionForm.convert(listePromotion));
		} catch (Exception e) { 
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}
		return "pages/listePromotion/listPromDet";		
	}
	
	@GetMapping("/promotion/delete/{id}")
	 public String deletePromo(@PathVariable String id , Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		int error = 0;
		try {
			error = this.deleteAllChild(id);
		}catch(Exception e) {
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}
		return "redirect:/listProm/warning/"+error;
	 }
	 @GetMapping("/editAdmis/{id}")
	 public String editAdmis(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 ListePromotionDetail admis = listePromotionDetailService.getById(Long.valueOf(id));
		 List<String> mentions = GlobalHelper.getMentionList();
		 try{
			if(admis==null) {
				return "redirect:/error404/listPromDet";	
			}
			ListePromotion listePromotion = listePromotionService.getById(Long.valueOf(admis.getListePromotion().getId()));
				
			ListePromotionDetailForm temp = listePromotionDetailToListePromotionDetailForm.convert(admis);
			model.addAttribute("listePromotionDetailForm", temp);
			model.addAttribute("mentions", mentions);
			model.addAttribute("diplomaDetail", admis);
			model.addAttribute("listePromotion", listePromotion);
		 }catch(Exception e) {
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
			// e.printStackTrace();
		 }
		 
		 return "pages/listePromotion/editAdmis";
	 }
	 @GetMapping("/showAdmis/{id}")
	 public String showAdmis(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		 ListePromotionDetail list = listePromotionDetailService.getById(Long.valueOf(id));
		 try{
			 
			 model.addAttribute("diplomaDetail", list);
			 
		 }catch(Exception e) {
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
			 //e.printStackTrace();
		 }
		 if(list==null) {
			return "redirect:/error404/listProm";	
		 } 
		 return "pages/listePromotion/showAdmis";
	 }
		
	@GetMapping(value="/importExcel/{id}")
	public String testImport(@PathVariable String id, @RequestParam(required=true)String  filename, Model model) {
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		ListePromotion listePromotion = listePromotionService.getById(Long.valueOf(id));
		 if(listePromotion == null) {
			return "redirect:/error404/listPromDet/"+id;	
		 } 
		
		MyReaderExcel testExcel = new MyReaderExcel();
		try {
			//INITIALISATION
			List<ListePromotionDetailForm> list = testExcel.getPromotionByExcel(filename, listePromotion);
			
			List<ListePromotionDetail> listePromotionDetails = listePromotionDetailService.getDetailByIdListePromotion(Long.valueOf(id));
			List<String> mentions = GlobalHelper.getMentionList(); 
			String ecole = listePromotion.getListesDiplome().getEcole(); 
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
			List<ListesDiplome> listeDiploma = listesDiplomeService.findDiplomeByEcole(ecole);
 	
			//Importation
			for(ListePromotionDetailForm temp : list) {
				//System.out.println("" + temp.getId() + " | " + temp.getListePromotion().getNomPromotion() + " | " + temp.getNumeroMatricule() + " | " + temp.getNomComplet()+ " | " + temp.getDateNaissance()+ " | " + temp.getLieuNaissance()+ " | " + temp.getMention());
				CIN cin = insertNewCIN(temp.getNomComplet(), temp.getDateNaissance(), temp.getLieuNaissance(), temp.getCin().getAdresseActuelle());
			//	System.out.println("\n DATE NAISSANCE CIN = "+temp.getLieuNaissance());
				temp.setDateAjout(GlobalHelper.getCurrentDate());
				temp.setCin(cin);
			/*	System.out.println("\n CIN getDateNaissance= "+temp.getCin().getDateNaissance());
				System.out.println("\n CIN getLieuNaissance= "+temp.getCin().getLieuNaissance());
				System.out.println("\n CIN getNom= "+temp.getCin().getNom()+" "+temp.getCin().getPrenom());
				System.out.println("\n CIN getNomPromotion= "+temp.getListePromotion().getNomPromotion());
				System.out.println("\n CIN getNomComplet= "+temp.getNomComplet());*/
				ListePromotionDetail listesSaved = listePromotionDetailService.saveOrUpdateListePromotionDetailForm(temp);
				
				model.addAttribute("successImport", "Félicitation, l'importation du fichier: \""+ filename +"\" est finie !!!");	
				
				//Mis en historique
				 ActiviteRecent historique = new ActiviteRecent(); 
				 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "(par import Excel) Un  étudiant admis \"" + listesSaved.getNomComplet().toUpperCase() + " à la "+listesSaved.getListePromotion().getNomPromotion() + " de " + listesSaved.getListePromotion().getListesDiplome().getEcole()+"\""));
				 	historique.setDateAjout(GlobalHelper.getCurrentDate());
				 	activiteRecentService.saveOrUpdate(historique);
			 	 //fin historique
			}
			
			//Model View
			model.addAttribute("listeDiploma", listeDiploma);
			model.addAttribute("annees", annee);
			model.addAttribute("ecole", ecole);
			model.addAttribute("mentions", mentions);
			model.addAttribute("listePromotion", listePromotion);
			model.addAttribute("listePromotionDetails", listePromotionDetails);
			model.addAttribute("listePromotionDetailForm", new ListePromotionDetailForm());
			model.addAttribute("listePromotionForm", this.listePromotionToListePromotionForm.convert(listePromotion));
			
		} catch (Exception e) { 
			//e.printStackTrace();
			model.addAttribute("errorImport", "Fichier incorrect (suggestion: vérifier si le fichier est bien sous l'extension .xls où .xlsx; Où s'il n'inclut pas une liste d'étudiants admis) Veuillez recommencez svp!!!");	
		}
		return "redirect:/showPromoDetail/"+listePromotion.getSessionSortie()+"/"+listePromotion.getListesDiplome().getId(); 
	}
	
	@GetMapping("/admis/delete/{id}")
	 public String deleteAdmis(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		ListePromotionDetail listesSaved = listePromotionDetailService.getById(Long.valueOf(id));

		listePromotionDetailService.delete(Long.valueOf(id));
		
		//Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(2, "L'étudiant \""+listesSaved.getNomComplet()+" qui était admis en "+listesSaved.getListePromotion().getListesDiplome().getFiliere()+" "
		 				+listesSaved.getListePromotion().getListesDiplome().getOption()
		 				+"\", dans l'établissement : "
		 				+listesSaved.getListePromotion().getListesDiplome().getEcole()
		 				+", session : "+listesSaved.getListePromotion().getSessionSortie()));
		 	
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique
		 	
	 		return "redirect:/showPromoDetail/"+listesSaved.getListePromotion().getSessionSortie()+"/"+listesSaved.getListePromotion().getListesDiplome().getId(); 
  	}
	
	public int deleteAllChild(String id) throws Exception {
		 ListePromotion listesSaved = listePromotionService.getById(Long.valueOf(id));
		 int ret = 0;
		 try {
			 
			 if(!hasChilds(id)) {
				 listePromotionService.delete(Long.valueOf(listesSaved.getId()));
				 //Mis en historique
				 	ActiviteRecent historique = new ActiviteRecent(); 
					 	historique.setDefinition( GlobalHelper.getQueryStringActivities(2, "Le promotion du nom \""+listesSaved.getNomPromotion()+" session: "+listesSaved.getSessionSortie()+"\""));
					 	historique.setDateAjout(GlobalHelper.getCurrentDate());
					 	activiteRecentService.saveOrUpdate(historique);
			 	 //fin historique
			 }else {
				 List<ListePromotionDetail> listeAdmisChild = listePromotionDetailService.getDetailByIdListePromotion(listesSaved.getId());
				 for(ListePromotionDetail child : listeAdmisChild) {
					 //Suppression entity mère
					 listePromotionDetailService.delete(Long.valueOf(child.getId()));
					 
					 //Mis en historique
					 ActiviteRecent historique = new ActiviteRecent(); 
						historique.setDefinition( GlobalHelper.getQueryStringActivities(2, "L'étudiant \""+child.getNomComplet()+" qui était admis en "+child.getListePromotion().getListesDiplome().getFiliere()+" "
				 				+child.getListePromotion().getListesDiplome().getOption()
				 				+"\", dans l'établissement : "
				 				+child.getListePromotion().getListesDiplome().getEcole()
				 				+", session : "+child.getListePromotion().getSessionSortie()));
						historique.setDateAjout(GlobalHelper.getCurrentDate());
					 	activiteRecentService.saveOrUpdate(historique);
				 	 //fin historique
				 	  
				 }
				 listePromotionService.delete(Long.valueOf(listesSaved.getId()));
				 //Mis en historique
				 	ActiviteRecent historique = new ActiviteRecent(); 
					 	historique.setDefinition( GlobalHelper.getQueryStringActivities(2, "Le promotion du nom \""+listesSaved.getNomPromotion()+" session: "+listesSaved.getSessionSortie()+"\""));
					 	historique.setDateAjout(GlobalHelper.getCurrentDate());
					 	activiteRecentService.saveOrUpdate(historique);
			 	 //fin historique
				 ret = listeAdmisChild.size();
			 }
		 
		 } catch (Exception e) { 
			 throw e;
			//e.printStackTrace();
		 } 
		 
		 return ret; 		
	}
	public boolean hasChilds(String id) throws Exception {
		 ListePromotion listesSaved = listePromotionService.getById(Long.valueOf(id));
		 boolean ret = false;
		
		try {
			 if(listesSaved == null) {
				throw new Exception("Error : the ListePromotion with id : "+id+" is invalid!");	
			 } 
			 
			 List<ListePromotionDetail> listeAdmisChild = listePromotionDetailService.getDetailByIdListePromotion(listesSaved.getId());
			 if(listeAdmisChild.size()!=0) {
				 ret = true;
			 }
			 
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	
	public CIN insertNewCIN(String nomComplet, String dateNaissance, String lieuNaissance, String adresseActuelle) {
		CIN ret = null;
		try {
			if(!nomComplet.equals("") && !dateNaissance.equals("") && !lieuNaissance.equals("")) {
				String[] tab1 = nomComplet.split(" ");
				CINForm cinForm = new CINForm();
				cinForm.setNom(tab1[0]);
				cinForm.setPrenom(tab1[1]); 
				/*System.out.println("\n\n ");
				System.out.println("Date dateNaissance = " + dateNaissance);
				System.out.println("Date formatDate = " + GlobalHelper.formatDate(dateNaissance));
				System.out.println("convert dateNaissance = " + GlobalHelper.convertStringToDate(GlobalHelper.formatDate(dateNaissance).toString()));*/
				cinForm.setDateNaissance( GlobalHelper.convertStringToDate(GlobalHelper.formatDate(dateNaissance).toString()));
				cinForm.setLieuNaissance(lieuNaissance);
				cinForm.setAdresseActuelle(adresseActuelle);
				cinForm.setDateAjout(GlobalHelper.getCurrentDate());
				cinForm.setNumeroCIN("");
				cinForm.setFonction("");
				cinForm.setLieuTravail("");
				cinForm.setDateDelivrance(GlobalHelper.convertStringToDate(GlobalHelper.getCurrentDate()));
				cinForm.setLieuDelivrance("(Temporaire)"); 
				cinForm.setNationalite("Malagasy");
				cinForm.setPhoto("".getBytes());
				
				ret = cinService.saveOrUpdateCINForm(cinForm); 
				
				 //Mis en historique
				 ActiviteRecent historique = new ActiviteRecent(); 
				 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Un CIN "+ret.getNom().toUpperCase()+" "+ret.getPrenom()));
				 	historique.setDateAjout(GlobalHelper.getCurrentDate());
				 	activiteRecentService.saveOrUpdate(historique);
			 	 //fin historique0
			}
		}catch(Exception e) {
			throw e;
			//e.printStackTrace();
		}
		return ret;
	 }
	 public void initialListePromotion() {
			this.listeProms = listePromotionService.listAll();
	 }
	 
	 public ListePromotionDetail getAdmisByFicheDemande(ListePromotion listeProm, FicheDemande fiche) throws Exception {
		 ListePromotionDetail ret = null;
		 List<ListePromotionDetail> getListProm = null;
		 FicheDemandeDetail demandeDetail = null;
		 try {
			 demandeDetail = ficheDemandeDetailService.getFicheDemandeByFiche(fiche.getId());
			 /*System.out.println("\n\n***********************************************************************\n"
				 		+ " ListePromotion getId() = "+listeProm.getId());*/
			 getListProm = listePromotionDetailService.getDetailByIdListePromotion(listeProm.getId());
			
			 if(getListProm.size()!=0) {
				 for(ListePromotionDetail temp : getListProm ) {
					 //System.out.println(" listePromotionsDetail recent ajout: COMPARER"); 
					 if(fiche.getListesDiplome().getId()==temp.getListePromotion().getListesDiplome().getId() && demandeDetail.getAnneeDeb().equals(temp.getListePromotion().getSessionSortie())) { ///Check conformité promotion et la fiche
						 		
						 if(fiche.getCin().compareTo(temp.getCin())) {
							 //System.out.println("Compare CIN = "+temp);
							 ret = temp;
							 break;
						 }
					 }
				 }
			 }
		 }catch(Exception e) {
			 throw e;
			 //e.printStackTrace();
		 }
		 //System.out.print("ret ==  "+ret);
		 return ret;
	 }
	 public ListePromotion getListePromotionByFiche(FicheDemande ficheDemande) throws Exception{
		 ListePromotion ret = null;
		 FicheDemandeDetail demandeDetail = null;
		 try {
			 if(ficheDemande==null) {
				 throw new Exception("Error at getListePromotionByFiche method's ListePromotionController: ficheDemande.Id is invalid or null !!! ");
			 }
			 demandeDetail = ficheDemandeDetailService.getFicheDemandeByFiche(ficheDemande.getId());
			 //System.out.println("\n\n demandeDetail = "+demandeDetail.getId());
			 if(demandeDetail!=null) {
				 System.out.println("\n\n getByIdDiplomeAndSession = "+ficheDemande.getListesDiplome().getEcole()+" session: "+demandeDetail.getAnneeDeb());
				 ret = listePromotionService.getByIdDiplomeAndSession(ficheDemande.getListesDiplome().getId(), demandeDetail.getAnneeDeb());
			 }
			 
		 }catch(Exception e) {
			 throw e;
			 //e.printStackTrace();
		 }
		 return ret;
	 }
	 public List<FicheDemande> refreshAllStatusRejet() throws Exception {
		 //ret va contenir le nombre de ListePromotionDetail(Admis) qui vont etre changer en false
		 List<FicheDemande> ret = new ArrayList<FicheDemande>();
		 List<FicheDemande> allRejet = ficheDemandeService.getFicheDemandeByStatusRejet();
		 
		 //On va prendre les ficheDemande avec statusRejet true 
		 try {
			 if(allRejet.size()==0) {
				 return ret;
			 }
			 for(FicheDemande fiche : allRejet) {
				 
				 ListePromotion listeProm = this.getListePromotionByFiche(fiche);
				 if(listeProm==null) {
					 //System.out.println("\n\n listeProm is NULL = "+fiche.getId()); 
				 }
				 ListePromotionDetail temp = null;
				 //System.out.println(" listeProm = "+listeProm.getNomPromotion());
				 if(listeProm!=null) {
					 temp = this.getAdmisByFicheDemande(listeProm, fiche);
				 }
				
				 if(temp!=null) {//
					 //System.out.println("\n fiche resolue = "+fiche.getId());
					 ret.add(fiche);
				 }
			 }
			
		 }catch(Exception e) {
			 throw e;
			 //e.printStackTrace();
		 }
		 return ret;
	 }
	 
	 public Integer[] getEffectifTotal(List<ListePromotion> listeProm) {
		 Integer[] ret = null;
		 try {
			 if(listeProm.size()!=0) {
				 ret = new Integer[listeProm.size()];
				 int i = 0;
				 for(ListePromotion temp : listeProm) {
					 Integer total = listePromotionDetailService.getDetailByIdListePromotion(temp.getId()).size();
					 ret[i] = total;
					 i++;
				 }
			 }
			 
		 }catch(Exception e) {
			 throw e;
		 }
		 return ret;
	 }
}
