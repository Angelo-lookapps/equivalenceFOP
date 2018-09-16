package com.testHibernate.repo.demande;

import org.springframework.data.repository.CrudRepository;

import com.testHibernate.model.demande.FicheDemande;
 
public interface FicheDemandeRepository extends CrudRepository<FicheDemande, Long> {
	
}
