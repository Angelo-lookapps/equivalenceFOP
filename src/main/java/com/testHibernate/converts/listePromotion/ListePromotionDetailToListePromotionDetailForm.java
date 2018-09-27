package com.testHibernate.converts.listePromotion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;

@Component
public class ListePromotionDetailToListePromotionDetailForm implements Converter<ListePromotionDetail, ListePromotionDetailForm> {

    @Override
    public ListePromotionDetailForm convert(ListePromotionDetail listePromotionDetail) {
    	ListePromotionDetailForm tousListeDiplomeDetailForm = new ListePromotionDetailForm();
        
    	tousListeDiplomeDetailForm.setId(listePromotionDetail.getId());
    	tousListeDiplomeDetailForm.setNumeroMatricule(listePromotionDetail.getNumeroMatricule());
    	tousListeDiplomeDetailForm.setListePromotion(listePromotionDetail.getListePromotion());
    	tousListeDiplomeDetailForm.setNomComplet(listePromotionDetail.getNomComplet());
    	tousListeDiplomeDetailForm.setDateNaissance(listePromotionDetail.getDateNaissance());  
    	tousListeDiplomeDetailForm.setLieuNaissance(listePromotionDetail.getLieuNaissance());
    	tousListeDiplomeDetailForm.setMention(listePromotionDetail.getMention());
        
    	return tousListeDiplomeDetailForm;
    }

}
