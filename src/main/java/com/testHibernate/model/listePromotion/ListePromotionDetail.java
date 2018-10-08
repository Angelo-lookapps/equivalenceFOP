package com.testHibernate.model.listePromotion;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.testHibernate.model.cin.CIN;


@Entity(name="ListePromotionDetail")
@NamedQueries({
	@NamedQuery(
		name = "ListePromotionDetail.findByIdListePromotion", 
		query = "SELECT c FROM ListePromotionDetail as c WHERE c.listePromotion.id = :idListePromotion "),
	@NamedQuery(
			name = "ListePromotionDetail.pagination", 
			query = "SELECT ld FROM ListePromotionDetail as ld order by ld.id"),
	@NamedQuery(
			name = "ListePromotionDetail.findAdmisByCIN", 
			query = "SELECT ld FROM ListePromotionDetail as ld WHERE ld.cin.id = :idCin order by ld.id")
 })
public class ListePromotionDetail {

	public ListePromotionDetail() {
		
	}

	public ListePromotionDetail(Long id, ListePromotion listePromotion, String numeroMatricule, String nomComplet,
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


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ListePromotion listePromotion;
	
	private String numeroMatricule;
	private String nomComplet;
	
	@ManyToOne
	private CIN cin;
	
	private String dateNaissance;
	private String lieuNaissance;
	private String mention;
	
	private String dateAjout;
	
	
	
	
	public CIN getCin() {
		return cin;
	}

	public void setCin(CIN cin) {
		this.cin = cin;
	}

	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout) {
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
