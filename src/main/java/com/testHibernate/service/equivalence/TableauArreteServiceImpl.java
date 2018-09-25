package com.testHibernate.service.equivalence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.equivalence.TableauArreteFormToTableauArrete;
import com.testHibernate.model.equivalence.TableauArrete;
import com.testHibernate.model.equivalence.TableauArreteForm;
import com.testHibernate.repo.equivalence.TableauArreteRepository;
 
@Service
public class TableauArreteServiceImpl implements TableauArreteService {
	
    private TableauArreteRepository tableauArreteRepository;
    private TableauArreteFormToTableauArrete tableauArreteFormToTableauArrete;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public TableauArreteServiceImpl(TableauArreteRepository tableauArreteRepository,
    		TableauArreteFormToTableauArrete tableauArreteFormToTableauArrete) {
		super();
		this.tableauArreteRepository = tableauArreteRepository;
		this.tableauArreteFormToTableauArrete = tableauArreteFormToTableauArrete;
	}


	@Override
    public List<TableauArrete> listAll() {
       List<TableauArrete> ret = new ArrayList<>();
       this.tableauArreteRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public TableauArrete getById(Long id) {
        return this.tableauArreteRepository.findById(id).orElse(null);
    }
	
	@Override
	public TableauArrete saveOrUpdate(TableauArrete tableauArrete) {
		this.tableauArreteRepository.save(tableauArrete);
        return tableauArrete;
	}


	@Override
	public void delete(Long id) {
		this.tableauArreteRepository.deleteById(id);
	}


	@Override
	public TableauArrete saveOrUpdateTableauArreteForm(TableauArreteForm tableauArreteForm) {
		TableauArrete savedTableauArrete = saveOrUpdate(this.tableauArreteFormToTableauArrete.convert(tableauArreteForm));

        System.out.println("Saved TableauArrete Id: " + savedTableauArrete.getId());
       
        return savedTableauArrete;
	}


	@Override
	public TableauArrete getTableauByIdArreteEqRef(String idArreteEqRef) {
		TypedQuery<TableauArrete> query = em.createNamedQuery("TableauArrete.findTableauArreteByIdArretEqRef", TableauArrete.class).setParameter("idArreteEqRef", idArreteEqRef);
		List<TableauArrete> ret = query.getResultList();
		System.out.println("\n\n \t Single resultat = "+ret.size());
		
		TableauArrete sortie = null;
		if(ret.size()!=0) {
			sortie = ret.get(0);
		}
			return sortie;
	}


		
}
