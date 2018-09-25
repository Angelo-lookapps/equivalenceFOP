package com.testHibernate.service.equivalence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.equivalence.LoiDecretArreteFormToLoiDecretArrete;
import com.testHibernate.model.equivalence.LoiDecretArrete;
import com.testHibernate.model.equivalence.LoiDecretArreteForm;
import com.testHibernate.repo.equivalence.LoiDecretArreteRepository;
 
@Service
public class LoiDecretArreteServiceImpl implements LoiDecretArreteService {
	
    private LoiDecretArreteRepository loiDecretArreteRepository;
    private LoiDecretArreteFormToLoiDecretArrete loiDecretArreteFormToLoiDecretArrete;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public LoiDecretArreteServiceImpl(LoiDecretArreteRepository loiDecretArreteRepository,
    		LoiDecretArreteFormToLoiDecretArrete loiDecretArreteFormToLoiDecretArrete) {
		super();
		this.loiDecretArreteRepository = loiDecretArreteRepository;
		this.loiDecretArreteFormToLoiDecretArrete = loiDecretArreteFormToLoiDecretArrete;
	}

	@Override
    public List<LoiDecretArrete> listAll() {
       List<LoiDecretArrete> ret = new ArrayList<>();
       this.loiDecretArreteRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
	public LoiDecretArrete getById(Long id) {
		return this.loiDecretArreteRepository.findById(id).orElse(null);
	}


	@Override
	public LoiDecretArrete getLoiDecretByIdArreteEqRef(String idArreteEqRef) {
		TypedQuery<LoiDecretArrete> query = em.createNamedQuery("LoiDecretArrete.findLoiDecretArreteByIdArretEqRef", LoiDecretArrete.class).setParameter("idArreteEqRef", idArreteEqRef);
		List<LoiDecretArrete> ret = query.getResultList();
		System.out.println("\n\n \t Single resultat = "+ret.size());
		
		LoiDecretArrete sortie = null;
		if(ret.size()!=0) {
			sortie = ret.get(0);
		}	
		return sortie;
	}
	
	@Override
	public void delete(Long id) {
		this.loiDecretArreteRepository.deleteById(id);
	}

	@Override
	public LoiDecretArrete saveOrUpdate(LoiDecretArrete loiDecretArrete) {
		this.loiDecretArreteRepository.save(loiDecretArrete);
        return loiDecretArrete;
	}


	@Override
	public LoiDecretArrete saveOrUpdateLoiDecretArreteForm(LoiDecretArreteForm loiDecretArreteForm) {
		LoiDecretArrete savedEnteteArrete = saveOrUpdate(this.loiDecretArreteFormToLoiDecretArrete.convert(loiDecretArreteForm));

        System.out.println("Saved EnteteArrete Id: " + savedEnteteArrete.getId());
       
        return savedEnteteArrete;
	}

	
}
