package com.testHibernate.converts.demande;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.demande.FicheDemandeDetail;
import com.testHibernate.model.demande.FicheDemandeDetailForm; 


@Component
public class DemandeDetailFormToDemandeDetail implements Converter<FicheDemandeDetailForm, FicheDemandeDetail> {

    @Override
    public FicheDemandeDetail convert(FicheDemandeDetailForm ficheDemandeDetailForm) {
    	FicheDemandeDetail ficheDetail = new FicheDemandeDetail();
        if (ficheDemandeDetailForm.getId() != null  && !StringUtils.isEmpty(ficheDemandeDetailForm.getId())) {
        	ficheDetail.setId(new Long(ficheDemandeDetailForm.getId()));
        }
        ficheDetail.setFicheDemande(ficheDemandeDetailForm.getFicheDemande());
        ficheDetail.setAnneeDeb(ficheDemandeDetailForm.getAnneeDeb());
        ficheDetail.setAnneeFin(ficheDemandeDetailForm.getAnneeFin());
        ficheDetail.setMention(ficheDemandeDetailForm.getMention());
        
        return ficheDetail;
    }
}
