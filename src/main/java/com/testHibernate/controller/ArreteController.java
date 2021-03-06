package com.testHibernate.controller;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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
 
import com.testHibernate.converts.equivalence.ArreteEqRefFormToArreteEqRef;
import com.testHibernate.converts.equivalence.ArreteEqRefToArreteEqRefForm;
import com.testHibernate.converts.equivalence.InfoArreteToInfoArreteForm;
import com.testHibernate.helpers.DateHelper;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.helpers.InfoArreteReport;
import com.testHibernate.helpers.UtilsHelper;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;
import com.testHibernate.model.equivalence.ChampArreteEqForm;
import com.testHibernate.model.equivalence.ContentArrete; 
import com.testHibernate.model.equivalence.InfoArrete;
import com.testHibernate.model.equivalence.InfoArreteForm;
import com.testHibernate.model.equivalence.TypeArreteJasper;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.service.demande.FicheDemandeService;
import com.testHibernate.service.diplome.ListesDiplomeService;
import com.testHibernate.service.equivalence.ArreteEqRefService;
import com.testHibernate.service.equivalence.ContentArreteService;
import com.testHibernate.service.equivalence.InfoArreteService;
import com.testHibernate.service.equivalence.TypeArreteJasperService;
import com.testHibernate.service.historique.ActiviteRecentService;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource; 

@Controller
public class ArreteController {
	
	 private ArreteEqRefService arreteEqRefService;
	 private ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm; 
	 private ListesDiplomeService listesDiplomeService;
	 private TypeArreteJasperService typeArreteService;
	 List<ArreteEqRef> arretes ;
	 private InfoArreteToInfoArreteForm infoArreteToInfoArreteForm;
	 
	 
	 private FicheDemandeService demandeService;
	 
	 private ActiviteRecentService activiteRecentService;
	 private InfoArreteService infoArreteService;
	 private InfoArreteReport infoArreteReport = new InfoArreteReport() ;
	 @Autowired
	 public void setInfoArreteToInfoArreteForm(InfoArreteToInfoArreteForm infoArreteToInfoArreteForm) {
		this.infoArreteToInfoArreteForm = infoArreteToInfoArreteForm;
	 }
	 @Autowired
	 public void setFicheDemandeService(FicheDemandeService demandeService) {
		this.demandeService = demandeService;
	 }
	 @Autowired
	 public void setTypeArreteJasperService(TypeArreteJasperService typeArreteService) {
		this.typeArreteService = typeArreteService;
	 }
	 //@Autowired
	// private HttpServletResponse httpServletResponse;
	 
	 @Autowired
	 public void setInfoArreteService(InfoArreteService infoArreteService) {
		this.infoArreteService = infoArreteService;
	 }
	 
	 
	 @Autowired
	 public void setActiviteRecentService(ActiviteRecentService activiteRecentService) {
		this.activiteRecentService = activiteRecentService;
	 }
	 private ContentArreteService contentArreteService;
	 @Autowired
	 public void setContentArreteService(ContentArreteService contentArreteService) {
		this.contentArreteService = contentArreteService;
		
	 } 
	 @Autowired
	 public void setArreteEqRefFormToArreteEqRef(ArreteEqRefFormToArreteEqRef arreteEqRefFormToArreteEqRef) {
	 }
	 
	
	 private HttpSession session;
	 int nombreLigneMax = 10;
	 @Autowired
	 public void setSession(HttpSession session) {
		this.session = session;
	 }

	@Autowired
	public void setListesDiplomeService(ListesDiplomeService listesDiplomeService) {
		this.listesDiplomeService = listesDiplomeService;
	}
	 
	@Autowired
	public void setArreteEqRefService(ArreteEqRefService arreteEqRefService) {
		this.arreteEqRefService = arreteEqRefService;
	}
	
	@Autowired
	public void setArreteEqRefToArreteEqRefForm(ArreteEqRefToArreteEqRefForm arreteEqRefToArreteEqRefForm) {
		this.arreteEqRefToArreteEqRefForm = arreteEqRefToArreteEqRefForm;
	}
	
