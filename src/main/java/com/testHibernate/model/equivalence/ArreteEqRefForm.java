package com.testHibernate.model.equivalence;

import com.testHibernate.model.diplome.ListesDiplome;

public class ArreteEqRefForm {

	public ArreteEqRefForm() {
		
	}

	public ArreteEqRefForm(Long id, ListesDiplome listesDiplome, String anneeSortie, String titre) {
		super();
		this.id = id;
		this.listesDiplome = listesDiplome;
		this.anneeSortie = anneeSortie;
		this.titre = titre;
	}

	private Long id;
	
	private ListesDiplome listesDiplome;
	
	private TypeArreteJasper typeArreteJasper;
	
	public TypeArreteJasper getTypeArreteJasper() {
		return typeArreteJasper;
	}

	public void setTypeArreteJasper(TypeArreteJasper typeArreteJasper) {
		this.typeArreteJasper = typeArreteJasper;
	}

	private String anneeSortie;
	private String titre;
	private String dateAjout;
	private Boolean status;
	
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean statut) {
		this.status = statut;
	}

	public String getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(String dateAjout) {
		this.dateAjout = dateAjout;
	}
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
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

	public String getAnneeSortie() {
		return anneeSortie;
	}

	public void setAnneeSortie(String anneeSortie) {
		this.anneeSortie = anneeSortie;
	}
	
	
}
