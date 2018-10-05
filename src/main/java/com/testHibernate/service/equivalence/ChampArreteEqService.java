package com.testHibernate.service.equivalence;
  
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.equivalence.ChampArreteEq;
import com.testHibernate.model.equivalence.ChampArreteEqForm; 

public interface ChampArreteEqService {
	
    List<ChampArreteEq> listAll();

    ChampArreteEq getById(Long id);
    
    ChampArreteEq  getChampArreteEqByIdArreteEqRef(@Param(value = "idArreteEqRef") String idArreteEqRef);

    ChampArreteEq saveOrUpdate(ChampArreteEq champArreteEq);

    void delete(Long id);
    
    ChampArreteEq saveOrUpdateChampArreteEqForm(ChampArreteEqForm champArreteEqForm);

}
