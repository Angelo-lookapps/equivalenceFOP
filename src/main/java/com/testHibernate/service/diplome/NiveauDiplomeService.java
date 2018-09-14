package com.testHibernate.service.diplome;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;
import com.testHibernate.model.diplome.NiveauDiplome;

public interface NiveauDiplomeService {

	List<NiveauDiplome> findNiveauByCategorie(@Param(value = "categorie") String categorie);
	
    List<NiveauDiplome> listAll();

    NiveauDiplome getById(Long id);
    
    NiveauDiplome saveOrUpdate(NiveauDiplome niveauDiplome);

    void delete(Long id);
    

}
