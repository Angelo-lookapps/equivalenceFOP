package com.testHibernate.model.user;
 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery; 

@Entity
@NamedQueries({ 
	@NamedQuery(
			name = "Utilisateur.findUserByLogin", 
			query = "SELECT ci FROM Utilisateur as ci  WHERE lower(ci.pseudoUser) = ?1 ")
})
public class Utilisateur {
	 
	public Utilisateur() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long _id;
	
	private String nomUser;
	private String prenomUser;
	private String emailUser;
	private String pseudoUser;
	private String mdpUser;
	 
	private String dateAjout;

	public Long getId() {
		return _id;
	}

	public void setId(Long _id) {
		this._id = _id;
	}

	public String getNomUser() {
		return nomUser;
	}

	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}

	public String getPrenomUser() {
		return prenomUser;
	}

	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}

	
	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getPseudoUser() {
		return pseudoUser;
	}

	public void setPseudoUser(String pseudoUser) {
		this.pseudoUser = pseudoUser;
	}

	public String getMdpUser() {
		return mdpUser;
	}

	public void setMdpUser(String mdpUser) {
		this.mdpUser = mdpUser;
	}

	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout) {
		this.dateAjout = dateAjout;
	}
	
	
	
}
