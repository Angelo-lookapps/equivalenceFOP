package com.testHibernate.converts.user;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.user.Utilisateur;
import com.testHibernate.model.user.UtilisateurForm;


@Component
public class UtilisateurToUtilisateurForm implements Converter<Utilisateur, UtilisateurForm> {

    @Override
    public UtilisateurForm convert(Utilisateur utilisateur) {
    	UtilisateurForm utilisateurForm = new UtilisateurForm();

    	utilisateurForm.setId(utilisateur.getId());
    	utilisateurForm.setNomUser(utilisateur.getNomUser());
    	utilisateurForm.setPrenomUser(utilisateur.getPrenomUser());
    	utilisateurForm.setEmailUser(utilisateur.getEmailUser());
    	utilisateurForm.setPseudoUser(utilisateur.getPseudoUser());
    	utilisateurForm.setMdpUser(utilisateur.getMdpUser());
        
        return utilisateurForm;
    }
}
