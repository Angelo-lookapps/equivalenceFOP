package com.testHibernate.model.equivalence;
 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ArticleLoiArrete {
	
	
	public ArticleLoiArrete() {
		super();
	}

	public ArticleLoiArrete(Long id, Long arreteEqRef, String contenu) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.contenu = contenu;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
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
