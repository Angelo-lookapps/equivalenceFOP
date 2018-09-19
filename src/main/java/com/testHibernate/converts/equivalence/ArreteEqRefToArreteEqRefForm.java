package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;

@Component
public class ArreteEqRefToArreteEqRefForm implements Converter<ArreteEqRef, ArreteEqRefForm> {

    @Override
    public ArreteEqRefForm convert(ArreteEqRef arreteEqRef) {
    	ArreteEqRefForm arreteEqRefForm = new ArreteEqRefForm();
    	
    	arreteEqRefForm.setId(arreteEqRef.getId());
    	arreteEqRefForm.setListesDiplome(arreteEqRef.getListesDiplome());
    	arreteEqRefForm.setTitre(arreteEqRef.getTitre());
    	arreteEqRefForm.setAnneeSortie(arreteEqRef.getAnneeSortie());  
    	
        return arreteEqRefForm;
    }

}
