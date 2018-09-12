package com.testHibernate.service.diplome;



import java.util.List;

import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;
import com.testHibernate.model.diplome.NiveauDiplome;
 

public interface ListesDiplomeService {

	List<NiveauDiplome> listAllNiveau();
	
    List<ListesDiplome> listAll();

    ListesDiplome getById(Long id);
    
    List<ListesDiplome>  getByNiveauDiplome(NiveauDiplome niveauDiplome);

    ListesDiplome saveOrUpdate(ListesDiplome listesDiplome);

    void delete(Long id);
    
    ListesDiplome saveOrUpdateListesDiplomeForm(ListesDiplomeForm listesDiplomeForm);

}
