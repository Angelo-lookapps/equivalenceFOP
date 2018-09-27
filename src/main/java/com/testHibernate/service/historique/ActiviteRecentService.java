package com.testHibernate.service.historique;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.historique.ActiviteRecent;

public interface ActiviteRecentService {
	
	List<ActiviteRecent> listAll();
	
    List<ActiviteRecent> getRecentActiviteByNumber(int number);

    ActiviteRecent getById(Long id);
    
    int deleteTheLatest(@Param(value = "dateCompare") String dateCompare);

    ActiviteRecent saveOrUpdate(ActiviteRecent activiteRecent);

    void delete(Long id);
    
    //ActiviteRecent saveOrUpdateActiviteRecentForm(ActiviteRecentForm activiteRecent);

}
