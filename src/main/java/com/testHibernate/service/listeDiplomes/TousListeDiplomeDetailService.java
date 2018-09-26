package com.testHibernate.service.listeDiplomes;

import java.util.List;

import org.springframework.data.repository.query.Param;
  
import com.testHibernate.model.listeDiplomes.TousListeDiplomeDetail;
import com.testHibernate.model.listeDiplomes.TousListeDiplomeDetailForm; 

public interface TousListeDiplomeDetailService {
	
    List<TousListeDiplomeDetail> listAll();

    TousListeDiplomeDetail getById(Long id);
    
    List<TousListeDiplomeDetail>  getDetailByIdTousListeDiplome(@Param(value = "idTousListeDiplome") String idTousListeDiplome);

    TousListeDiplomeDetail saveOrUpdate(TousListeDiplomeDetail tousListeDiplomeDetail);
    
    void delete(Long id);
    
    TousListeDiplomeDetail saveOrUpdateTousListeDiplomeDetailForm(TousListeDiplomeDetailForm tousListeDiplomeDetailForm);

}
