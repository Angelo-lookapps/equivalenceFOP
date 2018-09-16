package com.testHibernate.model.demande;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

public class FicheDemandeDetailForm {
	
	public FicheDemandeDetailForm () {
		
	}

	public FicheDemandeDetailForm(Long id, FicheDemande ficheDemande, String anneeDeb, String anneeFin, String mention,
			Blob image) {
		super();
		this.id = id;
		this.ficheDemande = ficheDemande;
		this.anneeDeb = anneeDeb;
		this.anneeFin = anneeFin;
		this.mention = mention;
		this.image = image;
	}

    private Long id;
	private FicheDemande ficheDemande;
	private String anneeDeb;
	private String anneeFin;
	private String mention;
	private Blob image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FicheDemande getFicheDemande() {
		return ficheDemande;
	}

	public void setFicheDemande(FicheDemande ficheDemande) {
		this.ficheDemande = ficheDemande;
	}

	public String getAnneeDeb() {
		return anneeDeb;
	}

	public void setAnneeDeb(String anneeDeb) {
		this.anneeDeb = anneeDeb;
	}

	public String getAnneeFin() {
		return anneeFin;
	}

	public void setAnneeFin(String anneeFin) {
		this.anneeFin = anneeFin;
	}

	public String getMention() {
		return mention;
	}

	public void setMention(String mention) {
		this.mention = mention;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	
	
}
