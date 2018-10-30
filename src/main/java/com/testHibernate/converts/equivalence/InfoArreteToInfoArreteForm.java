package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.equivalence.InfoArrete;
import com.testHibernate.model.equivalence.InfoArreteForm;
 

@Component
public class InfoArreteToInfoArreteForm implements Converter<InfoArrete, InfoArreteForm> {

  @Override
    public InfoArreteForm convert(InfoArrete  infoArrete ) {
  	InfoArreteForm infoArreteForm = new InfoArreteForm();
	  	infoArrete.setId(infoArreteForm.getId());
	  	infoArrete.setArreteEqRef(infoArreteForm.getArreteEqRef());
  		infoArrete.setNumeroArrete(infoArreteForm.getNumeroArrete());
	  	infoArrete.setDateSortieArrete(infoArreteForm.getDateSortieArrete());
	  	infoArrete.setDecretsArrete(infoArreteForm.getDecretsArrete());
	  	infoArrete.setTitreTableau(infoArreteForm.getTitreTableau());
	  	infoArrete.setOrganismePaysTableau(infoArreteForm.getOrganismePaysTableau());
	  	infoArrete.setCadreTableau( infoArreteForm.getCadreTableau());
	  	infoArrete.setEchelleTableau( infoArreteForm.getEchelleTableau());
	  	infoArrete.setCategorieTableau( infoArreteForm.getCategorieTableau());
	  	infoArrete.setDiplomeEquivalentDecret( infoArreteForm.getDiplomeEquivalentDecret());
	  	infoArrete.setCorpsFonctionnaireDecret( infoArreteForm.getCorpsFonctionnaireDecret());
	  	infoArrete.setIndiceDecret( infoArreteForm.getIndiceDecret());
	  	infoArrete.setDateSignature( infoArreteForm.getDateSignature());
		infoArrete.setNomMinistreSignature( infoArreteForm.getNomMinistreSignature());
	  
	return infoArreteForm;
    }
}
