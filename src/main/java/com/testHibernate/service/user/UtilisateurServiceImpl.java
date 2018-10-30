package com.testHibernate.service.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.user.UtilisateurFormToUtilisateur;
import com.testHibernate.model.user.Utilisateur;
import com.testHibernate.model.user.UtilisateurForm;
import com.testHibernate.repo.user.UtilisateurRepository;
 
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurRepository utilisateurRepository; 
	private UtilisateurFormToUtilisateur utilisateurFormToUtilisateur;
	@PersistenceContext
	private EntityManager em;

	@Autowired
	public UtilisateurServiceImpl(UtilisateurRepository UtilisateurRepository , UtilisateurFormToUtilisateur utilisateurFormToUtilisateur) {
		super();
		this.utilisateurRepository = UtilisateurRepository; 
		this.utilisateurFormToUtilisateur = utilisateurFormToUtilisateur; 
	}

	@Override
	public List<Utilisateur> listAll() {
		List<Utilisateur> ret = new ArrayList<>();
		this.utilisateurRepository.findAll().forEach(ret::add); // fun with Java 8
		return ret;
	}  

	@Override
	public Utilisateur saveOrUpdate(Utilisateur utilisateur) {
		this.utilisateurRepository.save(utilisateur);
		return utilisateur;
	}
	@Override
	public Utilisateur saveOrUpdateUtilisateurForm(UtilisateurForm utilisateurForm) {
		Utilisateur ret = saveOrUpdate(utilisateurFormToUtilisateur.convert(utilisateurForm));

        System.out.println("Saved ArreteEqRef Id: " + ret.getId());  
        return ret;
	}
	 
	@Override
	public void delete(Long id) {
		this.utilisateurRepository.deleteById(id);
	}

 
	@Override
	public Utilisateur getById(Long id) {
		return this.utilisateurRepository.findById(id).orElse(null);
		 
	}

	@Override
	public List<Utilisateur> findUserByLogin(String pseudo) {
		TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.findUserByLogin", Utilisateur.class)
				.setParameter(1, pseudo.toLowerCase());
		List<Utilisateur> ret = null;
		ret = query.getResultList();
		
		return ret;
	}

 
}
