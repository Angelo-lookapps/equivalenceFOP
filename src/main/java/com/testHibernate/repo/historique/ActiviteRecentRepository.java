package com.testHibernate.repo.historique;

import org.springframework.data.repository.CrudRepository;

import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.historique.ActiviteRecent;
 
public interface ActiviteRecentRepository extends CrudRepository<ActiviteRecent, Long> {
}
