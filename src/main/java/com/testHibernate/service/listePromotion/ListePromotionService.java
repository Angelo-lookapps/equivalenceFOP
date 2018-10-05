package com.testHibernate.service.listePromotion;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionForm;

public interface ListePromotionService {
	
    List<ListePromotion> listAll();

    ListePromotion getById(Long id);
    
    ListePromotion  getPromotionByIdListeDiplome(@Param(value = "idListe") Long idListe);
    
    List<ListePromotion>  getBySession(@Param(value = "session") String session);

    ListePromotion saveOrUpdate(ListePromotion listePromotion);
    
    void delete(Long id);
    
    ListePromotion saveOrUpdateListePromotionForm(ListePromotionForm listePromotionForm);

	List<ListePromotion> pagination(int page, int limit);

}
