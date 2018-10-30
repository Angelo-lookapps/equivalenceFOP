package com.testHibernate.service.equivalence;

import java.util.List;


import com.testHibernate.model.equivalence.TypeArreteJasper; 
 
public interface TypeArreteJasperService {
	
    List<TypeArreteJasper> listAll();

    TypeArreteJasper getById(Long id); 
    
    TypeArreteJasper saveOrUpdate(TypeArreteJasper TypeArreteJasper);

    void delete(Long id);
     

}
