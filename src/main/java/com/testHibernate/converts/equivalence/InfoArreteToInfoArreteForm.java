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
  	infoArreteForm.setId(infoArrete.getId());
  	infoArreteForm.setArreteEqRef(infoArrete.getArreteEqRef());
  	infoArreteForm.setNumeroArrete(infoArrete.getNumeroArrete());
  	infoArreteForm.setDateSortieArrete(infoArrete.getDateSortieArrete());
  	infoArreteForm.setDecretsArrete(infoArrete.getDecretsArrete());
  	infoArreteForm.setTitreTableau(infoArrete.getTitreTableau());
  	infoArreteForm.setOrganismePaysTableau(infoArrete.getOrganismePaysTableau());
  	infoArreteForm.setCadreTableau( infoArrete.getCadreTableau());
  	infoArreteForm.setEchelleTableau( infoArrete.getEchelleTableau());
  	infoArreteForm.setCategorieTableau( infoArrete.getCategorieTableau());
  	
  	infoArreteForm.setNiveauRefDecret( infoArrete.getNiveauRefDecret());
  	infoArreteForm.setDiplomeEquivalentDecret( infoArrete.getDiplomeEquivalentDecret());
  	infoArreteForm.setCorpsFonctionnaireDecret( infoArrete.getCorpsFonctionnaireDecret());
  	infoArreteForm.setIndiceDecret( infoArrete.getIndiceDecret());
  	infoArreteForm.setDateSignature( infoArrete.getDateSignature());
  	infoArreteForm.setNomMinistreSignature( infoArrete.getNomMinistreSignature());
	  
	return infoArreteForm;
    }
}
