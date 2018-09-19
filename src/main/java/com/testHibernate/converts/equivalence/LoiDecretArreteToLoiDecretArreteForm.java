package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.equivalence.LoiDecretArrete;
import com.testHibernate.model.equivalence.LoiDecretArreteForm;

@Component
public class LoiDecretArreteToLoiDecretArreteForm implements Converter<LoiDecretArrete, LoiDecretArreteForm> {

    @Override
    public LoiDecretArreteForm convert(LoiDecretArrete loiDecretArrete) {
    	LoiDecretArreteForm loiDecretArreteForm = new LoiDecretArreteForm();
    	
    	loiDecretArreteForm.setId(loiDecretArrete.getId());
    	loiDecretArreteForm.setArreteEqRef(loiDecretArrete.getArreteEqRef());
    	loiDecretArreteForm.setContenu(loiDecretArrete.getContenu());
    	
        return loiDecretArreteForm;
    }

}