	//Equivalence
	@PostMapping("/saveArrete")
	public String ajoutArrete(@Valid  @ModelAttribute ArreteEqRefForm arreteEqRefForm , @RequestParam String listeDiplome, BindingResult bindingResult, Model model) {
		
		ArreteEqRef listesSaved = null;
		if(bindingResult.hasErrors()){
			return "redirect/error404/listArrete";
		}
		if(session.getAttribute("isConnected")==null) {
			model.addAttribute("errorlogin", "4");
			return "pages/login";
		}	
		try {
			listesSaved = arreteEqRefService.getArreteByIdDiplome(Long.valueOf(listeDiplome));
			
		if(listesSaved!=null) {
			return "redirect:/listArrete/isExist-"+listesSaved.getId();
		}
		 
		arreteEqRefForm.setListesDiplome(listesDiplomeService.getById(Long.valueOf(listeDiplome)));
		
		listesSaved = arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
		//Mis en historique
		 ActiviteRecent historique = new ActiviteRecent(); 
		 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "une nouvelle arrêté avec comme titre : \""+listesSaved.getTitre()+" année sortie: "+listesSaved.getAnneeSortie()+"\""));
		 	historique.setDateAjout(GlobalHelper.getCurrentDate());
		 	activiteRecentService.saveOrUpdate(historique);
	 	 //fin historique
		 	
