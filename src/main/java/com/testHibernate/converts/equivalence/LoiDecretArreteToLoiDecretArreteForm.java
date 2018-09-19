package com.testHibernate.converts.equivalence;
 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class LoiDecretArreteToLoiDecretArreteForm {
	
	
	public LoiDecretArreteToLoiDecretArreteForm() {
		super();
	}
	
	public LoiDecretArreteToLoiDecretArreteForm(Long id, Long arreteEqRef, String articleComplet, String signatureMinistre) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.articleComplet = articleComplet;
		this.signatureMinistre = signatureMinistre;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Long arreteEqRef;
		
	private String articleComplet ;
	private String signatureMinistre ;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getArreteEqRef() {
		return arreteEqRef;
	}

	public void setArreteEqRef(Long arreteEqRef) {
		this.arreteEqRef = arreteEqRef;
	}

	public String getArticleComplet() {
		return articleComplet;
	}

	public void setArticleComplet(String articleComplet) {
		this.articleComplet = articleComplet;
	}

	public String getSignatureMinistre() {
		return signatureMinistre;
	}

	public void setSignatureMinistre(String signatureMinistre) {
		this.signatureMinistre = signatureMinistre;
	}

	
	
}
