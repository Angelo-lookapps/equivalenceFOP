package com.testHibernate.service.equivalence;

import java.util.List;

import org.springframework.data.repository.query.Param;
  
import com.testHibernate.model.equivalence.TableauArrete;
import com.testHibernate.model.equivalence.TableauArreteForm;

public interface TableauArreteService {
	
    List<TableauArrete> listAll();

    TableauArrete getById(Long id);
    
    TableauArrete  getTableauByIdArreteEqRef(@Param(value = "idArreteEqRef") String idArreteEqRef);

    TableauArrete saveOrUpdate(TableauArrete tableauArrete);

    void delete(Long id);
    
    TableauArrete saveOrUpdateTableauArreteForm(TableauArreteForm tableauArreteForm);

}
