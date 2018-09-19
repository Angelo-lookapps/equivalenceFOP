package com.testHibernate.model.equivalence;

public class TableauArreteForm {

	public TableauArreteForm() {
		
	}

	public TableauArreteForm(Long id, Long idArreteEqRef, String titre, String organismePaysDelivrance, String cadre,
			String echelle, String classementCategorie) {
		super();
		this.id = id;
		this.idArreteEqRef = idArreteEqRef;
		this.titre = titre;
		this.organismePaysDelivrance = organismePaysDelivrance;
		this.cadre = cadre;
		this.echelle = echelle;
		this.classementCategorie = classementCategorie;
	}

	private Long id;
	
	private Long idArreteEqRef;
	
	private String titre;
	private String organismePaysDelivrance;
	private String cadre;
	private String echelle;
	private String classementCategorie;
	

	public Long getIdArreteEqRef() {
		return idArreteEqRef;
	}
	public void setIdArreteEqRef(Long idArreteEqRef) {
		this.idArreteEqRef = idArreteEqRef;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getOrganismePaysDelivrance() {
		return this.organismePaysDelivrance;
	}
	public void setOrganismePaysDelivrance(String organismePaysDelivrance) {
		this.organismePaysDelivrance = organismePaysDelivrance;
	}
	public String getCadre() {
		return cadre;
	}
	public void setCadre(String cadre) {
		this.cadre = cadre;
	}
	public String getEchelle() {
		return echelle;
	}
	public void setEchelle(String echelle) {
		this.echelle = echelle;
	}
	public String getClassementCategorie() {
		return classementCategorie;
	}
	public void setClassementCategorie(String classementCategorie) {
		this.classementCategorie = classementCategorie;
	}
	
	
}
