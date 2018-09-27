package com.testHibernate.converts.listePromotion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionForm;

@Component
public class ListePromotionFormToListePromotion implements Converter<ListePromotionForm, ListePromotion> {

    @Override
    public ListePromotion convert(ListePromotionForm tousListeDiplomeForm) {
    	ListePromotion tousListeDiplome = new ListePromotion();
        
    	if (tousListeDiplomeForm.getId() != null  && !StringUtils.isEmpty(tousListeDiplomeForm.getId())) {
    		tousListeDiplome.setId(new Long(tousListeDiplomeForm.getId()));
        }
    	tousListeDiplome.setListesDiplome(tousListeDiplomeForm.getListesDiplome());
    	tousListeDiplome.setSessionSortie(tousListeDiplomeForm.getSessionSortie());
    	tousListeDiplome.setNomPromotion(tousListeDiplomeForm.getNomPromotion());  
    	tousListeDiplome.setDateAjout(tousListeDiplomeForm.getDateAjout()); 
        return tousListeDiplome;
    }

}
