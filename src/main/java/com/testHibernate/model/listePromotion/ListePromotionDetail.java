package com.testHibernate.model.listePromotion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;


@Entity(name="TousListeDiplomeDetail")
@NamedQueries({
	@NamedQuery(
		name = "TousListeDiplomeDetail.findByIdTousListeDiplome", 
		query = "SELECT c FROM TousListeDiplomeDetail as c WHERE c.tousListeDiplome.id = :idTousListeDiplome ")
	})
public class ListePromotionDetail {

	public ListePromotionDetail() {
		
	}

	 

	public ListePromotionDetail(Long id, ListePromotion tousListeDiplome, String numeroMatricule, String nomComplet,
			String dateNaissance, String lieuNaissance, String mention) {
		super();
		this.id = id;
		this.tousListeDiplome = tousListeDiplome;
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
	private ListePromotion tousListeDiplome;
	
	private String numeroMatricule;
	private String nomComplet;
	private String dateNaissance;
	private String lieuNaissance;
	private String mention;
	
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
	public ListePromotion getTousListeDiplome() {
		return tousListeDiplome;
	}
	public void setTousListeDiplome(ListePromotion tousListeDiplome) {
		this.tousListeDiplome = tousListeDiplome;
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
