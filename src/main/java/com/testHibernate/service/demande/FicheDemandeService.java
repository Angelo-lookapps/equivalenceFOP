package com.testHibernate.service.demande;



import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.demande.FicheDemandeForm;

public interface FicheDemandeService {
	
	List<FicheDemande> listAll();
	
	List<FicheDemande> getFicheDemandeByFilterASC(@Param("champ") String champ);
	
	List<FicheDemande> getFicheDemandeByFilterDESC(@Param("champ") String champ);
	
	
	List<FicheDemande> getFicheDemandeByCIN(@Param("idCIN") Long idCin);
	
	List<FicheDemande> getFicheDemandeByStatusRejet();
	
    List<FicheDemande> getFicheDemandeByDate(@Param("dateRetrait") String dateRetrait);

    FicheDemande getById(Long id);

    FicheDemande saveOrUpdate(FicheDemande ficheDemande);

    void delete(Long id);
    
    FicheDemande saveOrUpdateDemandeForm(FicheDemandeForm ficheForm);

	List<FicheDemande> pagination(int page, int limit);
	
	List<FicheDemande> selectByRejet(List<FicheDemande> listeDemande, Boolean statusRejet) throws Exception;
	
	long getFicheDemandeByMonth(Integer month);

}
