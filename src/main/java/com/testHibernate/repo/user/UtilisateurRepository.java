package com.testHibernate.repo.user;

import org.springframework.data.repository.CrudRepository;

import com.testHibernate.model.user.Utilisateur;
  
 
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long> {
}
