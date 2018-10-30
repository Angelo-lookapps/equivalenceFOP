package com.testHibernate.service.user;

import java.util.List; 

import com.testHibernate.model.user.Utilisateur;
import com.testHibernate.model.user.UtilisateurForm;
 
public interface UtilisateurService {
	
	List<Utilisateur> listAll();

    Utilisateur getById(Long id);
 
    Utilisateur saveOrUpdate(Utilisateur utilisateur);

    void delete(Long id);
    
    Utilisateur saveOrUpdateUtilisateurForm(UtilisateurForm utilisateurForm);

    List<Utilisateur> findUserByLogin(String pseudo);
}
