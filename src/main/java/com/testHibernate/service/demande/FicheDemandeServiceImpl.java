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
	public List<FicheDemande> getFicheDemandeByStatus(String status) {
		TypedQuery<FicheDemande> query = em.createNamedQuery("FicheDemande.findFicheDemandeByStatus", FicheDemande.class).setParameter("status", status);
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

}
