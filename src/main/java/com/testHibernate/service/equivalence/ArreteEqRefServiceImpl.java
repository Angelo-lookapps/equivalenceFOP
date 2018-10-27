package com.testHibernate.service.equivalence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.equivalence.ArreteEqRefFormToArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;
import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.repo.equivalence.ArreteEqRefRepository;
 
@Service
public class ArreteEqRefServiceImpl implements ArreteEqRefService {
	
    private ArreteEqRefRepository arreteEqRefRepository;
    private ArreteEqRefFormToArreteEqRef arreteEqRefFormToArreteEqRef;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public ArreteEqRefServiceImpl(ArreteEqRefRepository arreteEqRefRepository,
    		ArreteEqRefFormToArreteEqRef arreteEqRefFormToArreteEqRef) {
		super();
		this.arreteEqRefRepository = arreteEqRefRepository;
		this.arreteEqRefFormToArreteEqRef = arreteEqRefFormToArreteEqRef;
	}


	@Override
    public List<ArreteEqRef> listAll() {
       List<ArreteEqRef> ret = new ArrayList<>();
       arreteEqRefRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public ArreteEqRef getById(Long id) {
        return arreteEqRefRepository.findById(id).orElse(null);
    }


	@Override
	public List<ArreteEqRef> getArreteByTitre(String titre) {
		TypedQuery<ArreteEqRef> query = em.createNamedQuery("ArreteEqRef.findArreteByTitre", ArreteEqRef.class).setParameter("titre", titre);
		List<ArreteEqRef> ret = query.getResultList();
		
		return ret;
	}

	
	@Override
	public ArreteEqRef saveOrUpdate(ArreteEqRef arreteEqRef) {
		arreteEqRefRepository.save(arreteEqRef);
        return arreteEqRef;
	}


	@Override
	public void delete(Long id) {
		this.arreteEqRefRepository.deleteById(id);
	}


	@Override
	public ArreteEqRef saveOrUpdateArreteEqRefForm(ArreteEqRefForm arreteEqRefForm) {
		ArreteEqRef savedArreteEqRef = saveOrUpdate(arreteEqRefFormToArreteEqRef.convert(arreteEqRefForm));

        System.out.println("Saved ArreteEqRef Id: " + savedArreteEqRef.getId());
       
        return savedArreteEqRef;
	}


	@Override
	public int update(Long id, Long idListesDiplome, String anneeSortie, String titre) {
		int ret = -1;
		try{ 
			
			Query query = em.createNamedQuery("ArreteEqRef.updateArreteEqRef", ArreteEqRef.class)
					.setParameter("id", id)
					.setParameter("idListesDiplome", idListesDiplome)
					.setParameter("anneeSortie", anneeSortie)
					.setParameter("titre", titre);
			
			ret = query.executeUpdate();
			
		}catch(Exception e) { 
			e.printStackTrace();
		//	System.out.println(e.getMessage());
			
		}finally {
			if(em.isOpen()) {
				em.close();
			}
		}
		return ret;
	}
	@Override
	public List<ArreteEqRef> pagination(int page, int limit) {
		int offset = (page-1) * limit;
		TypedQuery<ArreteEqRef> query = em.createNamedQuery("ArreteEqRef.pagination", ArreteEqRef.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<ArreteEqRef> ret = query.getResultList();
		
		return ret; 
	}


	@Override
	public ArreteEqRef getArreteByIdDiplome(Long idDiplome) {
		TypedQuery<ArreteEqRef> query = em.createNamedQuery("ArreteEqRef.findArreteByIdDiplome", ArreteEqRef.class)
				.setParameter("idDiplome", idDiplome);
		List<ArreteEqRef> ret = query.getResultList();
		ArreteEqRef retour = null; 
		if(ret.size()!=0) {
			retour = ret.get(0);
		}
		
		return retour;
	}

}
