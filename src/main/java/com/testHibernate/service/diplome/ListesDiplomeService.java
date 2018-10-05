package com.testHibernate.service.diplome;

import java.util.List;
 
import org.springframework.data.repository.query.Param;

import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;
import com.testHibernate.model.diplome.NiveauDiplome;

public interface ListesDiplomeService {

	List<ListesDiplome> findDiplomeByCategorie(@Param(value = "categorie") String categorie);
	
	List<ListesDiplome> findDiplomeByEcole(@Param(value = "ecole") String ecole);
	
	List<String> getAllEcole();
	
    List<ListesDiplome> listAll();

    ListesDiplome getById(Long id);
    
    List<ListesDiplome>  getByNiveauDiplome(NiveauDiplome niveauDiplome);

    ListesDiplome saveOrUpdate(ListesDiplome listesDiplome);

    void delete(Long id);
    
    ListesDiplome saveOrUpdateListesDiplomeForm(ListesDiplomeForm listesDiplomeForm);

    List<ListesDiplome> pagination(int page, int limit);

}
