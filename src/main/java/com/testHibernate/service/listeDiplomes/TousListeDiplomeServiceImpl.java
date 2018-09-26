package com.testHibernate.service.listeDiplomes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.testHibernate.converts.listeDiplomes.TousListeDiplomeFormToTousListeDiplome;
import com.testHibernate.model.listeDiplomes.TousListeDiplome;
import com.testHibernate.model.listeDiplomes.TousListeDiplomeForm;
import com.testHibernate.repo.listeDiplomes.TousListeDiplomeRepository;
 
@Service
public class TousListeDiplomeServiceImpl implements TousListeDiplomeService {
	
    private TousListeDiplomeRepository tousListeDiplomeRepository;
    private TousListeDiplomeFormToTousListeDiplome tousListeDiplomeFormToTousListeDiplome;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public TousListeDiplomeServiceImpl(TousListeDiplomeRepository tousListeDiplomeRepository,
			TousListeDiplomeFormToTousListeDiplome tousListeDiplomeFormToTousListeDiplome) {
		super();
		this.tousListeDiplomeRepository = tousListeDiplomeRepository;
		this.tousListeDiplomeFormToTousListeDiplome = tousListeDiplomeFormToTousListeDiplome;
	}

	@Override
    public List<TousListeDiplome> listAll() {
       List<TousListeDiplome> ret = new ArrayList<>();
       tousListeDiplomeRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public TousListeDiplome getById(Long id) {
        return this.tousListeDiplomeRepository.findById(id).orElse(null);
    }

	@Override
	public void delete(Long id) {
		this.tousListeDiplomeRepository.deleteById(id);
	}

	@Override
	public List<TousListeDiplome> getBySession(String session) {
		TypedQuery<TousListeDiplome> query = em.createNamedQuery("TousListeDiplome.findBySession", TousListeDiplome.class).setParameter("session", session);
		List<TousListeDiplome> ret = query.getResultList();
		
		return ret;
	}

	@Override
	public TousListeDiplome saveOrUpdate(TousListeDiplome tousListeDiplome) {
		tousListeDiplomeRepository.save(tousListeDiplome);
        return tousListeDiplome;
	}

	@Override
	public TousListeDiplome saveOrUpdateTousListeDiplomeForm(TousListeDiplomeForm tousListeDiplomeForm) {
		TousListeDiplome savedTousListeDiplome = saveOrUpdate(tousListeDiplomeFormToTousListeDiplome.convert(tousListeDiplomeForm));

        System.out.println("Saved ArreteEqRef Id: " + savedTousListeDiplome.getId());  
        return savedTousListeDiplome;
	}

}
