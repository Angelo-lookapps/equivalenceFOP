package com.testHibernate.converts.listeDiplomes;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component; 
 
import com.testHibernate.model.listeDiplomes.TousListeDiplomeDetail;
import com.testHibernate.model.listeDiplomes.TousListeDiplomeDetailForm;

@Component
public class TousListeDiplomeDetailToTousListeDiplomeDetailForm implements Converter<TousListeDiplomeDetail, TousListeDiplomeDetailForm> {

    @Override
    public TousListeDiplomeDetailForm convert(TousListeDiplomeDetail tousListeDiplomeDetail) {
    	TousListeDiplomeDetailForm tousListeDiplomeDetailForm = new TousListeDiplomeDetailForm();
        
    	tousListeDiplomeDetailForm.setId(tousListeDiplomeDetail.getId());
    	tousListeDiplomeDetailForm.setTousListeDiplome(tousListeDiplomeDetail.getTousListeDiplome());
    	tousListeDiplomeDetailForm.setNomComplet(tousListeDiplomeDetail.getNomComplet());
    	tousListeDiplomeDetailForm.setDateNaissance(tousListeDiplomeDetail.getDateNaissance());  
    	tousListeDiplomeDetailForm.setLieuNaissance(tousListeDiplomeDetail.getLieuNaissance());
    	tousListeDiplomeDetailForm.setMention(tousListeDiplomeDetail.getMention());
        
    	return tousListeDiplomeDetailForm;
    }

}
