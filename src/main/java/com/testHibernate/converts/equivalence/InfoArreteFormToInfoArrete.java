package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.model.equivalence.InfoArrete;
import com.testHibernate.model.equivalence.InfoArreteForm;
 

@Component
public class InfoArreteFormToInfoArrete implements Converter<InfoArreteForm, InfoArrete> {

    @Override
    public InfoArrete convert(InfoArreteForm infoArreteForm) {
    	InfoArrete infoArrete = new InfoArrete();
        
    	if (infoArreteForm.getId() != null  && !StringUtils.isEmpty(infoArreteForm.getId())) {
    		infoArrete.setId(new Long(infoArreteForm.getId()));
        }
    	 
    	infoArrete.setArreteEqRef(infoArreteForm.getArreteEqRef());
    	infoArrete.setNumeroArrete(infoArreteForm.getNumeroArrete());
    	infoArrete.setDateSortieArrete(infoArreteForm.getDateSortieArrete());
    	infoArrete.setDecretsArrete(infoArreteForm.getDecretsArrete());
    	infoArrete.setTitreTableau(infoArreteForm.getTitreTableau());
    	infoArrete.setOrganismePaysTableau(infoArreteForm.getOrganismePaysTableau());
    	infoArrete.setCadreTableau( infoArreteForm.getCadreTableau());
    	infoArrete.setEchelleTableau( infoArreteForm.getEchelleTableau());
    	infoArrete.setCategorieTableau( infoArreteForm.getCategorieTableau());
    	
    	infoArrete.setNiveauRefDecret( infoArreteForm.getNiveauRefDecret());
    	infoArrete.setDiplomeEquivalentDecret( infoArreteForm.getDiplomeEquivalentDecret());
    	infoArrete.setCorpsFonctionnaireDecret( infoArreteForm.getCorpsFonctionnaireDecret());
    	infoArrete.setIndiceDecret( infoArreteForm.getIndiceDecret());
    	infoArrete.setDateSignature(infoArreteForm.getDateSignature());
		infoArrete.setNomMinistreSignature( infoArreteForm.getNomMinistreSignature());
		 
        return infoArrete;
    }

}
