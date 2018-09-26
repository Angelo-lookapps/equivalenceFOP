package com.testHibernate.converts.listeDiplomes;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
 
import com.testHibernate.model.listeDiplomes.TousListeDiplome;
import com.testHibernate.model.listeDiplomes.TousListeDiplomeForm;

@Component
public class TousListeDiplomeToTousListeDiplomeForm implements Converter<TousListeDiplome, TousListeDiplomeForm> {

    @Override
    public TousListeDiplomeForm convert(TousListeDiplome tousListeDiplome) {
    	TousListeDiplomeForm tousListeDiplomeForm = new TousListeDiplomeForm();
    	
    	tousListeDiplomeForm.setId(tousListeDiplome.getId());
    	tousListeDiplomeForm.setListesDiplome(tousListeDiplome.getListesDiplome());
    	tousListeDiplomeForm.setSessionSortie(tousListeDiplome.getSessionSortie());
    	tousListeDiplomeForm.setNomPromotion(tousListeDiplome.getNomPromotion());
    	
        return tousListeDiplomeForm;
    }
}
