package com.testHibernate.model.equivalence;
 

public class ArticleLoiArreteForm {
	
	
	public ArticleLoiArreteForm() {
		super();
	}

	public ArticleLoiArreteForm(Long id, Long arreteEqRef, String contenu) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.contenu = contenu;
	}

	private Long id;

	private Long arreteEqRef;
		
	private String contenu ;

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

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
		
	
}
