package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.equivalence.TableauArrete;
import com.testHibernate.model.equivalence.TableauArreteForm;

@Component
public class TableauArreteFormToTableauArrete implements Converter<TableauArreteForm, TableauArrete> {

    @Override
    public TableauArrete convert(TableauArreteForm tableauArreteForm2) {
    	TableauArrete tableauArreteForm = new TableauArrete();
    	
    	if (tableauArreteForm2.getId() != null  && !StringUtils.isEmpty(tableauArreteForm2.getId())) {
    		tableauArreteForm.setId(new Long(tableauArreteForm2.getId()));
        }
    	tableauArreteForm.setArreteEqRef(tableauArreteForm2.getArreteEqRef());
    	tableauArreteForm.setContenuTableau(tableauArreteForm2.getContenuTableau());
    	
        return tableauArreteForm;
    }

}
