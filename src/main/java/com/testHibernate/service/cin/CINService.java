package com.testHibernate.service.cin;



import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;

public interface CINService {
	
	List<CIN> listAllCIN(@Param("nom") String nom);
	
    List<CIN> listAll();

    CIN getById(Long id);

    CIN saveOrUpdate(CIN cin);

    void delete(Long id);
    
    CIN saveOrUpdateCINForm(CINForm cinForm);

}
