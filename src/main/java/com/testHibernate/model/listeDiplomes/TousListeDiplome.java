package com.testHibernate.model.listeDiplomes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.testHibernate.model.diplome.ListesDiplome;

@Entity(name="TousListeDiplome")
@NamedQueries({
	@NamedQuery(
		name = "TousListeDiplome.findBySession", 
		query = "SELECT c FROM TousListeDiplome as c WHERE c.sessionSortie = :session ")
	})
public class TousListeDiplome {

	public TousListeDiplome() {
		
	}
	
	public TousListeDiplome(Long id, ListesDiplome listesDiplome, String sessionSortie, String nomPromotion) {
		super();
		this.id = id;
		this.listesDiplome = listesDiplome;
		this.sessionSortie = sessionSortie;
		this.nomPromotion = nomPromotion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private ListesDiplome listesDiplome;
	
	private String sessionSortie;
	private String nomPromotion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ListesDiplome getListesDiplome() {
		return listesDiplome;
	}
	public void setListesDiplome(ListesDiplome listesDiplome) {
		this.listesDiplome = listesDiplome;
	}
	public String getSessionSortie() {
		return sessionSortie;
	}
	public void setSessionSortie(String sessionSortie) {
		this.sessionSortie = sessionSortie;
	}
	public String getNomPromotion() {
		return nomPromotion;
	}
	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}
	
	
}
