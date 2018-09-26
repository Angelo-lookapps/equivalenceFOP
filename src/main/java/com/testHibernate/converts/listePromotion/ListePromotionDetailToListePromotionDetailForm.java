package com.testHibernate.converts.listePromotion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;

@Component
public class ListePromotionDetailToListePromotionDetailForm implements Converter<ListePromotionDetail, ListePromotionDetailForm> {

    @Override
    public ListePromotionDetailForm convert(ListePromotionDetail tousListeDiplomeDetail) {
    	ListePromotionDetailForm tousListeDiplomeDetailForm = new ListePromotionDetailForm();
        
    	tousListeDiplomeDetailForm.setId(tousListeDiplomeDetail.getId());
    	tousListeDiplomeDetailForm.setTousListeDiplome(tousListeDiplomeDetail.getTousListeDiplome());
    	tousListeDiplomeDetailForm.setNomComplet(tousListeDiplomeDetail.getNomComplet());
    	tousListeDiplomeDetailForm.setDateNaissance(tousListeDiplomeDetail.getDateNaissance());  
    	tousListeDiplomeDetailForm.setLieuNaissance(tousListeDiplomeDetail.getLieuNaissance());
    	tousListeDiplomeDetailForm.setMention(tousListeDiplomeDetail.getMention());
        
    	return tousListeDiplomeDetailForm;
    }

}
