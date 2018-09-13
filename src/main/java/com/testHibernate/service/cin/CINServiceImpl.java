package com.testHibernate.service.cin;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.cin.CINFormToCIN;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;
import com.testHibernate.repo.cin.CINRepository;
 
@Service
public class CINServiceImpl implements CINService {
	
    private CINRepository cinRepository;
    private CINFormToCIN cinFormToCin;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public CINServiceImpl(com.testHibernate.repo.cin.CINRepository cinRepository, CINFormToCIN cinFormToCin) {
		super();
		this.cinRepository = cinRepository;
		this.cinFormToCin = cinFormToCin;
	}

	@Override
    public List<CIN> listAll() {
       List<CIN> cins = new ArrayList<>();
       cinRepository.findAll().forEach(cins::add); //fun with Java 8
       return cins;
    }

    @Override
    public CIN getById(Long id) {
        return cinRepository.findById(id).orElse(null);
    }

    @Override
    public CIN saveOrUpdate(CIN cin) {
    	cinRepository.save(cin);
        return cin;
    }

    @Override
    public void delete(Long id) {
    	cinRepository.deleteById(id);

    }

	@Override
	public CIN saveOrUpdateCINForm(CINForm cinForm) {
        CIN savedCin = saveOrUpdate(cinFormToCin.convert(cinForm));

        System.out.println("Saved CIN Id: " + savedCin.getId());
        return savedCin;
	}

	@Override
	public List<CIN> listAllCIN() {
		TypedQuery<CIN> query = em.createNamedQuery("CIN.findAllCIN", CIN.class);
		List<CIN> ret = query.getResultList();
		return ret;
		//return null;
	}

}
