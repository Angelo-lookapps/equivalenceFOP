package com.testHibernate.model.equivalence;
 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(
		name = "ArticleLoiArrete.findArticleLoiArreteByIdArretEqRef", 
		query = "SELECT c FROM ArticleLoiArrete as c WHERE c.arreteEqRef.id = :idArreteEqRef ")
	})
public class ArticleLoiArrete {
	
	
	public ArticleLoiArrete() {
		super();
	}

	public ArticleLoiArrete(Long id, ArreteEqRef arreteEqRef, String articleComplet, String signatureMinistre) {
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
	private ArreteEqRef arreteEqRef;
		
	@Lob 
	@Column(name="articleComplet", length=2048)
	private String articleComplet ;
	
	@Lob 
	@Column(name="signatureMinistre", length=2048)
	private String signatureMinistre ;

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
		return articleComplet;
	}

	public void setArticleComplet(String articleComplet) {
		this.articleComplet = articleComplet;
	}


	
}
