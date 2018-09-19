package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.equivalence.LoiDecretArrete;
import com.testHibernate.model.equivalence.LoiDecretArreteForm;

@Component
public class LoiDecretArreteFormToLoiDecretArrete implements Converter<LoiDecretArreteForm, LoiDecretArrete> {

    @Override
    public LoiDecretArrete convert(LoiDecretArreteForm loiDecretArreteForm) {
    	LoiDecretArrete loiDecretArrete = new LoiDecretArrete();
    	
    	if (loiDecretArreteForm.getId() != null  && !StringUtils.isEmpty(loiDecretArreteForm.getId())) {
    		loiDecretArrete.setId(new Long(loiDecretArreteForm.getId()));
        }
    	loiDecretArrete.setArreteEqRef(loiDecretArreteForm.getArreteEqRef());
    	loiDecretArrete.setContenu(loiDecretArreteForm.getContenu());
    	
        return loiDecretArrete;
    }

}
