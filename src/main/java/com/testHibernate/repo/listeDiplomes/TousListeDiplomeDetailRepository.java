package com.testHibernate.repo.listeDiplomes;

import org.springframework.data.repository.CrudRepository;
 
import com.testHibernate.model.listeDiplomes.TousListeDiplomeDetail;
  
 
public interface TousListeDiplomeDetailRepository extends CrudRepository<TousListeDiplomeDetail, Long> {
}
