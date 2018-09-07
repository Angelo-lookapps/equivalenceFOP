package com.testHibernate.service;



import java.util.List;

import com.testHibernate.model.cin.CIN;

public interface CINService {

    List<CIN> listAll();

    CIN getById(Long id);

    CIN saveOrUpdate(CIN cin);

    void delete(Long id);
}
