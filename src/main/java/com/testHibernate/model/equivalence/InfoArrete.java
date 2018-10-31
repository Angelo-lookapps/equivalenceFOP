package com.testHibernate.model.equivalence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery; 

@Entity 
@NamedQueries({
	@NamedQuery(  
		name = "InfoArrete.findInfoArreteByIdArrete", 
		query = "SELECT c FROM InfoArrete as c WHERE c.arreteEqRef.id = ?1 ")
})
public class InfoArrete {
	
	public InfoArrete() {} 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@ManyToOne 
	private ArreteEqRef arreteEqRef;
 
	private String numeroArrete;
	
	private Date dateSortieArrete;
	
	@Column(length= 1000000)
	private String decretsArrete;
	
	@Column(length= 10000)
	private String titreTableau;
	
	private String organismePaysTableau;
	
	private String cadreTableau;
	
	private String echelleTableau;
	
	private String categorieTableau;
	
	//DECRET Critère
	private String niveauRefDecret;
	private String diplomeEquivalentDecret;
	private String corpsFonctionnaireDecret;
	private String indiceDecret;
	
	private Date dateSignature;
	
	private String nomMinistreSignature;

	public String getNiveauRefDecret() {
		return niveauRefDecret;
	}

	public void setNiveauRefDecret(String niveauRefDecret) {
		this.niveauRefDecret = niveauRefDecret;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArreteEqRef getArreteEqRef() {
		return arreteEqRef;
	}

	public void setArreteEqRef(ArreteEqRef arreteEqRef) {
		this.arreteEqRef = arreteEqRef;
	}

	public String getNumeroArrete() {
		return numeroArrete;
	}

	public void setNumeroArrete(String numeroArrete) {
		this.numeroArrete = numeroArrete;
	}

	public Date getDateSortieArrete() {
		return dateSortieArrete;
	}

	public void setDateSortieArrete(Date dateSortieArrete) {
		this.dateSortieArrete = dateSortieArrete;
	}

	public String getDecretsArrete() {
		return decretsArrete;
	}

	public void setDecretsArrete(String decretsArrete) {
		this.decretsArrete = decretsArrete;
	}

	public String getTitreTableau() {
		return titreTableau;
	}

	public void setTitreTableau(String titreTableau) {
		this.titreTableau = titreTableau;
	}

	public String getOrganismePaysTableau() {
		return organismePaysTableau;
	}

	public void setOrganismePaysTableau(String organismePaysTableau) {
		this.organismePaysTableau = organismePaysTableau;
	}

	public String getCadreTableau() {
		return cadreTableau;
	}

	public void setCadreTableau(String cadreTableau) {
		this.cadreTableau = cadreTableau;
	}

	public String getEchelleTableau() {
		return echelleTableau;
	}

	public void setEchelleTableau(String echelleTableau) {
		this.echelleTableau = echelleTableau;
	}

	public String getCategorieTableau() {
		return categorieTableau;
	}

	public void setCategorieTableau(String categorieTableau) {
		this.categorieTableau = categorieTableau;
	}

	public Date getDateSignature() {
		return dateSignature;
	}

	public void setDateSignature(Date dateSignature) {
		this.dateSignature = dateSignature;
	}

	public String getNomMinistreSignature() {
		return nomMinistreSignature;
	}

	public void setNomMinistreSignature(String nomMinistreSignature) {
		this.nomMinistreSignature = nomMinistreSignature;
	}

	public String getDiplomeEquivalentDecret() {
		return diplomeEquivalentDecret;
	}

	public void setDiplomeEquivalentDecret(String diplomeEquivalentDecret) {
		this.diplomeEquivalentDecret = diplomeEquivalentDecret;
	}

	public String getCorpsFonctionnaireDecret() {
		return corpsFonctionnaireDecret;
	}

	public void setCorpsFonctionnaireDecret(String copsFonctionnaireDecret) {
		this.corpsFonctionnaireDecret = copsFonctionnaireDecret;
	}

	public String getIndiceDecret() {
		return indiceDecret;
	}

	public void setIndiceDecret(String indiceDecret) {
		this.indiceDecret = indiceDecret;
	}
 
	
}
