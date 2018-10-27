package com.testHibernate.model.equivalence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
		query = "UPDATE ArreteEqRef SET id = :id, listesDiplome.id = :idListesDiplome, anneeSortie = :anneeSortie, titre = :titre WHERE id = :id"),
	@NamedQuery(
		name = "ArreteEqRef.pagination", 
		query = "SELECT ld FROM ArreteEqRef as ld order by ld.id"),
	@NamedQuery(
		name = "ArreteEqRef.findArreteByIdDiplome", 
		query = "SELECT ld FROM ArreteEqRef as ld WHERE ld.listesDiplome.id = :idDiplome order by ld.id") 
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
	
	@OneToOne
	private ListesDiplome listesDiplome;
	
	private String anneeSortie;
	private String titre;
	private Boolean status;
	private String dateAjout;
	
	@ManyToOne
	private TypeArreteJasper typeArreteJasper;
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean statut) {
		this.status = statut;
	}

	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout) {
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

	public TypeArreteJasper getTypeArreteJasper() {
		return typeArreteJasper;
	}

	public void setTypeArreteJasper(TypeArreteJasper typeArreteJasper) {
		this.typeArreteJasper = typeArreteJasper;
	}
	
	
}
