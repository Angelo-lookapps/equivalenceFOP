package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.equivalence.ChampArreteEq;
import com.testHibernate.model.equivalence.ChampArreteEqForm;

@Component
public class ChampArreteEqToChampArreteEqForm implements Converter<ChampArreteEq, ChampArreteEqForm> {

    @Override
    public ChampArreteEqForm convert(ChampArreteEq champArreteEq) {
    	ChampArreteEqForm champArreteEqForm = new ChampArreteEqForm();
    	
    	champArreteEqForm.setId(champArreteEq.getId());
    	champArreteEqForm.setArreteEqRef(champArreteEq.getArreteEqRef());
    	champArreteEqForm.setNumero(champArreteEq.getNumero());
    	champArreteEqForm.setDelivreeA(champArreteEq.getDelivreeA());
    	champArreteEqForm.setTitulaireDe(champArreteEq.getTitulaireDe());
    	champArreteEqForm.setSpecialite(champArreteEq.getSpecialite());
    	
        return champArreteEqForm;
    }

}
