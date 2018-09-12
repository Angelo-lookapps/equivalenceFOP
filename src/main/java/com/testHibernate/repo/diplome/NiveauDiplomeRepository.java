package com.testHibernate.repo.diplome;

import org.springframework.data.repository.CrudRepository;
 
import com.testHibernate.model.diplome.NiveauDiplome;
 
public interface NiveauDiplomeRepository extends CrudRepository<NiveauDiplome, Long> {
}
