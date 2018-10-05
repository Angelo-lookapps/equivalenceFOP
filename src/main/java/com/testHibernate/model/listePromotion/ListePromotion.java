package com.testHibernate.model.listePromotion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.testHibernate.model.diplome.ListesDiplome;

@Entity(name="ListePromotion")
@NamedQueries({
	@NamedQuery(
		name = "ListePromotion.findBySession", 
		query = "SELECT c FROM ListePromotion as c WHERE c.sessionSortie = :session "),
	@NamedQuery(
		name = "ListePromotion.findPromotionByIdListeDiplome", 
		query = "SELECT c FROM ListePromotion as c WHERE c.listesDiplome.id = :idListe ")
	})
public class ListePromotion {

	public ListePromotion() {
		
	}
	
	public ListePromotion(Long id, ListesDiplome listesDiplome, String sessionSortie, String nomPromotion) {
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
	
	private String dateAjout;
	
	
	public String getDateAjout() {
		return this.dateAjout;
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
