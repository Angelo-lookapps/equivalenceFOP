package com.testHibernate.service.listePromotion;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm; 

public interface ListePromotionDetailService {
	
    List<ListePromotionDetail> listAll();

    ListePromotionDetail getById(Long id);
    
    List<ListePromotionDetail>  getDetailByIdListePromotion(@Param(value = "idListePromotion") String idListePromotion);

    ListePromotionDetail saveOrUpdate(ListePromotionDetail tousListePromotionDetail);
    
    void delete(Long id);
    
    ListePromotionDetail saveOrUpdateListePromotionDetailForm(ListePromotionDetailForm listePromotionDetailForm);

}
