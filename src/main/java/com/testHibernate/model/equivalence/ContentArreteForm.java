package com.testHibernate.model.equivalence;
 
 

public class ContentArreteForm {
	
	public ContentArreteForm() {}

	
	public ContentArreteForm(Long id, ArreteEqRef arreteEqRef, String contenu, String dateAjout) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.contenu = contenu;
		this.dateAjout = dateAjout;
	}
 
	private Long id;  
	private ArreteEqRef arreteEqRef; 
	private String contenu; 
	private String dateAjout;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArreteEqRef getArreteEqRef() {
		return arreteEqRef;
	}

	public void setArreteEqRef(ArreteEqRef arreteEqRef) {
		this.arreteEqRef = arreteEqRef;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout) {
		this.dateAjout = dateAjout;
	}
	
	
}
