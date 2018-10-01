package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
 
import com.testHibernate.model.equivalence.ContentArrete;
import com.testHibernate.model.equivalence.ContentArreteForm;

@Component
public class ContentArreteFormToContentArrete implements Converter<ContentArreteForm, ContentArrete> {

    @Override
    public ContentArrete convert(ContentArreteForm contentArreteForm) {
    	ContentArrete contentArrete = new ContentArrete();
        
    	if (contentArreteForm.getId() != null  && !StringUtils.isEmpty(contentArreteForm.getId())) {
        	contentArrete.setId(new Long(contentArreteForm.getId()));
        }
    	contentArrete.setArreteEqRef(contentArreteForm.getArreteEqRef());
    	contentArrete.setContenu(contentArreteForm.getContenu());  
    	contentArrete.setDateAjout(contentArreteForm.getDateAjout());
        return contentArrete;
    }

}
