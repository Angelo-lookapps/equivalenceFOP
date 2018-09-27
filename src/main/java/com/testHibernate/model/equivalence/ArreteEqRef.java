package com.testHibernate.model.equivalence;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.testHibernate.model.diplome.ListesDiplome;

@Entity
@NamedQueries({
	@NamedQuery(
		name = "ArreteEqRef.findArreteByTitre", 
		query = "SELECT c FROM ArreteEqRef as c WHERE c.titre = :titre "),
	@NamedQuery(
		name = "ArreteEqRef.updateArreteEqRef", 
		query = "UPDATE ArreteEqRef SET id = :id, listesDiplome.id = :idListesDiplome, anneeSortie = :anneeSortie, titre = :titre WHERE id = :id")
})
public class ArreteEqRef {

	public ArreteEqRef() {
		
	}

	public ArreteEqRef(Long id, ListesDiplome listesDiplome, String anneeSortie, String titre) {
		super();
		this.id = id;
		this.listesDiplome = listesDiplome;
		this.anneeSortie = anneeSortie;
		this.titre = titre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ListesDiplome listesDiplome;
	
	private String anneeSortie;
	private String titre;
	
	@Column(name = "dateAjout",columnDefinition = "DATE DEFAULT CURRENT_DATE")
	private Date dateAjout;
	
	
	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
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

	public ListesDiplome getListesDiplome() {
		return listesDiplome;
	}

	public void setListesDiplome(ListesDiplome listesDiplome) {
		this.listesDiplome = listesDiplome;
	}

	public String getAnneeSortie() {
		return anneeSortie;
	}

	public void setAnneeSortie(String anneeSortie) {
		this.anneeSortie = anneeSortie;
	}
	
	
}
