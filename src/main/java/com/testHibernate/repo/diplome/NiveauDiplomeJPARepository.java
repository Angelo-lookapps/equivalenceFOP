package com.testHibernate.repo.diplome;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.testHibernate.model.diplome.NiveauDiplome;
 
public interface NiveauDiplomeJPARepository extends JpaRepository<NiveauDiplome, Long> {
}
