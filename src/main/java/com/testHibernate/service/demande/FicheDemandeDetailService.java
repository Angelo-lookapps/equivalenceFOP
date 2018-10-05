package com.testHibernate.service.demande;



import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.demande.FicheDemandeDetail;
import com.testHibernate.model.demande.FicheDemandeDetailForm;

public interface FicheDemandeDetailService {
	
	List<FicheDemandeDetail> listAll();
	
	FicheDemandeDetail getFicheDemandeByFiche(@Param("idFiche") Long idFiche);
	 
	FicheDemandeDetail getById(Long id);

    FicheDemandeDetail saveOrUpdate(FicheDemandeDetail ficheDemandeDetail);

    void delete(Long id);
    
    FicheDemandeDetail saveOrUpdateDemandeFormDetail(FicheDemandeDetailForm ficheForm);

}
