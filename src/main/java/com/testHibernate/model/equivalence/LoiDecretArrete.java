package com.testHibernate.model.equivalence;
 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class LoiDecretArrete {
	
	
	public LoiDecretArrete() {
		super();
	}
	
	public LoiDecretArrete(Long id, ArreteEqRef arreteEqRef, String contenu) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.contenu = contenu;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ArreteEqRef arreteEqRef;
		
	@Lob 
	@Column(name="contenu", length=2048)
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
