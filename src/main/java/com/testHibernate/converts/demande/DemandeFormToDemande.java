package com.testHibernate.converts.demande;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.demande.FicheDemandeForm;


@Component
public class DemandeFormToDemande implements Converter<FicheDemandeForm, FicheDemande> {

    @Override
    public FicheDemande convert(FicheDemandeForm ficheDemandeForm) {
    	FicheDemande fiche = new FicheDemande();
        if (ficheDemandeForm.getId() != null  && !StringUtils.isEmpty(ficheDemandeForm.getId())) {
        	fiche.setId(new Long(ficheDemandeForm.getId()));
        }
        fiche.setCin(ficheDemandeForm.getCin());
        fiche.setListesDiplome(ficheDemandeForm.getListesDiplome());
        fiche.setTelephone(ficheDemandeForm.getTelephone());
        fiche.setDiplome(ficheDemandeForm.getDiplome());
        fiche.setUtilisation(ficheDemandeForm.getUtilisation());
        fiche.setStatusEnregistrement(ficheDemandeForm.getStatusEnregistrement());
        fiche.setDateRetrait(ficheDemandeForm.getDateRetrait());
        fiche.setStatusRejet(ficheDemandeForm.getStatusRejet());
        fiche.setDateAjout(ficheDemandeForm.getDateAjout());
        return fiche;
    }
}
