package com.testHibernate.converts.listePromotion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;

@Component
public class ListePromotionDetailFormToListePromotionDetail implements Converter<ListePromotionDetailForm, ListePromotionDetail> {

    @Override
    public ListePromotionDetail convert(ListePromotionDetailForm listePromotionDetailForm) {
    	ListePromotionDetail tousListeDiplomeDetail = new ListePromotionDetail();
        
    	if (listePromotionDetailForm.getId() != null  && !StringUtils.isEmpty(listePromotionDetailForm.getId())) {
    		tousListeDiplomeDetail.setId(new Long(listePromotionDetailForm.getId()));
        }
    	tousListeDiplomeDetail.setListePromotion(listePromotionDetailForm.getListePromotion());
    	tousListeDiplomeDetail.setNomComplet(listePromotionDetailForm.getNomComplet());
    	tousListeDiplomeDetail.setDateNaissance(listePromotionDetailForm.getDateNaissance());  
    	tousListeDiplomeDetail.setLieuNaissance(listePromotionDetailForm.getLieuNaissance());
    	tousListeDiplomeDetail.setMention(listePromotionDetailForm.getMention());
        
    	return tousListeDiplomeDetail;
    }

}
