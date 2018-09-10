package com.testHibernate.service;



import java.util.List;
 
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;

public interface CINService {

    List<CIN> listAll();

    CIN getById(Long id);

    CIN saveOrUpdate(CIN cin);

    void delete(Long id);
    
    CIN saveOrUpdateCINForm(CINForm cinForm);

}
