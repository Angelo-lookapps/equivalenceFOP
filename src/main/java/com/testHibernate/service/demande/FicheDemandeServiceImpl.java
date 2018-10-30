package com.testHibernate.service.demande;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.demande.DemandeFormToDemande;
import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.demande.FicheDemandeForm;
import com.testHibernate.repo.demande.FicheDemandeRepository;
 
@Service
public class FicheDemandeServiceImpl implements FicheDemandeService {
	
    private FicheDemandeRepository ficheDemandeRepository;
    private DemandeFormToDemande demandeFormToDemande;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public FicheDemandeServiceImpl(FicheDemandeRepository ficheDemandeRepository,
			DemandeFormToDemande demandeFormToDemande) {
		super();
		this.ficheDemandeRepository = ficheDemandeRepository;
		this.demandeFormToDemande = demandeFormToDemande;
	}


	@Override
    public List<FicheDemande> listAll() {
       List<FicheDemande> ret = new ArrayList<>();
       ficheDemandeRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public FicheDemande getById(Long id) {
        return ficheDemandeRepository.findById(id).orElse(null);
    }

	@Override
	public List<FicheDemande> getFicheDemandeByCIN(Long idCin) {
		TypedQuery<FicheDemande> query = em.createNamedQuery("FicheDemande.findFicheDemandeByCIN", FicheDemande.class).setParameter("idCIN", idCin);
		List<FicheDemande> ret = query.getResultList();
		
		return ret;
	
	}

	@Override
	public List<FicheDemande> getFicheDemandeByStatusRejet() {
		TypedQuery<FicheDemande> query = em.createNamedQuery("FicheDemande.findFicheDemandeByStatusRejet", FicheDemande.class) ;
		List<FicheDemande> ret = query.getResultList();
		
		return ret;
	}
	@Override
	public List<FicheDemande> getFicheDemandeByStatusEnCours() {
		TypedQuery<FicheDemande> query = em.createNamedQuery("FicheDemande.findFicheDemandeByStatusEnCours", FicheDemande.class) ;
		List<FicheDemande> ret = query.getResultList();
		
		return ret;
	}

	@Override
	public List<FicheDemande> getFicheDemandeByDate(String dateRetrait) {
		TypedQuery<FicheDemande> query = em.createNamedQuery("FicheDemande.findFicheDemandeByDate", FicheDemande.class).setParameter("dateRetrait", dateRetrait);
		List<FicheDemande> ret = query.getResultList();
		
		return ret;
	}

	
	@Override
	public FicheDemande saveOrUpdate(FicheDemande ficheDemande) {
		ficheDemandeRepository.save(ficheDemande);
        return ficheDemande;
	}

	@Override
	public FicheDemande saveOrUpdateDemandeForm(FicheDemandeForm ficheForm) {
		FicheDemande savedFicheDemande = saveOrUpdate(demandeFormToDemande.convert(ficheForm));

        System.out.println("Saved FicheDemande Id: " + savedFicheDemande.getId());
        return savedFicheDemande;
	}


	@Override
	public void delete(Long id) {
		ficheDemandeRepository.deleteById(id);
	}


	@Override
	public List<FicheDemande> getFicheDemandeByFilterASC(String champ) {
		TypedQuery<FicheDemande> query = em.createNamedQuery("FicheDemande.findByFilterASC", FicheDemande.class)
				.setParameter("champ", champ) ;
		List<FicheDemande> ret = query.getResultList();
		
		return ret;
	}
	@Override
	public List<FicheDemande> getFicheDemandeByFilterDESC(String champ) {
		TypedQuery<FicheDemande> query = em.createNamedQuery("FicheDemande.findByFilterDESC", FicheDemande.class)
				.setParameter("champ", champ) ;
		List<FicheDemande> ret = query.getResultList();
		
		return ret;
	}

	@Override
	public List<FicheDemande> pagination(int page, int limit) {
		int offset = (page-1) * limit;
		TypedQuery<FicheDemande> query = em.createNamedQuery("FicheDemande.pagination", FicheDemande.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<FicheDemande> ret = query.getResultList();
		
		return ret; 
	}


	@Override
	public List<FicheDemande> selectByRejet(List<FicheDemande> listeDemande, Boolean statusRejet) throws Exception {
		List<FicheDemande> liste = new ArrayList<FicheDemande>();
		try {
			if(listeDemande.size()!=0) { 
				for(FicheDemande temp : listeDemande) {
					if(temp.getStatusRejet()==statusRejet) { 
						liste.add(temp);
					}
				}	
			}
		}catch(Exception e) {
			throw e;
		}
		return liste;
	}


	@Override
	public long getFicheDemandeByDayOrMonth(String field, Integer month , Boolean statusRejet) {
		long result = 0;
		result = (long) em.createNamedQuery("FicheDemande.getDemandeByMonth", Long.class)
				.setParameter(1,  field )
				.setParameter(2, month)
				.setParameter(3, statusRejet)
				.getSingleResult(); 
	 
		return result;
	}
}
