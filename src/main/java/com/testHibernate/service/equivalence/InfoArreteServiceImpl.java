package com.testHibernate.service.equivalence;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.equivalence.InfoArreteFormToInfoArrete;
import com.testHibernate.model.equivalence.InfoArrete;
import com.testHibernate.model.equivalence.InfoArreteForm;
import com.testHibernate.repo.equivalence.InfoArreteRepository;

@Service
public class InfoArreteServiceImpl implements InfoArreteService {
	private InfoArreteRepository infoArreteRepository;
	private InfoArreteFormToInfoArrete infoArreteFormToInfoArrete;
	
	@PersistenceContext
    private EntityManager em;
	
    @Autowired
	public InfoArreteServiceImpl(InfoArreteRepository infoArreteRepository,
			InfoArreteFormToInfoArrete infoArreteFormToInfoArrete) {
		super();
		this.infoArreteRepository = infoArreteRepository;
		this.infoArreteFormToInfoArrete = infoArreteFormToInfoArrete;
	}

	@Override
	public List<InfoArrete> listAll() {
		List<InfoArrete> ret = new ArrayList<>();
		this.infoArreteRepository.findAll().forEach(ret::add); //fun with Java 8
		return ret;
	}

	@Override
	public InfoArrete getById(Long id) {
		return this.infoArreteRepository.findById(id).orElse(null);
	}

	@Override
	public InfoArrete getArreteByIdArrete(String idArreteEqRef) {
		InfoArrete sortie = null;
		try{
			TypedQuery<InfoArrete> list = em.createNamedQuery("InfoArrete.findInfoArreteByIdArrete", InfoArrete.class)
					.setParameter(1, idArreteEqRef);
		
			List<InfoArrete> ret = list.getResultList();
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

	@Override
	public InfoArrete saveOrUpdate(InfoArrete infoArrete) {
		this.infoArreteRepository.save(infoArrete);
        return infoArrete;
	}

	@Override
	public void delete(Long id) {
		this.infoArreteRepository.deleteById(id);
	}

	@Override
	public InfoArrete saveOrUpdateInfoArreteForm(InfoArreteForm infoArreteForm) {
		InfoArrete savedInfoArrete = saveOrUpdate(this.infoArreteFormToInfoArrete.convert(infoArreteForm));

        System.out.println("Saved ContentArrete Id: " + savedInfoArrete.getId());
       
        return savedInfoArrete;
	}

}
