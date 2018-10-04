package com.testHibernate.converts.demande;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.demande.FicheDemandeForm;

@Component
public class DemandeToDemandeForm implements Converter<FicheDemande, FicheDemandeForm> {
    @Override
    public FicheDemandeForm convert(FicheDemande fiche) {
    	FicheDemandeForm ficheForm = new FicheDemandeForm();
    	
    	ficheForm.setId(fiche.getId());
    	ficheForm.setCin(fiche.getCin());
    	ficheForm.setListesDiplome(fiche.getListesDiplome());
    	ficheForm.setTelephone(fiche.getTelephone());
    	ficheForm.setDiplome(fiche.getDiplome());
    	ficheForm.setUtilisation(fiche.getUtilisation());
    	ficheForm.setStatusEnregistrement(fiche.getStatusEnregistrement());
    	ficheForm.setDateRetrait(fiche.getDateRetrait());
    	ficheForm.setDateAjout(fiche.getDateAjout());
         
        return ficheForm;
    }
}
