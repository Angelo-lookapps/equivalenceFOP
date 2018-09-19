package com.testHibernate.converts.equivalence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ArreteEqRefToArreteEqRefForm {

	public ArreteEqRefToArreteEqRefForm() {
		
	}

	public ArreteEqRefToArreteEqRefForm(Long id, Long idListesDiplome, String anneeSortie, String titre) {
		super();
		this.id = id;
		this.idListesDiplome = idListesDiplome;
		this.anneeSortie = anneeSortie;
		this.titre = titre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idListesDiplome;
	
	private String anneeSortie;
	private String titre;
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

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
