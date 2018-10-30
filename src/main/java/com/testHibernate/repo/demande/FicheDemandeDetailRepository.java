package com.testHibernate.repo.demande;

import org.springframework.data.repository.CrudRepository;

import com.testHibernate.model.demande.FicheDemandeDetail;
 
public interface FicheDemandeDetailRepository extends CrudRepository<FicheDemandeDetail, Long> {
	
}
