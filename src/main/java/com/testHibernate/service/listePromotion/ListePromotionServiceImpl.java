package com.testHibernate.service.listePromotion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.listePromotion.ListePromotionFormToListePromotion;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionForm;
import com.testHibernate.repo.listePromotion.ListePromotionRepository;
 
@Service
public class ListePromotionServiceImpl implements ListePromotionService {
	
    private ListePromotionRepository listePromotionRepository;
    private ListePromotionFormToListePromotion listePromotionFormTolistePromotion;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public ListePromotionServiceImpl(ListePromotionRepository listePromotionRepository,
			ListePromotionFormToListePromotion listePromotionFormTolistePromotion) {
		super();
		this.listePromotionRepository = listePromotionRepository;
		this.listePromotionFormTolistePromotion = listePromotionFormTolistePromotion;
	}

	@Override
    public List<ListePromotion> listAll() {
       List<ListePromotion> ret = new ArrayList<>();
       listePromotionRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public ListePromotion getById(Long id) {
        return this.listePromotionRepository.findById(id).orElse(null);
    }

	@Override
	public void delete(Long id) {
		this.listePromotionRepository.deleteById(id);
	}

	@Override
	public List<ListePromotion> getBySession(String session) {
		TypedQuery<ListePromotion> query = em.createNamedQuery("listePromotion.findBySession", ListePromotion.class).setParameter("session", session);
		List<ListePromotion> ret = query.getResultList();
		
		return ret;
	}

	@Override
	public ListePromotion saveOrUpdate(ListePromotion listePromotion) {
		listePromotionRepository.save(listePromotion);
        return listePromotion;
	}

	@Override
	public ListePromotion saveOrUpdateListePromotionForm(ListePromotionForm listePromotionForm) {
		ListePromotion savedlistePromotion = saveOrUpdate(listePromotionFormTolistePromotion.convert(listePromotionForm));

        System.out.println("Saved ArreteEqRef Id: " + savedlistePromotion.getId());  
        return savedlistePromotion;
	}

	@Override
	public ListePromotion getPromotionByIdListeDiplome(Long idListe) {
		TypedQuery<ListePromotion> query = em.createNamedQuery("ListePromotion.findPromotionByIdListeDiplome", ListePromotion.class).setParameter("idListe", idListe);
		List<ListePromotion> ret = query.getResultList();
		ListePromotion retour = null;
		if(ret.size()!=0) {
			retour = ret.get(0);
		}
		return retour;
	}
	@Override
	public List<ListePromotion> pagination(int page, int limit) {
		int offset = (page-1) * limit;
		TypedQuery<ListePromotion> query = em.createNamedQuery("ListePromotion.pagination", ListePromotion.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<ListePromotion> ret = query.getResultList();
		
		return ret; 
	}

	@Override
	public ListePromotion getByIdDiplomeAndSession(Long idDiplome, String sessionSortie) {
		TypedQuery<ListePromotion> query = em.createNamedQuery("ListePromotion.findByIdDiplomeAndSession", ListePromotion.class)
				.setParameter(1, idDiplome)
				.setParameter(2, sessionSortie);
		List<ListePromotion> ret = query.getResultList();
		ListePromotion retour = null;
		if(ret.size()!=0) {
			retour = ret.get(0);
		}
		return retour;
	}

}
