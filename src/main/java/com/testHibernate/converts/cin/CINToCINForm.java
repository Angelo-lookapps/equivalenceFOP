package com.testHibernate.converts.cin;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;

@Component
public class CINToCINForm implements Converter<CIN, CINForm> {
    @Override
    public CINForm convert(CIN cin) {
    	CINForm cinForm = new CINForm();
    	
    	cinForm.setId(cin.getId());
    	cinForm.setNom(cin.getNom());
    	cinForm.setPrenom(cin.getPrenom());
    	cinForm.setDateNaissance(cin.getDateNaissance());
    	cinForm.setLieuNaissance(cin.getLieuNaissance());
    	cinForm.setNumeroCIN(cin.getNumeroCIN());
    	cinForm.setAdresseActuelle(cin.getAdresseActuelle());
    	cinForm.setDateDelivrance(cin.getDateDelivrance());
    	cinForm.setLieuDelivrance(cin.getLieuDelivrance());
    	cinForm.setNationalite(cin.getNationalite());
    	cinForm.setFonction(cin.getFonction());
    	cinForm.setLieuTravail(cin.getLieuTravail());
    	cinForm.setPhoto(cin.getPhoto());
    	cin.setDateAjout(cinForm.getDateAjout()); 
    	
        return cinForm;
    }
}
