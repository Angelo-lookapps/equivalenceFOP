package com.testHibernate.model.diplome;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ListesDiplome {
	
	public ListesDiplome() {}
	public ListesDiplome(Long id, String niveau, String filiere, String mention, String option, String ecole) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.filiere = filiere;
		this.mention = mention;
		this.option = option;
		this.ecole = ecole;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String niveau;
	private String filiere;
	private String mention;
	private String option;
	private String ecole;
	
	public Long getId() {
		return id;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
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

	
}
