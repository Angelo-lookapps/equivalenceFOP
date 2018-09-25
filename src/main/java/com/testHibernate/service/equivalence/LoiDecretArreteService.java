package com.testHibernate.service.equivalence;

import java.util.List;

import org.springframework.data.repository.query.Param;
 
import com.testHibernate.model.equivalence.LoiDecretArrete;
import com.testHibernate.model.equivalence.LoiDecretArreteForm;

public interface LoiDecretArreteService {
	
    List<LoiDecretArrete> listAll();

    LoiDecretArrete getById(Long id);
    
    LoiDecretArrete  getLoiDecretByIdArreteEqRef(@Param(value = "idArreteEqRef") String idArreteEqRef);

    LoiDecretArrete saveOrUpdate(LoiDecretArrete loiDecretArrete);

    void delete(Long id);
    
    LoiDecretArrete saveOrUpdateLoiDecretArreteForm(LoiDecretArreteForm loiDecretArreteForm);

}
