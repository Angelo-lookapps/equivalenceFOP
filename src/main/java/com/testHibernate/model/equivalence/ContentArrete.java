package com.testHibernate.model.equivalence;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@Entity
@NamedQueries({
	@NamedQuery(
		name = "ContentArrete.findContentByArrete", 
		query = "SELECT c FROM ContentArrete as c WHERE c.arreteEqRef.id = :idArrete ")
 })
public class ContentArrete {
	
	public ContentArrete() {}

	
	public ContentArrete(Long id, ArreteEqRef arreteEqRef, String contenu, String dateAjout) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.contenu = contenu;
		this.dateAjout = dateAjout;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@OneToOne(cascade = {CascadeType.ALL})
	private ArreteEqRef arreteEqRef;
	
	//@Lob
	@Column(length= 100000)
	private String contenu;
	
	private String dateAjout;

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

	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout) {
		this.dateAjout = dateAjout;
	}
	
	
}
