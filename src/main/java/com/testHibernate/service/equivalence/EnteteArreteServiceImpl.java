package com.testHibernate.service.equivalence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.testHibernate.converts.equivalence.EnteteArreteFormToEnteteArrete;
import com.testHibernate.model.equivalence.EnteteArrete;
import com.testHibernate.model.equivalence.EnteteArreteForm;
import com.testHibernate.repo.equivalence.EnteteArreteRepository;
 
@Service
public class EnteteArreteServiceImpl implements EnteteArreteService {
	
    private EnteteArreteRepository enteteArreteRepository;
    private EnteteArreteFormToEnteteArrete enteteArreteFormToEnteteArrete;
    
    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    public EnteteArreteServiceImpl(EnteteArreteRepository enteteArreteRepository,
    		EnteteArreteFormToEnteteArrete enteteArreteFormToEnteteArrete) {
		super();
		this.enteteArreteRepository = enteteArreteRepository;
		this.enteteArreteFormToEnteteArrete = enteteArreteFormToEnteteArrete;
	}


	@Override
    public List<EnteteArrete> listAll() {
       List<EnteteArrete> ret = new ArrayList<>();
       this.enteteArreteRepository.findAll().forEach(ret::add); //fun with Java 8
       return ret;
    }

	@Override
    public EnteteArrete getById(Long id) {
        return this.enteteArreteRepository.findById(id).orElse(null);
    }


	@Override
	public EnteteArrete getEnteteByIdArreteEqRef(String idArreteEqRef) {
		TypedQuery<EnteteArrete> query = em.createNamedQuery("EnteteArrete.findEnteteByIdArretEqRef", EnteteArrete.class).setParameter("idArreteEqRef", idArreteEqRef);
		List<EnteteArrete> ret = query.getResultList();
		System.out.println("\n\n \t Single resultat = "+ret.size());
		
		EnteteArrete sortie = null;
		if(ret.size()!=0) {
			sortie = ret.get(0);
		}
		
		System.out.println("\n\n \t Single resultat = "+sortie.getId()+" titreG: "+sortie.getTitreGauche()+sortie.getId()+" titreD: "+sortie.getTitreDroite());
		
		return sortie;
	}

	
	@Override
	public EnteteArrete saveOrUpdate(EnteteArrete EnteteArrete) {
		this.enteteArreteRepository.save(EnteteArrete);
        return EnteteArrete;
	}


	@Override
	public void delete(Long id) {
		this.enteteArreteRepository.deleteById(id);
	}


	@Override
	public EnteteArrete saveOrUpdateEnteteArreteForm(EnteteArreteForm enteteArreteForm) {
		EnteteArrete savedEnteteArrete = saveOrUpdate(this.enteteArreteFormToEnteteArrete.convert(enteteArreteForm));

        System.out.println("Saved EnteteArrete Id: " + savedEnteteArrete.getId());
       
        return savedEnteteArrete;
	}

	@Override
	public EnteteArrete storeFile(MultipartFile file, EnteteArrete cible) throws Exception {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
            }

            EnteteArrete enteteArrete = cible;
            enteteArrete.setLogo(file.getBytes());

            return enteteArreteRepository.save(enteteArrete);
            
        } catch (Exception ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
	
}
