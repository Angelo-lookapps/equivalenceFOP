package com.testHibernate.model.equivalence;

public class TableauArreteForm {

	public TableauArreteForm() {
		
	}

	public TableauArreteForm(Long id, ArreteEqRef arreteEqRef, String contenuTableau) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.contenuTableau = contenuTableau;
	}

	private Long id;
	private ArreteEqRef arreteEqRef;
	private String contenuTableau;
	
	
	public String getContenuTableau() {
		return contenuTableau;
	}

	public void setContenuTableau(String contenuTableau) {
		this.contenuTableau = contenuTableau;
	}

	public ArreteEqRef getArreteEqRef() {
		return arreteEqRef;
	}
	public void setArreteEqRef(ArreteEqRef arreteEqRef) {
		this.arreteEqRef = arreteEqRef;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
