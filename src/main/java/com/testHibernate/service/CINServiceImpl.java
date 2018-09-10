package com.testHibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.CINFormToCIN;
import com.testHibernate.model.Product;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;
import com.testHibernate.repo.CINRepository;
 
@Service
public class CINServiceImpl implements CINService {

    private CINRepository CINRepository;
    private CINFormToCIN cinFormToCin;
    
    
    @Autowired
    public CINServiceImpl(com.testHibernate.repo.CINRepository cINRepository, CINFormToCIN cinFormToCin) {
		super();
		CINRepository = cINRepository;
		this.cinFormToCin = cinFormToCin;
	}

	@Override
    public List<CIN> listAll() {
        List<CIN> CINs = new ArrayList<>();
        CINRepository.findAll().forEach(CINs::add); //fun with Java 8
        return CINs;
    }

    @Override
    public CIN getById(Long id) {
        return CINRepository.findById(id).orElse(null);
    }

    @Override
    public CIN saveOrUpdate(CIN CIN) {
        CINRepository.save(CIN);
        return CIN;
    }

    @Override
    public void delete(Long id) {
        CINRepository.deleteById(id);

    }

	@Override
	public CIN saveOrUpdateCINForm(CINForm cinForm) {
        CIN savedCin = saveOrUpdate(cinFormToCin.convert(cinForm));

        System.out.println("Saved CIN Id: " + savedCin.getId());
        return savedCin;
	}

}
