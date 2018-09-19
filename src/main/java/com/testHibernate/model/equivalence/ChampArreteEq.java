package com.testHibernate.model.equivalence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ChampArreteEq {

	public ChampArreteEq() {
		
	}
	
	
	public ChampArreteEq(Long id, ArreteEqRef arreteEqRef, int numero, String delivreeA, String titulaireDe,
			String specialite) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.numero = numero;
		this.delivreeA = delivreeA;
		this.titulaireDe = titulaireDe;
		this.specialite = specialite;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
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
