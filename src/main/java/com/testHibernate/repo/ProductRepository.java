package com.testHibernate.repo;

import org.springframework.data.repository.CrudRepository;

import com.testHibernate.model.Product;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {
}