		//initialisation ContentArrete
		ContentArrete content = new ContentArrete(); 
		content.setArreteEqRef(listesSaved);
		content.setDateAjout(GlobalHelper.getCurrentDate());
		ContentArrete temp = this.contentArreteService.saveOrUpdate(content);
		arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate());
		}catch(Exception e) {
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
		//	e.printStackTrace();
		}
		return "redirect:/newArrete/" + listesSaved.getId();		
	}
	//Equivalence
	@PutMapping("/updateArrete/{id}")
	public String updateArrete(@Valid @ModelAttribute ArreteEqRefForm arreteEqRefForm, @RequestParam String listeDiplome , @PathVariable String id, BindingResult bindingResult, Model model) {
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		ArreteEqRef updateEntity = null;
		if(bindingResult.hasErrors()){
			return "pages/equivalence/listArrete";
		}
		try{ 
			arreteEqRefForm.setListesDiplome(listesDiplomeService.getById(Long.valueOf(listeDiplome)));
			arreteEqRefForm.setDateAjout(GlobalHelper.getCurrentDate()); 
			updateEntity= arreteEqRefService.saveOrUpdateArreteEqRefForm(arreteEqRefForm);
		}catch(Exception ex) {
			model.addAttribute("error", ex.getMessage());
 			return "pages/erreur/505"; 
			//ex.printStackTrace();
		}
		return "redirect:/newArrete/" + updateEntity.getId();		
	}
	
	@GetMapping("/newArrete/{id}")
	public String newArrete(@PathVariable String id, Model model){
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		try { 
			 
			ArreteEqRef listesSaved = arreteEqRefService.getById(Long.valueOf(id));
			//ContentArrete contentArrete = contentArreteService.getContentByArrete(Long.valueOf(id))!=null ? contentArreteService.getContentByArrete(Long.valueOf(id)) : null;
				System.out.println("ID = "+id);
				System.out.println(" value idInfo= "+infoArreteService.getArreteByIdArrete(Long.valueOf(id)).getId());
			InfoArrete infoArrete = (infoArreteService.getArreteByIdArrete(Long.valueOf(id))!=null) ? infoArreteService.getArreteByIdArrete(Long.valueOf(id)) :  new InfoArrete();
				System.out.println(" value infoArrete= "+infoArrete.getId());
			
			InfoArreteForm infoArreteForm = infoArreteToInfoArreteForm.convert(infoArrete);
				System.out.println(" value infoArreteForm= "+infoArreteForm.getId());
			if(listesSaved==null) {
				return "redirect:/error404/listArrete";	
			}
			ArreteEqRefForm arreteEqRefForm = (listesSaved!=null) ? this.arreteEqRefToArreteEqRefForm.convert(listesSaved) : new ArreteEqRefForm();
			 	
			//List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
			List<String> listEcole = listesDiplomeService.getAllEcole();
			List<Integer> annee = DateHelper.getAnneeList(1920, 2022);
			String contentArticle = GlobalHelper._ArticleContent;
			
			model.addAttribute("arreteEqRef", listesSaved);
			model.addAttribute("annees", annee);
			model.addAttribute("listEcole", listEcole);
			//model.addAttribute("listeDiploma", listeDiploma);
			model.addAttribute("arreteEqRefForm", arreteEqRefForm);
			model.addAttribute("idArrete", arreteEqRefForm!=null ? arreteEqRefForm.getId().toString() : "");
			 
			//model.addAttribute("contentArticle", contentArticle);
			//model.addAttribute("contentArrete",  contentArrete);
			model.addAttribute("champArreteEqForm", new ChampArreteEqForm());
			model.addAttribute("infoArreteForm", infoArreteForm);
			System.out.println("INFO ARRETE === "+infoArreteForm.getNumeroArrete());
			System.out.println(" "+infoArreteForm.getId());
		} catch (Exception e) {
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}
		 
		return "pages/equivalence/newArrete";
		 
		
	
	}
	
	@GetMapping("/checkArrete")
	public String matchArrete() {
		return "pages/equivalence/checkArrete";		
	}
	
	@GetMapping({"/listArrete", "/listArrete/isExist-{idArrete}", "/listArrete/page-{page}"})
	public String listArrete(@PathVariable(required=false) Optional<Integer> idArrete, @PathVariable(required=false) Optional<Integer> page, Model model) {
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		try {	
				List<ListesDiplome> listeDiploma = listesDiplomeService.listAll();
				List<String> listEcole = listesDiplomeService.getAllEcole();
				 
				List<Integer> annee = DateHelper.getAnneeList(1920, 2022);
				
				if(idArrete.isPresent()) {
					model.addAttribute("isExistArrete", idArrete.get());
				}
				initialListeArrete();
				List<ArreteEqRef> arreteList = arreteEqRefService.pagination(1, nombreLigneMax);
					if(page.isPresent()) {
						arreteList = arreteEqRefService.pagination(page.get(), nombreLigneMax);
					}  
					try {
						Integer[] nombrePagination = GlobalHelper.getNombrePageMax(this.arretes.size(), nombreLigneMax);
						model.addAttribute("nombrePagination", nombrePagination);
					} catch (Exception e) { 
						e.printStackTrace();
					}
				List<TypeArreteJasper> typesArrete = this.typeArreteService.listAll();
				
				model.addAttribute("arreteEqRefForm", new ArreteEqRefForm());
				model.addAttribute("annees", annee);
				model.addAttribute("typesArrete", typesArrete);
				model.addAttribute("listEcole", listEcole);
				model.addAttribute("listeDiploma", listeDiploma);
				model.addAttribute("arreteList", arreteList);
		
			 
		} catch (Exception e) {
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}
		
		return "pages/equivalence/listArrete";	
		 
	}
	
	@GetMapping({"/showPDF/arrete/{idArrete}", "/showPDF/arrete/{idArrete}/demande/{idDemande}/download-{download}", "/showPDF/arrete/{idArrete}/demande/{idDemande}"})
	public void showPDFArrete(@PathVariable(required=true) String idArrete, @PathVariable(required=false) String idDemande, @PathVariable(required=false) String download, HttpServletResponse response) {
		if(idArrete!=null && idArrete.equals("") || idArrete==null ) {
			return;
		}
		
		InfoArrete infoArrete = null;
		infoArrete = infoArreteService.getArreteByIdArrete(Long.valueOf(idArrete));
		ArreteEqRef arrete = null;
		arrete = arreteEqRefService.getById(Long.valueOf(idArrete));
		if(arrete==null ) {
			return;
		}
		
		FicheDemande demande = null;
		if(idDemande!=null && !idDemande.equals("")) {
			demande = demandeService.getById(Long.valueOf(idDemande));
		} 
		infoArrete = GlobalHelper.getInitialInfoArrete(infoArrete, arrete);
		 
		List<Map<String, ?>> dataSource = infoArreteReport.getReportInfoArrete(infoArrete, demande);
		List<Map<String, ?>> one = new ArrayList<Map<String, ?>>();
		one.add(dataSource.get(0));
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(one);
		try {
			File jrxmlFile = null;
			if(!checkExistJaspers(arrete.getTypeArreteJasper().getTypeArrete())) {
				return;
			}
			if(arrete.getTypeArreteJasper().getTypeArrete().equals("1")) {
				jrxmlFile = UtilsHelper.getFilePath("reports/arrete1.jrxml");
			}else if(arrete.getTypeArreteJasper().getTypeArrete().equals("2")){
				jrxmlFile = UtilsHelper.getFilePath("reports/decret.jrxml");
			}else {
				jrxmlFile = UtilsHelper.getFilePath("reports/arrete"+arrete.getTypeArreteJasper().getTypeArrete()+".jrxml");
			}
			if(download!=null) {
				String outputName = demande.getListesDiplome().getEcole()+"-"+demande.getListesDiplome().getNiveauDiplome().getNiveau()+"-"+demande.getListesDiplome().getFiliere();
				response.setContentType("application/x-pdf");
				response.setHeader("Content-disposition",
				"inline; filename="+outputName+".pdf");
			}
		
			InputStream input = new FileInputStream(jrxmlFile);
			JasperReport jasperReport = JasperCompileManager.compileReport(input);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrDataSource);
			JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
			
			response.flushBuffer(); 
			response.getOutputStream().flush();
			response.getOutputStream().close();
			
		}catch(Exception e) {
			e.printStackTrace();
		} 
		
	}
	@PostMapping("/saveContent/{id}")
	public String saveContenu(@PathVariable String id, @Valid @ModelAttribute InfoArreteForm infoArreteForm, @RequestParam(required=false) String signatureDefaut, @RequestParam(required=false) String champDefaut, Model model, BindingResult bindingResult) {
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		InfoArrete listesSaved = null;
		 
		InfoArreteForm content = infoArreteForm; 
		ArreteEqRef temp = arreteEqRefService.getById(Long.valueOf(id));		//add arreteEqRef to entete foreign key
		temp.setStatus(true); 
		content.setArreteEqRef(temp);
		if(bindingResult.hasErrors()){
			 return "redirect:/error404/newArrete/"+id; 
		 }
	try {
			
			listesSaved = infoArreteService.saveOrUpdateInfoArreteForm(content);
			//Mis en historique
			 ActiviteRecent historique = new ActiviteRecent(); 
			 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "un contenue à l'arrêté \""+listesSaved.getArreteEqRef().getTitre()+" année sortie: "+listesSaved.getArreteEqRef().getAnneeSortie()+"\""));
			 	historique.setDateAjout(GlobalHelper.getCurrentDate());
			 	activiteRecentService.saveOrUpdate(historique);
		 	 //fin historique
						
		} catch (Exception e) {
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}
			
		return "redirect:/newArrete/" + id ;		
	}
	/*
	@PostMapping("/saveContent/{id}")
	public String saveContenu(@PathVariable String id, @Valid @ModelAttribute ContentArreteForm contentArreteForm, @RequestParam(required=false) String signatureDefaut, @RequestParam(required=false) String champDefaut, Model model, BindingResult bindingResult) {
		 if(session.getAttribute("isConnected")==null) {
			 model.addAttribute("errorlogin", "4");
			 return "pages/login";
		 }	
		ContentArrete listesSaved = null;
		if(signatureDefaut!=null ) { 
			String content = contentArreteForm.getContenu()+" "+GlobalHelper._ArticleContent; 
			if(champDefaut!=null) {
				content += " "+GlobalHelper._contentChamp;
			}
			contentArreteForm.setContenu(content); 
		}else if(signatureDefaut==null && champDefaut!=null) {
			String content = contentArreteForm.getContenu()+" "+GlobalHelper._contentChamp;  
			contentArreteForm.setContenu(content); 
		} 
		ContentArreteForm content = contentArreteForm; 
		ArreteEqRef temp = arreteEqRefService.getById(Long.valueOf(id));		//add arreteEqRef to entete foreign key
		temp.setStatus(true);
		content.setArreteEqRef(temp);
		content.setDateAjout(GlobalHelper.getCurrentDate());
		
		if(bindingResult.hasErrors()){
			 return "redirect:/error404/newArrete/"+id; 
		 }
	try {
			
			listesSaved = contentArreteService.saveOrUpdateContentArreteForm(content);
			//Mis en historique
			 ActiviteRecent historique = new ActiviteRecent(); 
			 	historique.setDefinition( GlobalHelper.getQueryStringActivities(1, "un contenue à l'arrêté \""+listesSaved.getArreteEqRef().getTitre()+" année sortie: "+listesSaved.getArreteEqRef().getAnneeSortie()+"\""));
			 	historique.setDateAjout(GlobalHelper.getCurrentDate());
			 	activiteRecentService.saveOrUpdate(historique);
		 	 //fin historique
						
		} catch (Exception e) {
			model.addAttribute("error", e);
 			return "pages/erreur/505"; 
			//e.printStackTrace();
		}
			
		return "redirect:/newArrete/" + id ;		
	}*/
	
	public void initialListeArrete() {
		if(this.arretes==null){
			this.arretes = arreteEqRefService.listAll();
		}
	 }
	public boolean checkExistJaspers(String type) {
		boolean ret = false;
		try {
			List<TypeArreteJasper> types = typeArreteService.listAll();
			for(TypeArreteJasper temp : types) {
				if(temp.getTypeArrete().toLowerCase().equals(type.toLowerCase())) {
					ret = true;break;
				}
			}
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}

}
