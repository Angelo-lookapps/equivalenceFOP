package com.testHibernate.model.diplome;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
public class ListesDiplome {
	
	public ListesDiplome() {}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String filiere;
	private String mention;
	private String option;
	private String ecole;
	
	@ManyToOne
	private NiveauDiplome niveauDiplome;

	public Long getId() {
		return id;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public String getMention() {
		return mention;
	}

	public void setMention(String mention) {
		this.mention = mention;
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
