package com.testHibernate.converts.demande;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.demande.FicheDemandeDetail;
import com.testHibernate.model.demande.FicheDemandeDetailForm; 

@Component
public class DemandeDetailToDemandeDetailForm implements Converter<FicheDemandeDetail, FicheDemandeDetailForm> {
    @Override
    public FicheDemandeDetailForm convert(FicheDemandeDetail ficheDemandeDetail) {
    	FicheDemandeDetailForm ficheForm = new FicheDemandeDetailForm();
    	
    	ficheForm.setId(ficheDemandeDetail.getId());
    	ficheForm.setFicheDemande(ficheDemandeDetail.getFicheDemande());
    	ficheForm.setAnneeDeb(ficheDemandeDetail.getAnneeDeb());
    	ficheForm.setAnneeFin(ficheDemandeDetail.getAnneeFin());
    	ficheForm.setMention(ficheDemandeDetail.getMention());
         
        return ficheForm;
    }
}
