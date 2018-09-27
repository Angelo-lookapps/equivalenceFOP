package com.testHibernate.model.cin;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob; 
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

@Entity
@NamedQueries({
	@NamedQuery(
		name = "CIN.findAllCIN", 
		query = "SELECT ci FROM CIN as ci  WHERE ci.nom = :nom"),
	@NamedQuery(
		name = "CIN.findByNom", 
		query = "SELECT c FROM CIN c WHERE c.nom = :nom "),
	@NamedQuery(
		name = "CIN.findAllLieuDelivrance", 
		query = "SELECT DISTINCT c.lieuDelivrance FROM CIN as c")
	})
public class CIN {
	
	public CIN(Long id, String nom, String prenom, Date dateNaissance, String lieuNaissance, String numeroCIN,
			String adresseActuelle, Date dateDelivrance, String lieuDelivrance, String nationalite, String fonction,
			String lieuTravail, byte[] photo) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setDateNaissance(dateNaissance);
		this.setLieuNaissance(lieuNaissance);
		this.setNumeroCIN(numeroCIN);
		this.setAdresseActuelle(adresseActuelle);
		this.setDateDelivrance(dateDelivrance);
		this.setLieuDelivrance(lieuDelivrance);
		this.setNationalite(nationalite);
		this.setFonction(fonction);
		this.setLieuTravail(lieuTravail);
		this.setPhoto(photo);
	}

	public CIN() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "CIN nom is required.")
	private String nom;
	
	@NotNull(message = "CIN prenom is required.")
	private String prenom;
	
	@NotNull(message = "CIN dateNaissance is required.")
	private Date dateNaissance;
	
	@NotNull(message = "CIN lieuNaissance is required.")
	private String lieuNaissance;
	
	@NotNull(message = "CIN numeroCIN is required.")
	private String numeroCIN;
	
	@NotNull(message = "CIN adresseActuelle is required.")
	private String adresseActuelle;
	
	@NotNull(message = "CIN dateDelivrance is required.")
	private Date dateDelivrance;
	
	@NotNull(message = "CIN lieuDelivrance is required.")
	private String lieuDelivrance;
	
	@NotNull(message = "CIN nationalite is required.")
	private String nationalite;
	
	@NotNull(message = "CIN fonction is required.")
	private String fonction;
	
	@NotNull(message = "CIN lieuTravail is required.")
	private String lieuTravail;
	
	@Lob
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] photo;
 
	private String dateAjout;
	
	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout) {
		this.dateAjout = dateAjout;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getNumeroCIN() {
		return numeroCIN;
	}

	public void setNumeroCIN(String numeroCIN) {
		this.numeroCIN = numeroCIN;
	}

	public String getAdresseActuelle() {
		return adresseActuelle;
	}

	public void setAdresseActuelle(String adresseActuelle) {
		this.adresseActuelle = adresseActuelle;
	}

	public Date getDateDelivrance() {
		return dateDelivrance;
	}

	public void setDateDelivrance(Date dateDelivrance) {
		this.dateDelivrance = dateDelivrance;
	}

	public String getLieuDelivrance() {
		return lieuDelivrance;
	}

	public void setLieuDelivrance(String lieuDelivrance) {
		this.lieuDelivrance = lieuDelivrance;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getFonction() {
		return fonction;
	}

	public void setFonction(String fonction) {
		this.fonction = fonction;
	}

	public String getLieuTravail() {
		return lieuTravail;
	}

	public void setLieuTravail(String lieuTravail) {
		this.lieuTravail = lieuTravail;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
	
}
