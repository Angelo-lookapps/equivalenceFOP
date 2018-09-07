package com.testHibernate.repo;

import org.springframework.data.repository.CrudRepository;

import com.testHibernate.model.cin.CIN;
 
public interface CINRepository extends CrudRepository<CIN, Long> {
}
