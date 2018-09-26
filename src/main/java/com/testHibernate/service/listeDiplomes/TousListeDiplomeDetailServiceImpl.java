package com.testHibernate.service.listeDiplomes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.listeDiplomes.TousListeDiplomeDetailFormToTousListeDiplomeDetail;
import com.testHibernate.model.listeDiplomes.TousListeDiplomeDetail;
import com.testHibernate.model.listeDiplomes.TousListeDiplomeDetailForm;
import com.testHibernate.repo.listeDiplomes.TousListeDiplomeDetailRepository;
 
@Service
public class TousListeDiplomeDetailServiceImpl implements TousListeDiplomeDetailService {
	
    private TousListeDiplomeDetailRepository tousListeDiplomeDetailRepository;
    private TousListeDiplomeDetailFormToTousListeDiplomeDetail tousListeDiplomeDetailFormToTousListeDiplomeDetail;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
	public TousListeDiplomeDetailServiceImpl(TousListeDiplomeDetailRepository tousListeDiplomeDetailRepository,
			TousListeDiplomeDetailFormToTousListeDiplomeDetail tousListeDiplomeDetailFormToTousListeDiplomeDetail) {
		super();
		this.tousListeDiplomeDetailRepository = tousListeDiplomeDetailRepository;
		this.tousListeDiplomeDetailFormToTousListeDiplomeDetail = tousListeDiplomeDetailFormToTousListeDiplomeDetail;
	}

	@Override
    public List<TousListeDiplomeDetail> listAll() {
       List<TousListeDiplomeDetail> ret = new ArrayList<>();
       this.tousListeDiplomeDetailRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public TousListeDiplomeDetail getById(Long id) {
        return this.tousListeDiplomeDetailRepository.findById(id).orElse(null);
    }

	@Override
	public void delete(Long id) {
		this.tousListeDiplomeDetailRepository.deleteById(id);
	}
	
	@Override
	public List<TousListeDiplomeDetail> getDetailByIdTousListeDiplome(String idTousListeDiplome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TousListeDiplomeDetail saveOrUpdate(TousListeDiplomeDetail tousListeDiplomeDetail) {
		this.tousListeDiplomeDetailRepository.save(tousListeDiplomeDetail);
        return tousListeDiplomeDetail;
	}

	@Override
	public TousListeDiplomeDetail saveOrUpdateTousListeDiplomeDetailForm(TousListeDiplomeDetailForm tousListeDiplomeDetailForm) {
		TousListeDiplomeDetail savedTousListeDiplome = saveOrUpdate(tousListeDiplomeDetailFormToTousListeDiplomeDetail.convert(tousListeDiplomeDetailForm));

        System.out.println("Saved ArreteEqRef Id: " + savedTousListeDiplome.getId());  
        return savedTousListeDiplome;
	}

}
