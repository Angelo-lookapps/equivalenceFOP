package com.testHibernate.converts.listeDiplomes;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils; 
 
import com.testHibernate.model.listeDiplomes.TousListeDiplomeDetail;
import com.testHibernate.model.listeDiplomes.TousListeDiplomeDetailForm;

@Component
public class TousListeDiplomeDetailFormToTousListeDiplomeDetail implements Converter<TousListeDiplomeDetailForm, TousListeDiplomeDetail> {

    @Override
    public TousListeDiplomeDetail convert(TousListeDiplomeDetailForm tousListeDiplomeDetailForm) {
    	TousListeDiplomeDetail tousListeDiplomeDetail = new TousListeDiplomeDetail();
        
    	if (tousListeDiplomeDetailForm.getId() != null  && !StringUtils.isEmpty(tousListeDiplomeDetailForm.getId())) {
    		tousListeDiplomeDetail.setId(new Long(tousListeDiplomeDetailForm.getId()));
        }
    	tousListeDiplomeDetail.setTousListeDiplome(tousListeDiplomeDetailForm.getTousListeDiplome());
    	tousListeDiplomeDetail.setNomComplet(tousListeDiplomeDetailForm.getNomComplet());
    	tousListeDiplomeDetail.setDateNaissance(tousListeDiplomeDetailForm.getDateNaissance());  
    	tousListeDiplomeDetail.setLieuNaissance(tousListeDiplomeDetailForm.getLieuNaissance());
    	tousListeDiplomeDetail.setMention(tousListeDiplomeDetailForm.getMention());
        
    	return tousListeDiplomeDetail;
    }

}
