package com.testHibernate.model.listeDiplomes;
 
import com.testHibernate.model.diplome.ListesDiplome;

public class TousListeDiplomeForm {

	public TousListeDiplomeForm() {
		
	}
	public TousListeDiplomeForm(Long id, ListesDiplome listesDiplome, String sessionSortie, String nomPromotion) {
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
