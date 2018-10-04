package com.testHibernate.service.equivalence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.equivalence.ContentArreteFormToContentArrete;
import com.testHibernate.model.equivalence.ContentArrete;
import com.testHibernate.model.equivalence.ContentArreteForm;
import com.testHibernate.repo.equivalence.ContentArreteRepository;
 
@Service
public class ContentArreteServiceImpl implements ContentArreteService {
	
    private ContentArreteRepository contentArreteRepository;
    private ContentArreteFormToContentArrete contentArreteFormToContentArrete;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public ContentArreteServiceImpl(ContentArreteRepository contentArreteRepository,
    		ContentArreteFormToContentArrete contentArreteFormToContentArrete) {
		super();
		this.contentArreteRepository = contentArreteRepository;
		this.contentArreteFormToContentArrete = contentArreteFormToContentArrete;
	}


	@Override
    public List<ContentArrete> listAll() {
       List<ContentArrete> ret = new ArrayList<>();
       this.contentArreteRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public ContentArrete getById(Long id) {
        return this.contentArreteRepository.findById(id).orElse(null);
    }

    
	@Override
	public ContentArrete saveOrUpdate(ContentArrete ContentArrete) {
		this.contentArreteRepository.save(ContentArrete);
        return ContentArrete;
	}


	@Override
	public void delete(Long id) {
		this.contentArreteRepository.deleteById(id);
	}


	@Override
	public ContentArrete saveOrUpdateContentArreteForm(ContentArreteForm contentArreteForm) {
		ContentArrete savedContentArrete = saveOrUpdate(this.contentArreteFormToContentArrete.convert(contentArreteForm));

        System.out.println("Saved ContentArrete Id: " + savedContentArrete.getId());
       
        return savedContentArrete;
	}


	@Override
	public ContentArrete getContentByArrete(Long idArrete) {
		ContentArrete sortie = null;
		try{
			TypedQuery<ContentArrete> list = em.createNamedQuery("ContentArrete.findContentByArrete", ContentArrete.class).setParameter("idArrete", idArrete);
		
			List<ContentArrete> ret = list.getResultList();
			//System.out.println("\n\n\n RET = "+ret.size());
			if(ret.size()!=0) {
				sortie = ret.get(0);
			} else {
				sortie = null;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sortie;
	}

	 
}
