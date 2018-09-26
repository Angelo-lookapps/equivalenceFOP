package com.testHibernate.converts.listePromotion;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;

@Component
public class ListePromotionDetailFormToListePromotionDetail implements Converter<ListePromotionDetailForm, ListePromotionDetail> {

    @Override
    public ListePromotionDetail convert(ListePromotionDetailForm tousListeDiplomeDetailForm) {
    	ListePromotionDetail tousListeDiplomeDetail = new ListePromotionDetail();
        
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
