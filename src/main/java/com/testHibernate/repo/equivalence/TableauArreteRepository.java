package com.testHibernate.repo.equivalence;

import org.springframework.data.repository.CrudRepository;

import com.testHibernate.model.equivalence.TableauArrete;
 
public interface TableauArreteRepository extends CrudRepository<TableauArrete, Long> {
}
