package com.testHibernate.model.diplome;

public class ListesDiplomeForm {
 	private Long id;
	private String filiere;
	private String mention;
	private String option;
	private String ecole;
	private NiveauDiplome niveauDiplome;
	
	public NiveauDiplome getNiveauDiplome() {
		return niveauDiplome;
	}
	public void setNiveauDiplome(NiveauDiplome niveauDiplome) {
		this.niveauDiplome = niveauDiplome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
}
