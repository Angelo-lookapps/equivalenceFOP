package com.testHibernate.service.cin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testHibernate.converts.cin.CINFormToCIN;
import com.testHibernate.helpers.GlobalHelper;
import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.cin.CINForm;
import com.testHibernate.model.diplome.ListesDiplome;
import com.testHibernate.repo.cin.CINRepository;
 
@Service
public class CINServiceImpl implements CINService {
	
    private CINRepository cinRepository;
    private CINFormToCIN cinFormToCin;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public CINServiceImpl(com.testHibernate.repo.cin.CINRepository cinRepository, CINFormToCIN cinFormToCin) {
		super();
		this.cinRepository = cinRepository;
		this.cinFormToCin = cinFormToCin;
	}

	@Override
    public List<CIN> listAll() {
       List<CIN> cins = new ArrayList<>();
       cinRepository.findAll().forEach(cins::add); //fun with Java 8
       return cins;
    }

    @Override
    public CIN getById(Long id) {
        return cinRepository.findById(id).orElse(null);
    }

    @Override
    public CIN saveOrUpdate(CIN cin) {
    	cinRepository.save(cin);
        return cin;
    }

    @Override
    public void delete(Long id) {
    	cinRepository.deleteById(id);

    }

	@Override
	public CIN saveOrUpdateCINForm(CINForm cinForm) {
        CIN savedCin = saveOrUpdate(cinFormToCin.convert(cinForm));

        System.out.println("Saved CIN Id: " + savedCin.getId());
        return savedCin;
	}

	@Override
	public List<CIN> listAllCIN(String nom) {
		TypedQuery<CIN> query = em.createNamedQuery("CIN.findAllCIN", CIN.class).setParameter("nom", nom);
		List<CIN> ret = query.getResultList();
		return ret;
		//return null;
	}

	@Override
	public List<String> getAllLieuDelivrance() {
		List<String> ret = new ArrayList<String>();
		try{
			List<String> cinList = em.createNamedQuery("CIN.findAllLieuDelivrance", String.class).getResultList();
			ret = cinList;
			/*System.out.println("\n\n \t cinList.Size = "+cinList.size()+" \n\n");
			//List<CIN> cinList = query.getResultList();
			
			for(CIN cin : cinList) {
				ret.add(cin.getLieuDelivrance());
			}*/
		}catch(Exception e) {
			e.printStackTrace();
			//System.out.println("\n ERRRRUUUUUUUURRRRRRRRRRRR   "+e.getMessage());
		}
		return ret;
	}

	@Override
	public List<CIN> pagination(int page, int limit) {
		int offset = (page-1) * limit;
		TypedQuery<CIN> query = em.createNamedQuery("CIN.pagination", CIN.class);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<CIN> ret = query.getResultList();
		
		return ret; 
	}

	@Override
	public List<CIN> searchMultiple(String nom, String prenom, String numeroCIN, 
			String adresseActuelle, String fonction, String lieuTravail) {
		TypedQuery<CIN> query = em.createNamedQuery("CIN.searchMultiple", CIN.class)
				.setParameter(1, "%"+nom.toLowerCase()+"%")
				.setParameter(2, "%"+prenom.toLowerCase()+"%") 
				.setParameter(3, "%"+numeroCIN.toLowerCase()+"%")
				.setParameter(4, "%"+adresseActuelle.toLowerCase()+"%") 
				.setParameter(5, "%"+fonction.toLowerCase()+"%")
				.setParameter(6, "%"+nom.toLowerCase()+"%");
		List<CIN> ret = query.getResultList();
		
		System.out.println("\n searchMultiple  CIN== "+ret.size());
		
		return ret;
	}

}
