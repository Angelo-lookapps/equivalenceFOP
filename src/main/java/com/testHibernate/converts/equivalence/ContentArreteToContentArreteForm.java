package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.equivalence.ContentArrete;
import com.testHibernate.model.equivalence.ContentArreteForm;

@Component
public class ContentArreteToContentArreteForm implements Converter<ContentArrete , ContentArreteForm> {

    @Override
    public ContentArreteForm convert(ContentArrete  contentArrete ) {
    	ContentArreteForm contentArreteForm = new ContentArreteForm();

    	contentArreteForm.setId(contentArrete.getId()); 
    	contentArreteForm.setArreteEqRef(contentArrete.getArreteEqRef());
    	contentArreteForm.setContenu(contentArrete.getContenu());  
    	contentArreteForm.setDateAjout(contentArrete.getDateAjout());
        return contentArreteForm;
    }

}
