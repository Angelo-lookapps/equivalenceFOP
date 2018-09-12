package com.testHibernate.service.diplome;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	public List<NiveauDiplome> listAllNiveau() {
		List<NiveauDiplome> listNiveauDiplome = new ArrayList<>();
        niveauDiplomeRepository.findAll().forEach(listNiveauDiplome::add); //fun with Java 8
        return listNiveauDiplome;
	}

	@Override
	public List<ListesDiplome> getByNiveauDiplome(NiveauDiplome niveauDiplome) {
		List<ListesDiplome> listNiveauDiplome = new ArrayList<>();
		listesDiplomeRepository.findById(id).orElse(null)
        niveauDiplomeRepository.findAll().forEach(listNiveauDiplome::add); //fun with Java 8
        return listNiveauDiplome;
	}
	
	public TypedQuery<Object> createQuery(String where){
	   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "byNiveauDiplome_JPA" );
	   EntityManager entitymanager = emfactory.createEntityManager( );
	   CriteriaBuilder criteriaBuilder = entitymanager.getCriteriaBuilder();
	   CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
	   Root<Employee> from = criteriaQuery.from(Employee.class);

	   //select all records
	   CriteriaQuery<Object> select = criteriaQuery.select(from);
	   TypedQuery<Object> typedQuery = entitymanager.createQuery(select);
	   List<Object> resultlist = typedQuery.getResultList();

	   for(Object o:resultlist) {
	      Employee e = (Employee)o;
	      System.out.println("EID : " + e.getEid() + " Ename : " + e.getEname());
	   }
	}

}
