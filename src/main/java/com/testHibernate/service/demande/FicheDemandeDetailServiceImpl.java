package com.testHibernate.service.demande;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.demande.DemandeDetailFormToDemandeDetail;
import com.testHibernate.converts.demande.DemandeFormToDemande;
import com.testHibernate.model.demande.FicheDemandeDetail;
import com.testHibernate.model.demande.FicheDemandeDetailForm;
import com.testHibernate.model.diplome.NiveauDiplome;
import com.testHibernate.repo.demande.FicheDemandeDetailRepository;
 
@Service
public class FicheDemandeDetailServiceImpl implements FicheDemandeDetailService {
	
    private FicheDemandeDetailRepository ficheDemandeDetailRepository;
    private DemandeDetailFormToDemandeDetail demandeDetailFormToDemandeDetail;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public FicheDemandeDetailServiceImpl(FicheDemandeDetailRepository ficheDemandeDetailRepository,
    		DemandeDetailFormToDemandeDetail demandeDetailFormToDemandeDetail) {
		super();
		this.ficheDemandeDetailRepository = ficheDemandeDetailRepository;
		this.demandeDetailFormToDemandeDetail = demandeDetailFormToDemandeDetail;
	}


	@Override
    public List<FicheDemandeDetail> listAll() {
       List<FicheDemandeDetail> ret = new ArrayList<>();
       ficheDemandeDetailRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public FicheDemandeDetail getById(Long id) {
        return ficheDemandeDetailRepository.findById(id).orElse(null);
    }

	@Override
	public FicheDemandeDetail getFicheDemandeByFiche(Long idFiche) {
		TypedQuery<FicheDemandeDetail> query = em.createNamedQuery("FicheDemandeDetail.findFicheDemandeByFiche", FicheDemandeDetail.class).setParameter("idFiche", idFiche);
		List<FicheDemandeDetail> ret = query.getResultList();
		FicheDemandeDetail retour= null;
		
		if(ret.size()!=0) {
			retour = ret.get(0);
		}
		return retour;
	
	} 
	@Override
	public FicheDemandeDetail saveOrUpdate(FicheDemandeDetail ficheDemandeDetail) {
		ficheDemandeDetailRepository.save(ficheDemandeDetail);
        return ficheDemandeDetail;
	}

	@Override
	public FicheDemandeDetail saveOrUpdateDemandeFormDetail(FicheDemandeDetailForm ficheForm) {
		FicheDemandeDetail savedFicheDemandeDetail = saveOrUpdate(demandeDetailFormToDemandeDetail.convert(ficheForm));

        System.out.println("Saved FicheDemandeDetail Id: " + savedFicheDemandeDetail.getId());
        return savedFicheDemandeDetail;
	}


	@Override
	public void delete(Long id) {
		ficheDemandeDetailRepository.deleteById(id);
	} 
	
	@Override
	public List<FicheDemandeDetail> pagination(int page, int limit) {
		int offset = (page-1) * limit;
		TypedQuery<FicheDemandeDetail> query = em.createNamedQuery("FicheDemandeDetail.pagination", FicheDemandeDetail.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<FicheDemandeDetail> ret = query.getResultList();
		
		return ret; 
	}

}
