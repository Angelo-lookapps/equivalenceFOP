package com.testHibernate.service.equivalence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.model.equivalence.TypeArreteJasper;
import com.testHibernate.repo.equivalence.TypeArreteJasperRepository;

@Service
public class TypeArreteJasperServiceImpl implements TypeArreteJasperService {
	private TypeArreteJasperRepository typeArreteJasperRepository; 
	
	@PersistenceContext
    private EntityManager em;
	
    @Autowired
	public TypeArreteJasperServiceImpl(TypeArreteJasperRepository typeArreteJasperRepository ) {
		super();
		this.typeArreteJasperRepository = typeArreteJasperRepository; 
	}

	@Override
	public List<TypeArreteJasper> listAll() {
		List<TypeArreteJasper> ret = new ArrayList<>();
		this.typeArreteJasperRepository.findAll().forEach(ret::add); //fun with Java 8
		return ret;
	}

	@Override
	public TypeArreteJasper getById(Long id) {
		return this.typeArreteJasperRepository.findById(id).orElse(null);
	}
 

	@Override
	public TypeArreteJasper saveOrUpdate(TypeArreteJasper TypeArreteJasper) {
		this.typeArreteJasperRepository.save(TypeArreteJasper);
        return TypeArreteJasper;
	}

	@Override
	public void delete(Long id) {
		this.typeArreteJasperRepository.deleteById(id);
	}

	 
}
