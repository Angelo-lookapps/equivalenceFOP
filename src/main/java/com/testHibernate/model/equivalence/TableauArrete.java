package com.testHibernate.model.equivalence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class TableauArrete {

	public TableauArrete() {
		
	}
	
	public TableauArrete(Long id, ArreteEqRef arreteEqRef, String contenuTableau) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.contenuTableau = contenuTableau;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ArreteEqRef arreteEqRef;
	
	@Lob 
	@Column(name="contenuTableau", length=2048)
	private String contenuTableau;
	

	
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

	public String getContenuTableau() {
		return contenuTableau;
	}

	public void setContenuTableau(String contenuTableau) {
		this.contenuTableau = contenuTableau;
	}
	
	
}
