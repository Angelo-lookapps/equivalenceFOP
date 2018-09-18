package com.testHibernate.model.demande;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.diplome.ListesDiplome;

@Entity
@NamedQueries({
	@NamedQuery(
		name = "FicheDemande.findFicheDemandeByCIN", 
		query = "SELECT fi FROM FicheDemande as fi  WHERE fi.cin.id = :idCIN"),
	@NamedQuery(
		name = "FicheDemande.findFicheDemandeByStatus", 
		query = "SELECT fi FROM FicheDemande as fi WHERE fi.statusEnregistrement = :status "),
	@NamedQuery(
		name = "FicheDemande.findFicheDemandeByDate", 
		query = "SELECT fi FROM FicheDemande as fi WHERE fi.dateRetrait = :dateRetrait ")		
})
public class FicheDemande {
	
	public FicheDemande () {
		
	}

	public FicheDemande(Long id, CIN cin, ListesDiplome listesDiplome, String telephone, String diplome,
			String utilisation, boolean statusEnregistrement, Date dateRetrait) {
		super();
		this.id = id;
		this.cin = cin;
		this.listesDiplome = listesDiplome;
		this.telephone = telephone;
		this.diplome = diplome;
		this.utilisation = utilisation;
		this.statusEnregistrement = statusEnregistrement;
		this.dateRetrait = dateRetrait;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
	private CIN cin;
	
	@ManyToOne
	private ListesDiplome listesDiplome;
	
	private String telephone;
	private String diplome;
	private String utilisation;
	private boolean statusEnregistrement;
	private Date dateRetrait;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public CIN getCin() {
		return cin;
	}
	public void setCin(CIN cin) {
		this.cin = cin;
	}
	public ListesDiplome getListesDiplome() {
		return listesDiplome;
	}
	public void setListesDiplome(ListesDiplome listesDiplome) {
		this.listesDiplome = listesDiplome;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	public String getUtilisation() {
		return utilisation;
	}
	public void setUtilisation(String utilisation) {
		this.utilisation = utilisation;
	}
	public boolean getStatusEnregistrement() {
		return statusEnregistrement;
	}
	public void setStatusEnregistrement(boolean statusEnregistrement) {
		this.statusEnregistrement = statusEnregistrement;
	}
	public Date getDateRetrait() {
		return dateRetrait;
	}
	public void setDateRetrait(Date dateRetrait) {
		this.dateRetrait = dateRetrait;
	}
	
		
}
