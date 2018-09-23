package com.testHibernate.service.equivalence;

import java.util.List;

import org.springframework.data.repository.query.Param;
  
import com.testHibernate.model.equivalence.EnteteArrete;
import com.testHibernate.model.equivalence.EnteteArreteForm;

public interface EnteteArreteService {
	
    List<EnteteArrete> listAll();

    EnteteArrete getById(Long id);
    
    EnteteArrete  getEnteteByIdArreteEqRef(@Param(value = "idArreteEqRef") String idArreteEqRef);

    EnteteArrete saveOrUpdate(EnteteArrete enteteArrete);

    void delete(Long id);
    
    EnteteArrete saveOrUpdateEnteteArreteForm(EnteteArreteForm enteteArreteForm);

}
