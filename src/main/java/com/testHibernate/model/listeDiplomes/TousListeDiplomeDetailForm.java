package com.testHibernate.model.listeDiplomes;

 
public class TousListeDiplomeDetailForm {

	public TousListeDiplomeDetailForm() {
		
	}
	

 	public TousListeDiplomeDetailForm(Long id, TousListeDiplome tousListeDiplome, String numeroMatricule,
			String nomComplet, String dateNaissance, String lieuNaissance, String mention) {
		super();
		this.id = id;
		this.tousListeDiplome = tousListeDiplome;
		this.numeroMatricule = numeroMatricule;
		this.nomComplet = nomComplet;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.mention = mention;
	}


	private Long id;
 	private TousListeDiplome tousListeDiplome;
 	private String numeroMatricule;
	private String nomComplet;
	private String dateNaissance;
	private String lieuNaissance;
	private String mention;
	
	
	public String getMention() {
		return mention;
	}
	public void setMention(String mention) {
		this.mention = mention;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TousListeDiplome getTousListeDiplome() {
		return tousListeDiplome;
	}
	public void setTousListeDiplome(TousListeDiplome tousListeDiplome) {
		this.tousListeDiplome = tousListeDiplome;
	}
	public String getNumeroMatricule() {
		return numeroMatricule;
	}
	public void setNumeroMatricule(String numeroMatricule) {
		this.numeroMatricule = numeroMatricule;
	}
	public String getNomComplet() {
		return nomComplet;
	}
	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}
	public String getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}
	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	
	
	
	
}
