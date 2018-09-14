package com.testHibernate.service.diplome;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.diplome.DiplomeFormToDiplome;
import com.testHibernate.model.diplome.NiveauDiplome;
import com.testHibernate.repo.diplome.NiveauDiplomeRepository;
 
@Service
public class NiveauDiplomeServiceImpl implements NiveauDiplomeService {

    private NiveauDiplomeRepository NiveauDiplomeRepository;
    private NiveauDiplomeRepository niveauDiplomeRepository;
    private DiplomeFormToDiplome listFormTolist;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public NiveauDiplomeServiceImpl(NiveauDiplomeRepository NiveauDiplomeRepository, NiveauDiplomeRepository niveauDiplomeRepository, DiplomeFormToDiplome listFormTolist) {
		super();
		this.NiveauDiplomeRepository = NiveauDiplomeRepository;
		this.niveauDiplomeRepository = niveauDiplomeRepository;
		this.listFormTolist = listFormTolist;
	}

	@Override
    public List<NiveauDiplome> listAll() {
        List<NiveauDiplome> listDiplome = new ArrayList<>();
        niveauDiplomeRepository.findAll().forEach(listDiplome::add); //fun with Java 8
        return listDiplome;
    }

    @Override
    public NiveauDiplome getById(Long id) {
        return niveauDiplomeRepository.findById(id).orElse(null);
    }

    @Override
    public NiveauDiplome saveOrUpdate(NiveauDiplome listediplome) {
    	niveauDiplomeRepository.save(listediplome);
        return listediplome;
    }

    @Override
    public void delete(Long id) {
    	niveauDiplomeRepository.deleteById(id);

    }

	@Override
	public List<NiveauDiplome> findNiveauByCategorie(String categorie) {
		TypedQuery<NiveauDiplome> query = em.createNamedQuery("NiveauDiplome.findNiveauByCategorie", NiveauDiplome.class).setParameter("categorie", categorie);
		List<NiveauDiplome> ret = query.getResultList();
		return ret;
		//return null;
	}

}
