package com.testHibernate.converts.listeDiplomes;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils; 

import com.testHibernate.model.listeDiplomes.TousListeDiplome;
import com.testHibernate.model.listeDiplomes.TousListeDiplomeForm;

@Component
public class TousListeDiplomeFormToTousListeDiplome implements Converter<TousListeDiplomeForm, TousListeDiplome> {

    @Override
    public TousListeDiplome convert(TousListeDiplomeForm tousListeDiplomeForm) {
    	TousListeDiplome tousListeDiplome = new TousListeDiplome();
        
    	if (tousListeDiplomeForm.getId() != null  && !StringUtils.isEmpty(tousListeDiplomeForm.getId())) {
    		tousListeDiplome.setId(new Long(tousListeDiplomeForm.getId()));
        }
    	tousListeDiplome.setListesDiplome(tousListeDiplomeForm.getListesDiplome());
    	tousListeDiplome.setSessionSortie(tousListeDiplomeForm.getSessionSortie());
    	tousListeDiplome.setNomPromotion(tousListeDiplomeForm.getNomPromotion());  
    	
        return tousListeDiplome;
    }

}
