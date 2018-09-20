package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.equivalence.TableauArrete;
import com.testHibernate.model.equivalence.TableauArreteForm;

@Component
public class TableauArreteToTableauArreteForm implements Converter<TableauArrete, TableauArreteForm> {

    @Override
    public TableauArreteForm convert(TableauArrete tableauArrete) {
    	TableauArreteForm tableauArreteForm = new TableauArreteForm();
    	
    	tableauArreteForm.setId(tableauArrete.getId());
    	tableauArreteForm.setArreteEqRef(tableauArrete.getArreteEqRef());
    	tableauArreteForm.setTitre(tableauArrete.getTitre());
    	tableauArreteForm.setOrganismePaysDelivrance(tableauArrete.getOrganismePaysDelivrance());
    	tableauArreteForm.setCadre(tableauArrete.getCadre());
    	tableauArreteForm.setEchelle(tableauArrete.getEchelle());
    	tableauArreteForm.setClassementCategorie(tableauArrete.getClassementCategorie());
    	
        return tableauArreteForm;
    }

}