package com.testHibernate.model.equivalence;
 

public class LoiDecretArreteForm {
	
	
	public LoiDecretArreteForm() {
		super();
	}
	
	public LoiDecretArreteForm(Long id, ArreteEqRef arreteEqRef, String contenu) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.contenu = contenu;
	}

	private Long id;
	private ArreteEqRef arreteEqRef;
	private String contenu ;
	
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

	
	
}
