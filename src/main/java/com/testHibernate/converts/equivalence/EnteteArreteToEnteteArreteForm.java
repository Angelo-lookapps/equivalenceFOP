package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.equivalence.EnteteArrete;
import com.testHibernate.model.equivalence.EnteteArreteForm;

@Component
public class EnteteArreteToEnteteArreteForm implements Converter<EnteteArrete, EnteteArreteForm> {

    @Override
    public EnteteArreteForm convert(EnteteArrete enteteArrete) {
    	EnteteArreteForm enteteArreteForm = new EnteteArreteForm();
    	
    	enteteArreteForm.setId(enteteArrete.getId());
    	enteteArreteForm.setArreteEqRef(enteteArrete.getArreteEqRef());
    	enteteArreteForm.setLogo(enteteArrete.getLogo());
    	enteteArreteForm.setTitreGauche(enteteArrete.getTitreGauche());
    	enteteArreteForm.setTitreDroite(enteteArrete.getTitreDroite());
    	
        return enteteArreteForm;
    }

}

