package com.testHibernate.converts.diplome;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
 
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;


@Component
public class DiplomeToDiplomeForm implements Converter<ListesDiplome, ListesDiplomeForm> {

    @Override
    public ListesDiplomeForm convert(ListesDiplome diplome) {
    	ListesDiplomeForm listesDiplomeForm = new ListesDiplomeForm();

    	listesDiplomeForm.setFiliere(diplome.getFiliere());
    	listesDiplomeForm.setMention(diplome.getMention());
    	listesDiplomeForm.setOption(diplome.getOption());
    	listesDiplomeForm.setEcole(diplome.getEcole());
    	listesDiplomeForm.setNiveauDiplome(diplome.getNiveauDiplome());
        
        return listesDiplomeForm;
    }
}
