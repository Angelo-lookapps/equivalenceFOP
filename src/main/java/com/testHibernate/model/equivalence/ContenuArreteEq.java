package com.testHibernate.model.equivalence;
 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ContenuArreteEq {
	
	
	public ContenuArreteEq() {
		super();
	}
	public ContenuArreteEq(Long id, Long idArreteEqRef, Byte[] logoGauche, Byte[] logoCentre,
			String textBox1, String textBox2, String titre1, String acte, String titre2, String[] articles,
			String textBox3, String nomMinistre, String textBox4) {
		super();
		this.setId(id);
		this.setIdArreteEqRef(idArreteEqRef);
		this.setLogoGauche(logoGauche);
		this.setLogoCentre(logoCentre);
		this.setTextBox1(textBox1);
		this.setTextBox2(textBox2);
		this.setTitre1(titre1);
		this.setActe(acte);
		this.setTitre2(titre2);
		this.setArticles(articles);
		this.setTextBox3(textBox3);
		this.setNomMinistre(nomMinistre);
		this.setTextBox4(textBox4);
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idArreteEqRef;
		
	@Lob
	private Byte[] logoGauche ;
	
	@Lob
	private Byte[] logoCentre ;
	
	private String textBox1 ;
	private String textBox2 ;
	private String titre1 ;
	private String acte ;
	private String titre2 ;
	private String[] articles ;
	private String textBox3 ;
	private String nomMinistre ;
	private String textBox4 ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdArreteEqRef() {
		return idArreteEqRef;
	}
	public void setIdArreteEqRef(Long idArreteEqRef) {
		this.idArreteEqRef = idArreteEqRef;
	}
	public Byte[] getLogoGauche() {
		return logoGauche;
	}
	public void setLogoGauche(Byte[] logoGauche) {
		this.logoGauche = logoGauche;
	}
	public Byte[] getLogoCentre() {
		return logoCentre;
	}
	public void setLogoCentre(Byte[] logoCentre) {
		this.logoCentre = logoCentre;
	}
	public String getTextBox1() {
		return textBox1;
	}
	public void setTextBox1(String textBox1) {
		this.textBox1 = textBox1;
	}
	public String getTextBox2() {
		return textBox2;
	}
	public void setTextBox2(String textBox2) {
		this.textBox2 = textBox2;
	}
	public String getTitre1() {
		return titre1;
	}
	public void setTitre1(String titre1) {
		this.titre1 = titre1;
	}
	public String getActe() {
		return acte;
	}
	public void setActe(String acte) {
		this.acte = acte;
	}
	public String getTitre2() {
		return titre2;
	}
	public void setTitre2(String titre2) {
		this.titre2 = titre2;
	}
	public String[] getArticles() {
		return articles;
	}
	public void setArticles(String[] articles) {
		this.articles = articles;
	}
	public String getTextBox3() {
		return textBox3;
	}
	public void setTextBox3(String textBox3) {
		this.textBox3 = textBox3;
	}
	public String getNomMinistre() {
		return nomMinistre;
	}
	public void setNomMinistre(String nomMinistre) {
		this.nomMinistre = nomMinistre;
	}
	public String getTextBox4() {
		return textBox4;
	}
	public void setTextBox4(String textBox4) {
		this.textBox4 = textBox4;
	}

	
	
		
}
