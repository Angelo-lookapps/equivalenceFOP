package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.equivalence.TableauArrete;
import com.testHibernate.model.equivalence.TableauArreteForm;

@Component
public class TableauArreteFormToTableauArrete implements Converter<TableauArrete, TableauArreteForm> {

    @Override
    public TableauArreteForm convert(TableauArrete tableauArrete) {
    	TableauArreteForm tableauArreteForm = new TableauArreteForm();
    	
    	if (tableauArrete.getId() != null  && !StringUtils.isEmpty(tableauArrete.getId())) {
    		tableauArreteForm.setId(new Long(tableauArrete.getId()));
        }
    	tableauArreteForm.setArreteEqRef(tableauArrete.getArreteEqRef());
    	tableauArreteForm.setContenuTableau(tableauArrete.getContenuTableau());
    	
        return tableauArreteForm;
    }

}
