package com.testHibernate.model.cin;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class CIN {
	
	
	public CIN(Long id, String nom, String prenom, Date dateNaissance, String lieuNaissance, String numeroCIN,
			String adresseActuelle, Date dateDelivrance, String lieuDelivrance, String nationalite, String fonction,
			String lieuTravail, Blob photo) {
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
	
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String lieuNaissance;
	private String numeroCIN;
	private String adresseActuelle;
	private Date dateDelivrance;
	private String lieuDelivrance;
	private String nationalite;
	private String fonction;
	private String lieuTravail;
	
	@Lob
	private Blob photo;

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

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	
	
}
