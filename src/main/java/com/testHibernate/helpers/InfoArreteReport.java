package com.testHibernate.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testHibernate.model.equivalence.InfoArrete;
 

public class InfoArreteReport {

	public List<Map<String, ?>> getReportInfoArrete( InfoArrete info){
		List<Map<String, ?>> ret = new ArrayList<Map<String, ?>>(); 
		 
		
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", info.getId());
			map.put("arreteEqRef", info.getArreteEqRef());
			map.put("numeroArrete", info.getNumeroArrete());
			map.put("dateSortieArrete", info.getDateSortieArrete());
			map.put("decretsArrete", info.getDecretsArrete());
			map.put("titreTableau", info.getTitreTableau());
			map.put("organismePaysTableau", info.getOrganismePaysTableau());
			map.put("cadreTableau", info.getCadreTableau());
			map.put("echelleTableau", info.getEchelleTableau());
			map.put("categorieTableau", info.getCategorieTableau());
			map.put("diplomeEquivalenceDecret", info.getDiplomeEquivalentDecret());
			map.put("copsFonctionnaireDecret", info.getCopsFonctionnaireDecret());
			map.put("indiceDecret", info.getIndiceDecret());
			map.put("dateSignature", info.getDateSignature());
			map.put("nomMinistreSignature", info.getNomMinistreSignature());
			
			ret.add(map);
		 
		return ret;
	}
}
