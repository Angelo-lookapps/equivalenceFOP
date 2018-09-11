package com.testHibernate.model.equivalence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ArreteEqRef {

	public ArreteEqRef() {
		
	}
	
	public ArreteEqRef(Long id, Long idListesDiplome, String anneeSortie) {
		super();
		this.setId(id);
		this.setIdListesDiplome(idListesDiplome);
		this.setAnneeSortie(anneeSortie);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idListesDiplome;
	
	private String anneeSortie;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdListesDiplome() {
		return idListesDiplome;
	}

	public void setIdListesDiplome(Long idListesDiplome) {
		this.idListesDiplome = idListesDiplome;
	}

	public String getAnneeSortie() {
		return anneeSortie;
	}

	public void setAnneeSortie(String anneeSortie) {
		this.anneeSortie = anneeSortie;
	}
	
	
}
