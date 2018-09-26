package com.testHibernate.repo.listeDiplomes;

import org.springframework.data.repository.CrudRepository;

import com.testHibernate.model.listeDiplomes.TousListeDiplome;
  
 
public interface TousListeDiplomeRepository extends CrudRepository<TousListeDiplome, Long> {
}
