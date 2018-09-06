package com.testHibernate.model.diplome;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class DiplomeDetail {
	
	public DiplomeDetail () {
		
	}
	

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long idListesDiplome;
	private String anneeDeb;
	private String anneeFin;
	private String mention;

	@Lob
	private Blob image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdListesDiplome() {
		return idListesDiplome;
	}

	public void setIdListesDiplome(Long idListesDiplome) {
		this.idListesDiplome = idListesDiplome;
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
