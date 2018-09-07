package com.testHibernate.repo;

import org.springframework.data.repository.CrudRepository;

import com.testHibernate.model.Product;

 
public interface ProductRepository extends CrudRepository<Product, Long> {
}
