package com.testHibernate.model.equivalence;
 

public class LoiDecretArreteForm {
	
	
	public LoiDecretArreteForm() {
		super();
	}
	
	public LoiDecretArreteForm(Long id, Long arreteEqRef, String articleComplet, String signatureMinistre) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.articleComplet = articleComplet;
		this.signatureMinistre = signatureMinistre;
	}

	private Long id;
	
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
