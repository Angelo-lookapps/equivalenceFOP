package com.testHibernate.model.demande;

import java.sql.Date;

import com.testHibernate.model.cin.CIN;
import com.testHibernate.model.diplome.ListesDiplome;

public class FicheDemandeForm {
	
	public FicheDemandeForm () {
		
	}
	
    public FicheDemandeForm(Long id, CIN cin, ListesDiplome listesDiplome, String telephone, String diplome,
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

	private Long id;
	private CIN cin;
	private ListesDiplome listesDiplome;
	private String telephone;
	private String diplome;
	private String utilisation;
	private boolean statusEnregistrement;
	private Date dateRetrait; 
	
	private String dateAjout;
	
	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout2) {
		this.dateAjout = dateAjout2;
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
