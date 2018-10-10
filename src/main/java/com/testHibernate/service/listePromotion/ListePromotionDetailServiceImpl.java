package com.testHibernate.service.listePromotion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.listePromotion.ListePromotionDetailFormToListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotion;
import com.testHibernate.model.listePromotion.ListePromotionDetail;
import com.testHibernate.model.listePromotion.ListePromotionDetailForm;
import com.testHibernate.repo.listePromotion.ListePromotionDetailRepository;
 
@Service
public class ListePromotionDetailServiceImpl implements ListePromotionDetailService {
	
    private ListePromotionDetailRepository tousListeDiplomeDetailRepository;
    private ListePromotionDetailFormToListePromotionDetail tousListeDiplomeDetailFormToTousListeDiplomeDetail;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
	public ListePromotionDetailServiceImpl(ListePromotionDetailRepository tousListeDiplomeDetailRepository,
			ListePromotionDetailFormToListePromotionDetail tousListeDiplomeDetailFormToTousListeDiplomeDetail) {
		super();
		this.tousListeDiplomeDetailRepository = tousListeDiplomeDetailRepository;
		this.tousListeDiplomeDetailFormToTousListeDiplomeDetail = tousListeDiplomeDetailFormToTousListeDiplomeDetail;
	}

	@Override
    public List<ListePromotionDetail> listAll() {
       List<ListePromotionDetail> ret = new ArrayList<>();
       this.tousListeDiplomeDetailRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public ListePromotionDetail getById(Long id) {
        return this.tousListeDiplomeDetailRepository.findById(id).orElse(null);
    }

	@Override
	public void delete(Long id) {
		this.tousListeDiplomeDetailRepository.deleteById(id);
	}
	
	@Override
	public List<ListePromotionDetail> getDetailByIdListePromotion(Long idListePromotion) {
		TypedQuery<ListePromotionDetail> query = em.createNamedQuery("ListePromotionDetail.findByIdListePromotion", ListePromotionDetail.class).setParameter("idListePromotion", idListePromotion);
		List<ListePromotionDetail> ret = query.getResultList();
		
		return ret;
	}

	@Override
	public ListePromotionDetail saveOrUpdate(ListePromotionDetail listePromotionDetail) {
		this.tousListeDiplomeDetailRepository.save(listePromotionDetail);
        return listePromotionDetail;
	}

	@Override
	public ListePromotionDetail saveOrUpdateListePromotionDetailForm(ListePromotionDetailForm listePromotionDetailForm) {
		ListePromotionDetail savedTousListeDiplome = saveOrUpdate(tousListeDiplomeDetailFormToTousListeDiplomeDetail.convert(listePromotionDetailForm));

        System.out.println("Saved ArreteEqRef Id: " + savedTousListeDiplome.getId());  
        return savedTousListeDiplome;
	}

	@Override
	public List<ListePromotionDetail> pagination(int page, int limit) {
		int offset = (page-1) * limit;
		TypedQuery<ListePromotionDetail> query = em.createNamedQuery("ListePromotionDetail.pagination", ListePromotionDetail.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<ListePromotionDetail> ret = query.getResultList();
		
		return ret; 
	}

	@Override
	public ListePromotionDetail getAdmisByCIN(Long idCin) throws Exception {
		TypedQuery<ListePromotionDetail> query = em.createNamedQuery("ListePromotionDetail.findAdmisByCIN", ListePromotionDetail.class)
				.setParameter(1, idCin);
		System.out.println("\n\n GET ADMIS = "+idCin);
		List<ListePromotionDetail> ret = query.getResultList();
		ListePromotionDetail retour = null;
		if(ret.size()!=0) {
			retour = ret.get(0);
		}else {
			throw new Exception("Error in com.testHibernate.service.listePromotion.ListePromotionDetailServiceImpl.getAdmisByCIN : size is 0");
		}
		
		return retour;
	}

	@Override
	public List<ListePromotionDetail> getAllAdmisByCIN(Long idCin) throws Exception {
		TypedQuery<ListePromotionDetail> query = em.createNamedQuery("ListePromotionDetail.findAdmisByCIN", ListePromotionDetail.class)
				.setParameter(1, idCin); 
		List<ListePromotionDetail> ret = query.getResultList();
	
		return ret;
	}
}
