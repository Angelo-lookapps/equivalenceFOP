package com.testHibernate.service.historique;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.historique.ActiviteRecent;

public interface ActiviteRecentService {
	
	List<ActiviteRecent> listAll();
	
	List<ActiviteRecent> findASC();
	
    List<ActiviteRecent> getRecentActiviteByNumber(int number);

    ActiviteRecent getById(Long id);
    
    int deleteTheLatest(@Param(value = "dateCompare") String dateCompare);
    
    int deleteAllLast();

    
    ActiviteRecent saveOrUpdate(ActiviteRecent activiteRecent);

    void delete(Long id);

	List<ActiviteRecent> findDESC();
    
    //ActiviteRecent saveOrUpdateActiviteRecentForm(ActiviteRecentForm activiteRecent);

}
