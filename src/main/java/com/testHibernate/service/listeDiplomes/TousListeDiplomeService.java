package com.testHibernate.service.listeDiplomes;

import java.util.List;

import org.springframework.data.repository.query.Param;
 
import com.testHibernate.model.listeDiplomes.TousListeDiplome;
import com.testHibernate.model.listeDiplomes.TousListeDiplomeForm;

public interface TousListeDiplomeService {
	
    List<TousListeDiplome> listAll();

    TousListeDiplome getById(Long id);
    
    List<TousListeDiplome>  getBySession(@Param(value = "session") String session);

    TousListeDiplome saveOrUpdate(TousListeDiplome tousListeDiplome);
    
    void delete(Long id);
    
    TousListeDiplome saveOrUpdateTousListeDiplomeForm(TousListeDiplomeForm tousListeDiplomeForm);

}
