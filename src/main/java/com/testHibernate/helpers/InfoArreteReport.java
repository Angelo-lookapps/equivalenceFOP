package com.testHibernate.helpers;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.icu.text.DecimalFormat;
import com.ibm.icu.text.SimpleDateFormat;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.equivalence.InfoArrete;
 

public class InfoArreteReport {
	DecimalFormat df = new DecimalFormat("00");
	public List<Map<String, ?>> getReportInfoArrete( InfoArrete info, FicheDemande demande) {
		List<Map<String, ?>> ret = new ArrayList<Map<String, ?>>(); 
		try{ 
			
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", info.getId()!=null ? info.getId() : "");
				map.put("numeroArrete", info.getNumeroArrete());
				
				//date sortie
				map.put("dateSortieArrete", GlobalHelper.convertToDate(info.getDateSortieArrete()));		
				map.put("jourSortieArrete", df.format(GlobalHelper.getByFormat(info.getDateSortieArrete(), "dd")));
								
				map.put("moisSortieArrete", GlobalHelper.getMoisBy(GlobalHelper.getByFormat(info.getDateSortieArrete(), "MM")));
				int annee = (info.getDateSortieArrete().getYear()+(int)1900);
				map.put("anneeSortieArrete", annee);
				map.put("anneeSortieArrete2", annee);
				
				//demande
				if(demande!=null) {
				
					map.put("nomCompletDemande", demande.getCin().getNom().toUpperCase()+"  "+demande.getCin().getPrenom().toUpperCase());
					map.put("anneeDemande",  demande.getDateRetrait().getYear()-100);
					System.out.println("anneeDEmande = = "+(demande.getDateRetrait().getYear()-100));
					String[] niveau = demande.getListesDiplome().getNiveauDiplome().getNiveau().split("/");
					String niv = "";
					if(niveau.length==2) {
						niv = niveau[1].toUpperCase();
					}else if(niveau.length==3){
						niv = niveau[0].toUpperCase();
					}else {
						niv = demande.getListesDiplome().getNiveauDiplome().getNiveau().toUpperCase();
					}
					map.put("titulaireDemande", "DIPLOME  DE  "+demande.getDiplome()+"  EN  "+demande.getListesDiplome().getFiliere().toUpperCase());
					map.put("specialiteDemande", demande.getListesDiplome().getOption().toUpperCase());
					map.put("idDemande", demande.getId());
				
				}
				if(info.getArreteEqRef().getTypeArreteJasper().getTypeArrete().equals("1")){
					System.out.println("DECRET == "+info.getTitreTableau());
					map.put("decretsArrete", info.getDecretsArrete());
					map.put("titreTableau", info.getTitreTableau());
					map.put("organismePaysTableau", info.getOrganismePaysTableau());
					map.put("cadreTableau", info.getCadreTableau());
					map.put("echelleTableau", info.getEchelleTableau());
				}else if(info.getArreteEqRef().getTypeArreteJasper().getTypeArrete().equals("2")) {
					map.put("niveauRefDecret", info.getNiveauRefDecret());
					map.put("diplomeEquivalenceDecret", info.getDiplomeEquivalentDecret());
					map.put("corpsFonctionnaireDecret", info.getCorpsFonctionnaireDecret());
					map.put("indiceDecret", info.getIndiceDecret());
				}
				map.put("categorieTableau", info.getCategorieTableau()); 
				int joursSignature =  GlobalHelper.getByFormat(info.getDateSignature(), "dd") ;
				String moisSignature = GlobalHelper.getMoisBy(GlobalHelper.getByFormat(info.getDateSignature(), "MM"));
				int anneeSignature =  GlobalHelper.getByFormat(info.getDateSignature(), "yyyy") ;
				
				String jj = df.format(joursSignature);   // 0009 
				map.put("dateSignature",  jj+" "+moisSignature+" "+anneeSignature);
				
				map.put("nomMinistreSignature", info.getNomMinistreSignature());
				
				ret.add(map);
			 
			
		}  catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return ret;
	}
}
