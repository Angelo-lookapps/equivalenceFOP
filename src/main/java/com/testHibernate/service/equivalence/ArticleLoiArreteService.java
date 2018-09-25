package com.testHibernate.service.equivalence;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.testHibernate.model.equivalence.ArticleLoiArrete;
import com.testHibernate.model.equivalence.ArticleLoiArreteForm;

public interface ArticleLoiArreteService {
	
    List<ArticleLoiArrete> listAll();

    ArticleLoiArrete getById(Long id);
    
    ArticleLoiArrete  getArticleLoiByIdArreteEqRef(@Param(value = "idArreteEqRef") String idArreteEqRef);

    ArticleLoiArrete saveOrUpdate(ArticleLoiArrete articleLoiArrete);

    void delete(Long id);
    
    ArticleLoiArrete saveOrUpdateArticleLoiArreteForm(ArticleLoiArreteForm articleLoiArrete);

}
