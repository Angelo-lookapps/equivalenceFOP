package com.testHibernate.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.equivalence.InfoArrete;
 

public class InfoArreteReport {

	public static List<Map<String, ?>> getReportInfoArrete( InfoArrete info, FicheDemande demande){
		List<Map<String, ?>> ret = new ArrayList<Map<String, ?>>(); 
		 
		
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", info.getId()!=null ? info.getId() : "");
			map.put("numeroArrete", info.getNumeroArrete());
			
			//date sortie
			map.put("dateSortieArrete", info.getDateSortieArrete());
			map.put("jourSortieArrete", info.getDateSortieArrete().getDay());
			map.put("moisSortieArrete", GlobalHelper.getMoisBy(info.getDateSortieArrete().getMonth()));
			int annee = (info.getDateSortieArrete().getYear()+(int)1900);
			map.put("anneeSortieArrete", annee);
			map.put("anneeSortieArrete2", annee-2000);
			
			//demande
			if(demande!=null) {
			
				map.put("nomCompletDemande", demande.getCin().getNom().toUpperCase()+"  "+demande.getCin().getPrenom().toUpperCase());
				map.put("anneeDemande",  demande.getDateRetrait().getYear()+1900);
				map.put("titulaireDemande", "DIPLOME  DE  "+demande.getListesDiplome().getNiveauDiplome().getNiveau().toUpperCase()+"  EN  "+demande.getListesDiplome().getFiliere().toUpperCase());
				map.put("specialiteDemande", demande.getListesDiplome().getOption().toUpperCase());
				map.put("idDemande", demande.getId());
			
			}
			
			if(info.getArreteEqRef().getTypeArreteJasper().equals("1")){
				map.put("decretsArrete", info.getDecretsArrete());
				map.put("titreTableau", info.getTitreTableau());
				map.put("organismePaysTableau", info.getOrganismePaysTableau());
				map.put("cadreTableau", info.getCadreTableau());
				map.put("echelleTableau", info.getEchelleTableau());
			}else if(info.getArreteEqRef().getTypeArreteJasper().equals("2")) {
				map.put("diplomeEquivalenceDecret", info.getDiplomeEquivalentDecret());
				map.put("corpsFonctionnaireDecret", info.getCorpsFonctionnaireDecret());
				map.put("indiceDecret", info.getIndiceDecret());
			}
			map.put("categorieTableau", info.getCategorieTableau()); 
			map.put("dateSignature", info.getDateSignature());
			map.put("nomMinistreSignature", info.getNomMinistreSignature());
			
			ret.add(map);
		 
		return ret;
	}
}
