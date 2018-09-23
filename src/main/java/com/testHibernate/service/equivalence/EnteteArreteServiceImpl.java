package com.testHibernate.service.equivalence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.testHibernate.converts.equivalence.EnteteArreteFormToEnteteArrete;
import com.testHibernate.model.equivalence.EnteteArrete;
import com.testHibernate.model.equivalence.EnteteArreteForm;
import com.testHibernate.repo.equivalence.EnteteArreteRepository;
 
@Service
public class EnteteArreteServiceImpl implements EnteteArreteService {
	
    private EnteteArreteRepository enteteArreteRepository;
    private EnteteArreteFormToEnteteArrete enteteArreteFormToEnteteArrete;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public EnteteArreteServiceImpl(EnteteArreteRepository enteteArreteRepository,
    		EnteteArreteFormToEnteteArrete enteteArreteFormToEnteteArrete) {
		super();
		this.enteteArreteRepository = enteteArreteRepository;
		this.enteteArreteFormToEnteteArrete = enteteArreteFormToEnteteArrete;
	}


	@Override
    public List<EnteteArrete> listAll() {
       List<EnteteArrete> ret = new ArrayList<>();
       this.enteteArreteRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public EnteteArrete getById(Long id) {
        return this.enteteArreteRepository.findById(id).orElse(null);
    }


	@Override
	public EnteteArrete getEnteteByIdArreteEqRef(String idArreteEqRef) {
		TypedQuery<EnteteArrete> query = em.createNamedQuery("EnteteArrete.findEnteteByIdArretEqRef", EnteteArrete.class).setParameter("idArreteEqRef", idArreteEqRef);
		List<EnteteArrete> ret = query.getResultList();
		System.out.println("\n\n \t Single resultat = "+ret.size());
		
		EnteteArrete sortie = null;
		if(ret.size()!=0) {
			sortie = ret.get(0);
		}
		
		System.out.println("\n\n \t Single resultat = "+sortie.getId()+" titreG: "+sortie.getTitreGauche()+sortie.getId()+" titreD: "+sortie.getTitreDroite());
		
		return sortie;
	}

	
	@Override
	public EnteteArrete saveOrUpdate(EnteteArrete EnteteArrete) {
		this.enteteArreteRepository.save(EnteteArrete);
        return EnteteArrete;
	}


	@Override
	public void delete(Long id) {
		this.enteteArreteRepository.deleteById(id);
	}


	@Override
	public EnteteArrete saveOrUpdateEnteteArreteForm(EnteteArreteForm enteteArreteForm) {
		EnteteArrete savedEnteteArrete = saveOrUpdate(this.enteteArreteFormToEnteteArrete.convert(enteteArreteForm));

        System.out.println("Saved EnteteArrete Id: " + savedEnteteArrete.getId());
       
        return savedEnteteArrete;
	}

}
