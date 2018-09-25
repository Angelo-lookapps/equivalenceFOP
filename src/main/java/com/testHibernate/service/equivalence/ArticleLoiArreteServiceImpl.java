package com.testHibernate.service.equivalence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.equivalence.ArticleLoiArreteFormToArticleLoiArrete;
import com.testHibernate.model.equivalence.ArticleLoiArrete;
import com.testHibernate.model.equivalence.ArticleLoiArreteForm;
import com.testHibernate.repo.equivalence.ArticleLoiArreteRepository;
 
@Service
public class ArticleLoiArreteServiceImpl implements ArticleLoiArreteService {
	
    private ArticleLoiArreteRepository articleLoiArreteRepository;
    private ArticleLoiArreteFormToArticleLoiArrete articleLoiArreteFormToArticleLoiArrete;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public ArticleLoiArreteServiceImpl(ArticleLoiArreteRepository articleLoiArreteRepository,
    		ArticleLoiArreteFormToArticleLoiArrete articleLoiArreteFormToArticleLoiArrete) {
		super();
		this.articleLoiArreteRepository = articleLoiArreteRepository;
		this.articleLoiArreteFormToArticleLoiArrete = articleLoiArreteFormToArticleLoiArrete;
	}


	@Override
    public List<ArticleLoiArrete> listAll() {
       List<ArticleLoiArrete> ret = new ArrayList<>();
       this.articleLoiArreteRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public ArticleLoiArrete getById(Long id) {
        return this.articleLoiArreteRepository.findById(id).orElse(null);
    }
	
	@Override
	public ArticleLoiArrete saveOrUpdate(ArticleLoiArrete ArticleLoiArrete) {
		this.articleLoiArreteRepository.save(ArticleLoiArrete);
        return ArticleLoiArrete;
	}


	@Override
	public void delete(Long id) {
		this.articleLoiArreteRepository.deleteById(id);
	}


	@Override
	public ArticleLoiArrete saveOrUpdateArticleLoiArreteForm(ArticleLoiArreteForm articleLoiArreteForm) {
		ArticleLoiArrete savedArticleLoiArrete = saveOrUpdate(this.articleLoiArreteFormToArticleLoiArrete.convert(articleLoiArreteForm));

        System.out.println("Saved ArticleLoiArrete Id: " + savedArticleLoiArrete.getId());
       
        return savedArticleLoiArrete;
	}


	@Override
	public ArticleLoiArrete getArticleLoiByIdArreteEqRef(String idArreteEqRef) {
		TypedQuery<ArticleLoiArrete> query = em.createNamedQuery("ArticleLoiArrete.findArticleLoiArreteByIdArretEqRef", ArticleLoiArrete.class).setParameter("idArreteEqRef", idArreteEqRef);
		List<ArticleLoiArrete> ret = query.getResultList();
		System.out.println("\n\n \t Single resultat = "+ret.size());
		
		ArticleLoiArrete sortie = null;
		if(ret.size()!=0) {
			sortie = ret.get(0);
		}
			return sortie;
	}
	
}
