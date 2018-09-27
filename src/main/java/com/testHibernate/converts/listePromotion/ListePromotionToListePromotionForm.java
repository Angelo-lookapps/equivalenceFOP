package com.testHibernate.converts.listePromotion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionForm;

@Component
public class ListePromotionToListePromotionForm implements Converter<ListePromotion, ListePromotionForm> {

    @Override
    public ListePromotionForm convert(ListePromotion tousListeDiplome) {
    	ListePromotionForm tousListeDiplomeForm = new ListePromotionForm();
    	
    	tousListeDiplomeForm.setId(tousListeDiplome.getId());
    	tousListeDiplomeForm.setListesDiplome(tousListeDiplome.getListesDiplome());
    	tousListeDiplomeForm.setSessionSortie(tousListeDiplome.getSessionSortie());
    	tousListeDiplomeForm.setNomPromotion(tousListeDiplome.getNomPromotion());
    	tousListeDiplomeForm.setDateAjout(tousListeDiplome.getDateAjout());
        return tousListeDiplomeForm;
    }
}
