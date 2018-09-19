package com.testHibernate.model.equivalence;
 

public class ArticleLoiArreteForm {
	
	
	public ArticleLoiArreteForm() {
		super();
	}

	public ArticleLoiArreteForm(Long id, ArreteEqRef arreteEqRef, String articleComplet, String signatureMinistre) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.articleComplet = articleComplet;
		this.signatureMinistre = signatureMinistre;
	}

	private Long id;

	private ArreteEqRef arreteEqRef;
		
	private String articleComplet ;
	private String signatureMinistre;
	
	

	public String getSignatureMinistre() {
		return signatureMinistre;
	}

	public void setSignatureMinistre(String signatureMinistre) {
		this.signatureMinistre = signatureMinistre;
	}

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

	public String getArticleComplet() {
		return this.articleComplet;
	}

	public void setArticleComplet(String articleComplet) {
		this.articleComplet = articleComplet;
	}
		
	
}
