package com.testHibernate.service.equivalence;

import java.util.List;

import com.testHibernate.model.equivalence.InfoArrete;
import com.testHibernate.model.equivalence.InfoArreteForm;

public interface InfoArreteService {
	
    List<InfoArrete> listAll();

    InfoArrete getById(Long id);
    
    InfoArrete  getArreteByIdArrete( String idArreteEqRef);
    
    InfoArrete saveOrUpdate(InfoArrete infoArrete);

    void delete(Long id);
    
    InfoArrete saveOrUpdateInfoArreteForm(InfoArreteForm infoArreteForm);


}
