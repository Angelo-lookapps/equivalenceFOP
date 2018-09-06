package com.testHibernate.model.equivalence;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ArreteEquivalence {
	
	
	public ArreteEquivalence(Long id, Long idListesDiplome, Long idTableauArrete, Blob logoGauche, Blob logoCentre,
			String textBox1, String textBox2, String titre1, String acte, String titre2, String[] articles,
			String textBox3, String nomMinistre, String textBox4, Long numero, String delivreeA, String titulaireDe,
			String specialite) {
		super();
		this.setId(id);
		this.setIdListesDiplome(idListesDiplome);
		this.setIdTableauArrete(idTableauArrete);
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
		this.setNumero(numero);
		this.setDelivreeA(delivreeA);
		this.setTitulaireDe(titulaireDe);
		this.setSpecialite(specialite);
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long idListesDiplome;
	
	private Long idTableauArrete;
		
	@Lob
	private Blob logoGauche ;
	
	@Lob
	private Blob logoCentre ;
	
	private String textBox1 ;
	private String textBox2 ;
	private String titre1 ;
	private String acte ;
	private String titre2 ;
	private String[] articles ;
	private String textBox3 ;
	private String nomMinistre ;
	private String textBox4 ;
	private Long numero ;
	private String delivreeA ;
	private String titulaireDe ;
	private String specialite ;
	
	
	public Long getIdTableauArrete() {
		return idTableauArrete;
	}
	public void setIdTableauArrete(Long idTableauArrete) {
		this.idTableauArrete = idTableauArrete;
	}
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
	public Blob getLogoGauche() {
		return logoGauche;
	}
	public void setLogoGauche(Blob logoGauche) {
		this.logoGauche = logoGauche;
	}
	public Blob getLogoCentre() {
		return logoCentre;
	}
	public void setLogoCentre(Blob logoCentre) {
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
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public String getDelivreeA() {
		return delivreeA;
	}
	public void setDelivreeA(String delivreeA) {
		this.delivreeA = delivreeA;
	}
	public String getTitulaireDe() {
		return titulaireDe;
	}
	public void setTitulaireDe(String titulaireDe) {
		this.titulaireDe = titulaireDe;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	
	
}
