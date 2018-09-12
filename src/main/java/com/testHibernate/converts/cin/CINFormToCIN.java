package com.testHibernate.converts.cin;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;


@Component
public class CINFormToCIN implements Converter<CINForm, CIN> {

    @Override
    public CIN convert(CINForm cinForm) {
    	CIN cin = new CIN();
        if (cinForm.getId() != null  && !StringUtils.isEmpty(cinForm.getId())) {
            cin.setId(new Long(cinForm.getId()));
        }
        cin.setNom(cinForm.getNom());
        cin.setPrenom(cinForm.getPrenom());
        cin.setDateNaissance(cinForm.getDateNaissance());
        cin.setLieuNaissance(cinForm.getLieuNaissance());
        cin.setNumeroCIN(cinForm.getNumeroCIN());
        cin.setAdresseActuelle(cinForm.getAdresseActuelle());
        cin.setDateDelivrance(cinForm.getDateDelivrance());
        cin.setLieuDelivrance(cinForm.getLieuDelivrance());
        cin.setNationalite(cinForm.getNationalite());
        cin.setFonction(cinForm.getFonction());
        cin.setLieuTravail(cinForm.getLieuTravail());
        cin.setPhoto(cinForm.getPhoto());
        
        return cin;
    }
}
