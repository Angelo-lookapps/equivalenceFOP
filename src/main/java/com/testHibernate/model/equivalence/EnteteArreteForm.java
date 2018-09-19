package com.testHibernate.model.equivalence;


public class EnteteArreteForm {
	
	
	public EnteteArreteForm() {
		super();
	}

	public EnteteArreteForm(Long id, Long arreteEqRef, byte[] logo, String titreGauche, String titreDroite) {
		super();
		this.id = id;
		this.arreteEqRef = arreteEqRef;
		this.logo = logo;
		this.titreGauche = titreGauche;
		this.titreDroite = titreDroite;
	}

	
	private Long id;
	
	private Long arreteEqRef;
		
	private byte[] logo ;
		
	private String titreGauche ;
	private String titreDroite ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getArreteEqRef() {
		return this.arreteEqRef;
	}
	public void setArreteEqRef(Long arreteEqRef) {
		this.arreteEqRef = arreteEqRef;
	}
	public byte[] getLogo() {
		return logo;
	}
	public void setLogo(byte[] logo) {
		this.logo = logo;
	}
	public String getTitreGauche() {
		return titreGauche;
	}
	public void setTitreGauche(String titreGauche) {
		this.titreGauche = titreGauche;
	}
	public String getTitreDroite() {
		return titreDroite;
	}
	public void setTitreDroite(String titreDroite) {
		this.titreDroite = titreDroite;
	}
	
	
		
}
