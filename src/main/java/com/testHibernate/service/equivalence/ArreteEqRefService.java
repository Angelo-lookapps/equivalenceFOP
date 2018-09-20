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

    void delete(Long id);
    
    ArreteEqRef saveOrUpdateArreteEqRefForm(ArreteEqRefForm ArreteEqRefForm);

}
