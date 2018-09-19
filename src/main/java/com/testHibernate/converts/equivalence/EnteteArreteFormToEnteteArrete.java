package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.equivalence.EnteteArrete;
import com.testHibernate.model.equivalence.EnteteArreteForm;

@Component
public class EnteteArreteFormToEnteteArrete implements Converter<EnteteArreteForm, EnteteArrete> {

    @Override
    public EnteteArrete convert(EnteteArreteForm enteteArreteForm) {
    	EnteteArrete enteteArrete = new EnteteArrete();
    	
    	if (enteteArreteForm.getId() != null  && !StringUtils.isEmpty(enteteArreteForm.getId())) {
    		enteteArrete.setId(new Long(enteteArreteForm.getId()));
        }
    	enteteArrete.setArreteEqRef(enteteArreteForm.getArreteEqRef());
    	enteteArrete.setLogo(enteteArreteForm.getLogo());
    	enteteArrete.setTitreGauche(enteteArreteForm.getTitreGauche());
    	enteteArrete.setTitreDroite(enteteArreteForm.getTitreDroite());
    	
        return enteteArrete;
    }

}

