package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.ArreteEqRefForm;

@Component
public class ArreteEqRefFormToArreteEqRef implements Converter<ArreteEqRefForm, ArreteEqRef> {

    @Override
    public ArreteEqRef convert(ArreteEqRefForm arreteEqRefForm) {
    	ArreteEqRef arreteEqRef = new ArreteEqRef();
        
    	if (arreteEqRefForm.getId() != null  && !StringUtils.isEmpty(arreteEqRefForm.getId())) {
        	arreteEqRef.setId(new Long(arreteEqRefForm.getId()));
        }
        arreteEqRef.setListesDiplome(arreteEqRefForm.getListesDiplome());
        arreteEqRef.setTitre(arreteEqRefForm.getTitre());
        arreteEqRef.setAnneeSortie(arreteEqRefForm.getAnneeSortie());
        arreteEqRef.setTypeArreteJasper(arreteEqRefForm.getTypeArreteJasper());  
    	arreteEqRef.setDateAjout(arreteEqRefForm.getDateAjout());
    	arreteEqRef.setStatus(arreteEqRefForm.getStatus());
        return arreteEqRef;
    }

}
