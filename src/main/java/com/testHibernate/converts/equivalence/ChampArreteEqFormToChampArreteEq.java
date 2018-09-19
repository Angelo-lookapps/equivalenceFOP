package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.equivalence.ChampArreteEq;
import com.testHibernate.model.equivalence.ChampArreteEqForm;

@Component
public class ChampArreteEqFormToChampArreteEq implements Converter<ChampArreteEqForm, ChampArreteEq> {

    @Override
    public ChampArreteEq convert(ChampArreteEqForm champArreteEqForm) {
    	ChampArreteEq champArreteEq = new ChampArreteEq();
    	
    	if (champArreteEqForm.getId() != null  && !StringUtils.isEmpty(champArreteEqForm.getId())) {
    		champArreteEq.setId(new Long(champArreteEqForm.getId()));
        }
    	champArreteEq.setArreteEqRef(champArreteEqForm.getArreteEqRef());
    	champArreteEq.setNumero(champArreteEqForm.getNumero());
    	champArreteEq.setDelivreeA(champArreteEqForm.getDelivreeA());
    	champArreteEq.setTitulaireDe(champArreteEqForm.getTitulaireDe());
    	champArreteEq.setSpecialite(champArreteEqForm.getSpecialite());
    	
        return champArreteEq;
    }

}
