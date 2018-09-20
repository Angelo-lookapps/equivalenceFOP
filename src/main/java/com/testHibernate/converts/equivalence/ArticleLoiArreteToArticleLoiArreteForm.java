package com.testHibernate.converts.equivalence;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.testHibernate.model.equivalence.ArticleLoiArrete;
import com.testHibernate.model.equivalence.ArticleLoiArreteForm;

@Component
public class ArticleLoiArreteToArticleLoiArreteForm implements Converter<ArticleLoiArrete, ArticleLoiArreteForm> {

    @Override
    public ArticleLoiArreteForm convert(ArticleLoiArrete articleLoiArrete) {
    	ArticleLoiArreteForm articleLoiArreteForm = new ArticleLoiArreteForm();
    	
    	articleLoiArreteForm.setId(articleLoiArrete.getId());
    	articleLoiArrete.setArreteEqRef(articleLoiArreteForm.getArreteEqRef());
    	articleLoiArrete.setArticleComplet(articleLoiArreteForm.getArticleComplet());
    	articleLoiArrete.setSignatureMinistre(articleLoiArreteForm.getSignatureMinistre());
    	
        return articleLoiArreteForm;
    }

}
