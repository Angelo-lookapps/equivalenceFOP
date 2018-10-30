package com.testHibernate.model.demande;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
		name = "FicheDemande.findFicheDemandeByStatusRejet", 
		query = "SELECT fi FROM FicheDemande as fi WHERE fi.statusRejet = 'true' "),
	@NamedQuery(
		name = "FicheDemande.findFicheDemandeByStatusEnCours", 
		query = "SELECT fi FROM FicheDemande as fi WHERE fi.statusRejet = 'false' "),
	@NamedQuery(
		name = "FicheDemande.findFicheDemandeByDate", 
		query = "SELECT fi FROM FicheDemande as fi WHERE fi.dateRetrait = :dateRetrait "),		
	@NamedQuery(
		name = "FicheDemande.findByFilterDESC", 
		query = "SELECT fi FROM FicheDemande as fi ORDER BY :champ DESC ")	,
	@NamedQuery(
		name = "FicheDemande.findByFilterASC", 
		query = "SELECT fi FROM FicheDemande as fi ORDER BY :champ ASC "),
	@NamedQuery(
		name = "FicheDemande.pagination", 
		query = "SELECT fd FROM FicheDemande as fd order by fd.id"),
	@NamedQuery(
		name = "FicheDemande.getDemandeByMonth",
		query = "SELECT COUNT(fd.id) as nbFiche FROM FicheDemande as fd WHERE date_part(?1, DATE(fd.dateAjout)) = ?2 AND statusRejet = ?3"  )

})
public class FicheDemande {
	
	public FicheDemande () {
		
	}

	public FicheDemande(Long id, CIN cin, ListesDiplome listesDiplome, String telephone, String diplome,
			String utilisation, boolean statusEnregistrement, Date dateRetrait, String dateAjout) {
		super();
		this.id = id;
		this.cin = cin;
		this.listesDiplome = listesDiplome;
		this.telephone = telephone;
		this.diplome = diplome;
		this.utilisation = utilisation;
		this.statusEnregistrement = statusEnregistrement;
		this.dateRetrait = dateRetrait;
		this.dateAjout = dateAjout;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@OneToOne
	private CIN cin;
	
	@OneToOne
	private ListesDiplome listesDiplome;
	
	private String telephone;
	private String diplome;
	private String utilisation;
	private Boolean statusEnregistrement;
	private Date dateRetrait;
	private Boolean statusRejet;
	
	public Boolean getStatusRejet() {
		return statusRejet;
	}

	public void setStatusRejet(Boolean statusRejet) {
		this.statusRejet = statusRejet;
	}

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
