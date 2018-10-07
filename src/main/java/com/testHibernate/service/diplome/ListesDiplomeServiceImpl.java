package com.testHibernate.service.diplome;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.testHibernate.converts.diplome.DiplomeFormToDiplome;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;
import com.testHibernate.model.diplome.NiveauDiplome; 
import com.testHibernate.repo.diplome.ListesDiplomeRepository;
import com.testHibernate.repo.diplome.NiveauDiplomeRepository;
 
@Service
public class ListesDiplomeServiceImpl implements ListesDiplomeService {

    private ListesDiplomeRepository listesDiplomeRepository;
    private NiveauDiplomeRepository niveauDiplomeRepository;
    private DiplomeFormToDiplome listFormTolist;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public ListesDiplomeServiceImpl(ListesDiplomeRepository listesDiplomeRepository, NiveauDiplomeRepository niveauDiplomeRepository, DiplomeFormToDiplome listFormTolist) {
		super();
		this.listesDiplomeRepository = listesDiplomeRepository;
		this.niveauDiplomeRepository = niveauDiplomeRepository;
		this.listFormTolist = listFormTolist;
	}

	@Override
    public List<ListesDiplome> listAll() {
        List<ListesDiplome> listDiplome = new ArrayList<>();
        listesDiplomeRepository.findAll().forEach(listDiplome::add); //fun with Java 8
        return listDiplome;
    }

    @Override
    public ListesDiplome getById(Long id) {
        return listesDiplomeRepository.findById(id).orElse(null);
    }

    @Override
    public ListesDiplome saveOrUpdate(ListesDiplome listediplome) {
    	listesDiplomeRepository.save(listediplome);
        return listediplome;
    }

    @Override
    public void delete(Long id) {
    	listesDiplomeRepository.deleteById(id);

    }

	@Override
	public ListesDiplome saveOrUpdateListesDiplomeForm(ListesDiplomeForm listeForm) {
		ListesDiplome listesDiplome = saveOrUpdate(listFormTolist.convert(listeForm));

        System.out.println("Saved CIN Id: " + listesDiplome.getId());
        return listesDiplome;
	}

	@Override
	public List<ListesDiplome> getByNiveauDiplome(NiveauDiplome niveauDiplome) {
		
        return null;
	}

	@Override
	public List<ListesDiplome> findDiplomeByCategorie(String categorie) {
		TypedQuery<ListesDiplome> query = em.createNamedQuery("ListesDiplome.findDiplomeByCategorie", ListesDiplome.class).setParameter("categorie", categorie);
		List<ListesDiplome> ret = query.getResultList();
		return ret;
		//return null;
	}

	@Override
	public List<String> getAllEcole() {
		List<String> ret = new ArrayList<String>();
		try{
			 ret = em.createNamedQuery("ListesDiplome.findAllEcole", String.class).getResultList();
			/*List<ListesDiplome> diplomeList = query.getResultList();
		
			for(ListesDiplome liste : diplomeList) {
				ret.add(liste.getEcole());
			}*/
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return ret;
	}

	@Override
	public List<ListesDiplome> findDiplomeByEcole(String ecole) {
		TypedQuery<ListesDiplome> query = em.createNamedQuery("ListesDiplome.findDiplomeByEcole", ListesDiplome.class).setParameter("ecole", ecole);
		List<ListesDiplome> ret = query.getResultList();
		return ret;
		//return null;
	}

	@Override
	public List<ListesDiplome> pagination(int page, int limit) {
		int offset = (page-1) * limit;
		TypedQuery<ListesDiplome> query = em.createNamedQuery("ListesDiplome.pagination", ListesDiplome.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<ListesDiplome> ret = query.getResultList();
		
		return ret; 
	}

	@Override
	public List<ListesDiplome> searchMultiple(String ecole, String filiere, String option ) {
		TypedQuery<ListesDiplome> query = em.createNamedQuery("ListesDiplome.searchMultiple", ListesDiplome.class)
										.setParameter(1, "%"+ecole.toLowerCase()+"%")
										.setParameter(2, "%"+filiere.toLowerCase()+"%")
										.setParameter(3, "%"+option.toLowerCase()+"%");
		System.out.println("Query searchMultiple = " + query.toString());
		List<ListesDiplome> ret = query.getResultList();
		
		System.out.println("\n searchMultiple  ListesDiplome== "+ret.size());
		
		return ret; 
	}

}
