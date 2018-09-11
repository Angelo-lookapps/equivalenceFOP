package com.testHibernate.model.demande;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FicheDemande {
	
	public FicheDemande () {
		
	}

	public FicheDemande(Long id, Long idCIN, Long idListesDiplome, String telephone, String diplome, String utilisation,
			int statusEnregistrement, Date dateRetrait) {
		super();
		this.id = id;
		this.idCIN = idCIN;
		this.idListesDiplome = idListesDiplome;
		this.telephone = telephone;
		this.diplome = diplome;
		this.utilisation = utilisation;
		this.statusEnregistrement = statusEnregistrement;
		this.dateRetrait = dateRetrait;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long idCIN;
	
	private Long idListesDiplome;
	
	private String telephone;
	private String diplome;
	private String utilisation;
	private int statusEnregistrement;
	private Date dateRetrait;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCIN() {
		return idCIN;
	}
	public void setIdCIN(Long idCIN) {
		this.idCIN = idCIN;
	}
	public Long getIdListesDiplome() {
		return idListesDiplome;
	}
	public void setIdListesDiplome(Long idListesDiplome) {
		this.idListesDiplome = idListesDiplome;
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
	public int getStatusEnregistrement() {
		return statusEnregistrement;
	}
	public void setStatusEnregistrement(int statusEnregistrement) {
		this.statusEnregistrement = statusEnregistrement;
	}
	public Date getDateRetrait() {
		return dateRetrait;
	}
	public void setDateRetrait(Date dateRetrait) {
		this.dateRetrait = dateRetrait;
	}
	
		
}
