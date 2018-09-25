package com.testHibernate.service.equivalence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.equivalence.ChampArreteEqFormToChampArreteEq;
import com.testHibernate.model.equivalence.ChampArreteEq;
import com.testHibernate.model.equivalence.ChampArreteEqForm;
import com.testHibernate.repo.equivalence.ChampArreteEqRepository;
 
@Service
public class ChampArreteEqServiceImpl implements ChampArreteEqService {
	
    private ChampArreteEqRepository champArreteEqRepository;
    private ChampArreteEqFormToChampArreteEq champArreteEqFormToChampArreteEq;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public ChampArreteEqServiceImpl(ChampArreteEqRepository champArreteEqRepository,
    		ChampArreteEqFormToChampArreteEq champArreteEqFormToChampArreteEq) {
		super();
		this.champArreteEqRepository = champArreteEqRepository;
		this.champArreteEqFormToChampArreteEq = champArreteEqFormToChampArreteEq;
	}


	@Override
    public List<ChampArreteEq> listAll() {
       List<ChampArreteEq> ret = new ArrayList<>();
       this.champArreteEqRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public ChampArreteEq getById(Long id) {
        return this.champArreteEqRepository.findById(id).orElse(null);
    }
	
	@Override
	public ChampArreteEq saveOrUpdate(ChampArreteEq champArreteEq) {
		this.champArreteEqRepository.save(champArreteEq);
        return champArreteEq;
	}


	@Override
	public void delete(Long id) {
		this.champArreteEqRepository.deleteById(id);
	}


	@Override
	public ChampArreteEq saveOrUpdateChampArreteEqForm(ChampArreteEqForm champArreteEqForm) {
		ChampArreteEq savedChampArreteEq = saveOrUpdate(this.champArreteEqFormToChampArreteEq.convert(champArreteEqForm));

        System.out.println("Saved ChampArreteEq Id: " + savedChampArreteEq.getId());
       
        return savedChampArreteEq;
	}


	@Override
	public ChampArreteEq getChampArreteEqByIdArreteEqRef(String idArreteEqRef) {
		TypedQuery<ChampArreteEq> query = em.createNamedQuery("ChampArreteEq.findChampArreteEqByIdArretEqRef", ChampArreteEq.class).setParameter("idArreteEqRef", idArreteEqRef);
		List<ChampArreteEq> ret = query.getResultList();
		System.out.println("\n\n \t Single resultat = "+ret.size());
		
		ChampArreteEq sortie = null;
		if(ret.size()!=0) {
			sortie = ret.get(0);
		}
			return sortie;
	}
	
}
