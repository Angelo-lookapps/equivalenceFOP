package com.testHibernate.model.diplome;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
	@NamedQuery(
		name = "NiveauDiplome.findAllNiveau", 
		query = "SELECT nv FROM NiveauDiplome nv"),
	@NamedQuery(
		name = "NiveauDiplome.findByCategorie", 
		query = "SELECT nv FROM NiveauDiplome nv WHERE nv.categorie = :categorie ")
})
public class NiveauDiplome {
	
	public NiveauDiplome() {}

	public NiveauDiplome(Long id, String niveau, String categorie) {
		super();
		this.id = id;
		this.niveau = niveau;
		this.categorie = categorie;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String niveau;
	private String categorie;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
		
}
