package com.testHibernate.model.equivalence;

public class ChampArreteEqForm {

	public ChampArreteEqForm() {
		
	}

	private Long id;
	private ArreteEqRef arreteEqRef;
	private int numero;
	private String delivreeA;
	private String titulaireDe;
	private String specialite;

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
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getDelivreeA() {
		return delivreeA;
	}
	public void setDelivreeA(String delivreeA) {
		this.delivreeA = delivreeA;
	}
	public String getTitulaireDe() {
		return titulaireDe;
	}
	public void setTitulaireDe(String titulaireDe) {
		this.titulaireDe = titulaireDe;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	
	
}
