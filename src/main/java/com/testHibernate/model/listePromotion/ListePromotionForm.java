package com.testHibernate.model.listePromotion;
 
import java.sql.Date;

import javax.persistence.TemporalType;

import com.testHibernate.model.diplome.ListesDiplome;

public class ListePromotionForm {

	public ListePromotionForm() {
		
	}
	public ListePromotionForm(Long id, ListesDiplome listesDiplome, String sessionSortie, String nomPromotion) {
		super();
		this.id = id;
		this.listesDiplome = listesDiplome;
		this.sessionSortie = sessionSortie;
		this.nomPromotion = nomPromotion;
	}
 
	private Long id;
	private ListesDiplome listesDiplome;
	private String sessionSortie;
	private String nomPromotion;
	private Date dateAjout;

	public void setDateAjout(Date dateAjout) {
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
