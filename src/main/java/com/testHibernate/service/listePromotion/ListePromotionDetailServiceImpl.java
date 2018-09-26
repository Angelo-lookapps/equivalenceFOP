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

}
