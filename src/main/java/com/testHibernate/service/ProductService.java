package com.testHibernate.service;



import java.util.List;

import com.testHibernate.model.Product;
import com.testHibernate.model.ProductForm;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductService {

    List<Product> listAll();

    Product getById(Long id);

    Product saveOrUpdate(Product product);

    void delete(Long id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}
