package com.testHibernate.service.historique;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.repo.historique.ActiviteRecentRepository; 

@Service
public class ActiviteRecentServiceImpl implements ActiviteRecentService {

	private ActiviteRecentRepository activiteRecentRepository; 

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public ActiviteRecentServiceImpl(ActiviteRecentRepository activiteRecentRepository ) {
		super();
		this.activiteRecentRepository = activiteRecentRepository; 
	}

	@Override
	public List<ActiviteRecent> listAll() {
		List<ActiviteRecent> ret = new ArrayList<>();
		this.activiteRecentRepository.findAll().forEach(ret::add); // fun with Java 8
		return ret;
	}  

	@Override
	public ActiviteRecent saveOrUpdate(ActiviteRecent activiteRecent) {
		this.activiteRecentRepository.save(activiteRecent);
		return activiteRecent;
	}
 

	@Override
	public void delete(Long id) {
		this.activiteRecentRepository.deleteById(id);
	}

	@Override
	public List<ActiviteRecent> getRecentActiviteByNumber(int number) {
		TypedQuery<ActiviteRecent> query = em.createNamedQuery("ActiviteRecent.find5RecentActivite", ActiviteRecent.class);
		List<ActiviteRecent> ret = query.setMaxResults(number).getResultList();
		
		return ret;
	}

	@Override
	public ActiviteRecent getById(Long id) {
		return this.activiteRecentRepository.findById(id).orElse(null);
		 
	}

	@Override
	public int deleteTheLatest(String dateCompare) {
		int ret = 0;
		try {
			ret = em.createNamedQuery("ActiviteRecent.deleteTheLatest", ActiviteRecent.class)
						  .setParameter("dateCompare", dateCompare).executeUpdate();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}

	@Override
	public int deleteAllLast() {
		List<ActiviteRecent> toDelete = this.findASC();
		int ret = 0;
		int intervalle = toDelete.size()-10;  //ex: 12-10 = 2 -> 10
		
		if(toDelete.size() != 0 && intervalle > 0) {
			for(int i=0 ; i < intervalle+5 ; i++) {
				this.activiteRecentRepository.deleteById(toDelete.get(i).getId());
			}
			ret = intervalle+5;
		}
		
		return ret;
	}

	@Override
	public List<ActiviteRecent> findASC() {
		TypedQuery<ActiviteRecent> query = em.createNamedQuery("ActiviteRecent.findASC", ActiviteRecent.class);
		List<ActiviteRecent> ret = query.getResultList();
		
		return ret;
	}
 
}
