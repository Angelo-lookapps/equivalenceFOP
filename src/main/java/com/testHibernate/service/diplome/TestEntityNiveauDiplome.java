package com.testHibernate.service.diplome;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.diplome.NiveauDiplome;

@Service
public class TestEntityNiveauDiplome {
	
	/*
	@SuppressWarnings("unchecked")
	 public List<NiveauDiplome> findAll(){
		 List<NiveauDiplome> listNiveauDiplome = (List<NiveauDiplome>) em.createNamedQuery("NiveauDiplome.findAll")
				 	.getResultList();
		 return listNiveauDiplome;
	 }

	@SuppressWarnings("unchecked")
	public List<CIN> findAllCIN(){
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory( "myEntityManager" );
	      
		 EntityManager entitymanager = emf.createEntityManager();
		 
		 return entitymanager.createNamedQuery("NiveauDiplome.findAllNiveau").getResultList();
	}
	 
*/
}
