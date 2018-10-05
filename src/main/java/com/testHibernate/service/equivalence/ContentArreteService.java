package com.testHibernate.service.equivalence;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.equivalence.ContentArrete;
import com.testHibernate.model.equivalence.ContentArreteForm;

public interface ContentArreteService {
	
    List<ContentArrete> listAll();

    ContentArrete getById(Long id);
    
    ContentArrete  getContentByArrete(@Param(value = "idArrete") Long idArrete);

    ContentArrete saveOrUpdate(ContentArrete contentArrete);
 
    void delete(Long id);
    
    ContentArrete saveOrUpdateContentArreteForm(ContentArreteForm contentArreteForm);

	List<ContentArrete> pagination(int page, int limit);

}
