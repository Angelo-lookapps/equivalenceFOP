package com.testHibernate.service;



import java.util.List;

import com.testHibernate.model.Product;
import com.testHibernate.model.ProductForm;

public interface ProductService {

    List<Product> listAll();

    Product getById(Long id);

    Product saveOrUpdate(Product product);

    void delete(Long id);

    Product saveOrUpdateProductForm(ProductForm productForm);
}
