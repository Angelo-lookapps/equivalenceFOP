package com.testHibernate.model.listePromotion;

import java.sql.Date;

import javax.persistence.TemporalType;

public class ListePromotionDetailForm {

	public ListePromotionDetailForm() {
		
	}

	public ListePromotionDetailForm(Long id, ListePromotion listePromotion, String numeroMatricule, String nomComplet,
			String dateNaissance, String lieuNaissance, String mention) {
		super();
		this.id = id;
		this.listePromotion = listePromotion;
		this.numeroMatricule = numeroMatricule;
		this.nomComplet = nomComplet;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.mention = mention;
	}



	private Long id;
 	private ListePromotion listePromotion;
 	private String numeroMatricule;
	private String nomComplet;
	private String dateNaissance;
	private String lieuNaissance;
	private String mention;
	private Date dateAjout;
	

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	
	public String getMention() {
		return mention;
	}
	public void setMention(String mention) {
		this.mention = mention;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	 
	public ListePromotion getListePromotion() {
		return listePromotion;
	}
	public void setListePromotion(ListePromotion listePromotion) {
		this.listePromotion = listePromotion;
	}
	public String getNumeroMatricule() {
		return numeroMatricule;
	}
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	
	
	
	
}
