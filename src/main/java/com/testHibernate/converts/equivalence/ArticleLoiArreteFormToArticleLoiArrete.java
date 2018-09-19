package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.testHibernate.model.equivalence.ArticleLoiArrete;
import com.testHibernate.model.equivalence.ArticleLoiArreteForm;

@Component
public class ArticleLoiArreteFormToArticleLoiArrete implements Converter<ArticleLoiArreteForm, ArticleLoiArrete> {

    @Override
    public ArticleLoiArrete convert(ArticleLoiArreteForm articleLoiArreteForm) {
    	ArticleLoiArrete articleLoiArrete = new ArticleLoiArrete();
    	
    	if (articleLoiArreteForm.getId() != null  && !StringUtils.isEmpty(articleLoiArreteForm.getId())) {
    		articleLoiArrete.setId(new Long(articleLoiArreteForm.getId()));
        }
    	articleLoiArrete.setArreteEqRef(articleLoiArreteForm.getArreteEqRef());
    	articleLoiArrete.setArticleComplet(articleLoiArreteForm.getArticleComplet());
    	articleLoiArrete.setSignatureMinistre(articleLoiArreteForm.getSignatureMinistre());
    	
        return articleLoiArrete;
    }

}

