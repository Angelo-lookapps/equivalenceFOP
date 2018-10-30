package com.testHibernate.converts.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.user.Utilisateur;
import com.testHibernate.model.user.UtilisateurForm;
 


@Component
public class UtilisateurFormToUtilisateur implements Converter<UtilisateurForm, Utilisateur> {

    @Override
    public Utilisateur convert(UtilisateurForm listDiplomeForm) {
    	Utilisateur utilisateur = new Utilisateur();
        if (listDiplomeForm.getId() != null  && !StringUtils.isEmpty(listDiplomeForm.getId())) {
        	utilisateur.setId(new Long(listDiplomeForm.getId()));
        }
        utilisateur.setNomUser(listDiplomeForm.getNomUser());
        utilisateur.setPrenomUser(listDiplomeForm.getPrenomUser());
        utilisateur.setEmailUser(listDiplomeForm.getEmailUser());
        utilisateur.setPseudoUser(listDiplomeForm.getPseudoUser());
        utilisateur.setMdpUser(listDiplomeForm.getMdpUser());
        
        return utilisateur;
    }
}
