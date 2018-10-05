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
import org.springframework.web.bind.annotation.RequestParam;

import com.testHibernate.converts.listePromotion.ListePromotionDetailToListePromotionDetailForm;
import com.testHibernate.converts.listePromotion.ListePromotionToListePromotionForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.importFile.excel.MyReaderExcel;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;
import com.testHibernate.model.listePromotion.ListePromotionForm;
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
	 
	 private HttpSession session;
	 
	 @Autowired
	 public void setSession(HttpSession session) {
		this.session = session;
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
	 

	@GetMapping({"/listProm", "/listProm/warning/{id}"})
	 public String listPromo(Model model, @PathVariable(required=false) Optional<String> id){
		List<ListePromotion> ret = this.listePromotionService.listAll();
		try {
			if(id.isPresent() && !id.get().equals("0")) {
        		model.addAttribute("deleteWarning", id.get()); 
            }else if(id.isPresent() && id.get().equals("0")) {
        		model.addAttribute("deleteWarning2", id.get()); 
            }
			
			List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
			List<String> listEcole = listesDiplomeService.getAllEcole();
			
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
			model.addAttribute("annees", annee);
			model.addAttribute("listEcole", listEcole);
			model.addAttribute("listeDiploma", listeDiploma);
		    model.addAttribute("promos", ret);
			model.addAttribute("listPromotionForm", new ListePromotionForm());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(session.getAttribute("isConnected")!=null) {
			 return "pages/listePromotion/listProm";
        }
    	model.addAttribute("errorlogin", "4");
		return "pages/login";
       // System.out.println("\n ret.Length = " + ret.size());
      
        
	 }	
	
	@PostMapping("/savePromo")
	public String ajoutPromo(@Valid  @ModelAttribute ListePromotionForm listePromotionForm , BindingResult bindingResult, Model model) {
		
		ListePromotion listesSaved = null;
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		} 
		System.out.println("\n\n Hello world \n");
		listePromotionForm.setDateAjout(GlobalHelper.getCurrentDate());
		
		System.out.println("\n\n date d'ajout = "+listePromotionForm.getDateAjout());
		
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
	public String ajoutAdmisDetail(@PathVariable String id,@Valid  @ModelAttribute ListePromotionDetailForm listePromotionDetailForm , BindingResult bindingResult, Model model) {
		ListePromotionDetail listesSaved = null;
		ListePromotion listePromotion1 = listePromotionService.getById(Long.valueOf(id));
		if(listePromotion1==null) {
			return "redirect:/error404/listProm";	
		}
		
		if(bindingResult.hasErrors()){
			return "redirect:/error505";
		}  
		try {
			
			if(!listePromotionDetailForm.getNomComplet().equals("") || !listePromotionDetailForm.getLieuNaissance().equals("") ) {
				listePromotionDetailForm.setDateAjout(GlobalHelper.getCurrentDate());
				listePromotionDetailForm.setListePromotion(listePromotion1);
				listesSaved = listePromotionDetailService.saveOrUpdateListePromotionDetailForm(listePromotionDetailForm);
				System.out.println("\n\n\n GEGE ===== " + listesSaved.getNomComplet());
				
				//Mis en historique
				ActiviteRecent historique = new ActiviteRecent(); 
				 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "Un étudiant admis \"" + listesSaved.getNomComplet().toUpperCase() + " à la "+listesSaved.getListePromotion().getNomPromotion() + " de " + listesSaved.getListePromotion().getListesDiplome().getEcole()+"\""));
				 	historique.setDateAjout(GlobalHelper.getCurrentDate());
				 	activiteRecentService.saveOrUpdate(historique);
			 	//fin historique
			}

		}catch(Exception er) {
			er.printStackTrace();
		}
		 return "redirect:/showPromoDetail/"+listesSaved.getListePromotion().getId();		
	}
	@GetMapping("/showPromoDetail/{id}")
	public String ajoutPromo( @PathVariable String id, Model model) {
		
		List<ListePromotionDetail> listePromotionDetails = listePromotionDetailService.getDetailByIdListePromotion(Long.valueOf(id));
		ListePromotion listePromotion = listePromotionService.getById(Long.valueOf(id));
		List<String> mentions = GlobalHelper.getMentionList();
		
		if(listePromotion==null) {
			return "redirect:/error404/listProm";	
		}
		//List<String> listEcole = listesDiplomeService.getAllEcole();
		String ecole = listePromotion.getListesDiplome().getEcole();
		try {
			List<Integer> annee = DateHelper.getAnneeList(1999, 2022);
			List<ListesDiplome> listeDiploma = listesDiplomeService.findDiplomeByEcole(ecole);
	 
			model.addAttribute("listeDiploma", listeDiploma);
			model.addAttribute("annees", annee);
			model.addAttribute("ecole", ecole);
			model.addAttribute("mentions", mentions);
			model.addAttribute("listePromotion", listePromotion);
			model.addAttribute("listePromotionDetails", listePromotionDetails);
			model.addAttribute("listePromotionDetailForm", new ListePromotionDetailForm());
			model.addAttribute("listePromotionForm", this.listePromotionToListePromotionForm.convert(listePromotion));
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return "pages/listePromotion/listPromDet";		
	}
	
	@GetMapping("/promotion/delete/{id}")
	 public String deletePromo(@PathVariable String id ){
		int error = 0;
		try {
			error = this.deleteAllChild(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/listProm/warning/"+error;
	 }
	 @GetMapping("/editAdmis/{id}")
	 public String editAdmis(@PathVariable String id, Model model){
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
			 e.printStackTrace();
		 }
		 if(admis==null) {
			return "redirect:/error404/listProm";	
		 } 
		 return "pages/listePromotion/editAdmis";
	 }
	 @GetMapping("/showAdmis/{id}")
	 public String showAdmis(@PathVariable String id, Model model){
		 ListePromotionDetail list = listePromotionDetailService.getById(Long.valueOf(id));
		 try{
			 
			 model.addAttribute("diplomaDetail", list);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 if(list==null) {
			return "redirect:/error404/listProm";	
		 } 
		 return "pages/listePromotion/showAdmis";
	 }
		
	@GetMapping(value="/importExcel/{id}")
	public String testImport(@PathVariable String id, @RequestParam(required=true)String  filename, Model model) {
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
				
				temp.setDateAjout(GlobalHelper.getCurrentDate());
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
			model.addAttribute("errorImport", "Fichier incorrect (suggestion: vérifier si le fichier est bien sous l'extension .xls où .xlsx; Où s'il n'inclut pas une liste d'étudiants admis) Veuillez recommencez svp!!!");	
		}
		return "redirect:/showPromoDetail/" + listePromotion.getId();
	}
	
	@GetMapping("/admis/delete/{id}")
	 public String deleteAdmis(@PathVariable String id){
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
		 	
       return "redirect:/showPromoDetail/" + listesSaved.getListePromotion().getId();
	 }
	
	public int deleteAllChild(String id) {
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
			e.printStackTrace();
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
	
}
