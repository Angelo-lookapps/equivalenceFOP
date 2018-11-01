package com.testHibernate.model.diplome;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery; 

@Entity
@NamedQueries({
	@NamedQuery(
		name = "ListesDiplome.findDiplomeByCategorie", 
		query = "SELECT diplome FROM ListesDiplome diplome"
				+ " JOIN NiveauDiplome nv ON diplome.niveauDiplome.id = nv.id WHERE diplome.niveauDiplome.categorie = :categorie "),
	@NamedQuery(
		name = "ListesDiplome.findAllEcole", 
		query = "SELECT DISTINCT ld.ecole FROM ListesDiplome as ld"),
	@NamedQuery(
		name = "ListesDiplome.findDiplomeByEcole", 
		query = "SELECT ld FROM ListesDiplome as ld WHERE ld.ecole = :ecole"),
	@NamedQuery(
		name = "ListesDiplome.pagination", 
		query = "SELECT ld FROM ListesDiplome as ld order by ld.id"),
	@NamedQuery(
		name = "ListesDiplome.searchMultiple", 
		query = "SELECT ld FROM ListesDiplome as ld WHERE lower(ld.ecole) LIKE ?1 "
				+ "	AND lower(ld.filiere) LIKE ?2"
				+ " AND lower(ld.option) LIKE ?3 ORDER BY ld.id" ) 
})
public class ListesDiplome {  
	public ListesDiplome() {}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String filiere;
	private String option;
	private String ecole;
	
	@ManyToOne
	private NiveauDiplome niveauDiplome;
	
	private String dateAjout;
	
	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout2) {
		this.dateAjout =  dateAjout2;
	}
	public Long getId() {
		return id;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getEcole() {
		return ecole;
	}

	public void setEcole(String ecole) {
		this.ecole = ecole;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public NiveauDiplome getNiveauDiplome() {
		return niveauDiplome;
	}

	public void setNiveauDiplome(NiveauDiplome niveauDiplome) {
		this.niveauDiplome = niveauDiplome;
	}

	
}
