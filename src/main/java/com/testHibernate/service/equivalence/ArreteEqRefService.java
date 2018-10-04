package com.testHibernate.service.equivalence;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;

public interface ArreteEqRefService {
	
    List<ArreteEqRef> listAll();

    ArreteEqRef getById(Long id);
    
    List<ArreteEqRef>  getArreteByTitre(@Param(value = "titre") String titre);

    ArreteEqRef saveOrUpdate(ArreteEqRef ArreteEqRef);

    int update(@Param(value = "id") Long id, @Param(value = "idListesDiplome") Long idListesDiplome,
    		@Param(value = "anneeSortie") String anneeSortie, @Param(value = "titre") String titre);
    
    void delete(Long id);
    
    ArreteEqRef saveOrUpdateArreteEqRefForm(ArreteEqRefForm ArreteEqRefForm);

}
