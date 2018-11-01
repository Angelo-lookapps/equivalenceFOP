package com.testHibernate.converts.diplome;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.model.diplome.ListesDiplomeForm;


@Component
public class DiplomeFormToDiplome implements Converter<ListesDiplomeForm, ListesDiplome> {

    @Override
    public ListesDiplome convert(ListesDiplomeForm listDiplomeForm) {
    	ListesDiplome listesDiplome = new ListesDiplome();
        if (listDiplomeForm.getId() != null  && !StringUtils.isEmpty(listDiplomeForm.getId())) {
        	listesDiplome.setId(new Long(listDiplomeForm.getId()));
        }
        listesDiplome.setFiliere(listDiplomeForm.getFiliere());
        listesDiplome.setOption(listDiplomeForm.getOption());
        listesDiplome.setEcole(listDiplomeForm.getEcole());
        listesDiplome.setNiveauDiplome(listDiplomeForm.getNiveauDiplome());
        listesDiplome.setDateAjout( listDiplomeForm.getDateAjout());
        
        return listesDiplome;
    }
}
