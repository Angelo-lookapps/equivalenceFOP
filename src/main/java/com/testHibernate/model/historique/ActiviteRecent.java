package com.testHibernate.model.historique;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity(name="ActiviteRecent")
@NamedQueries({
	@NamedQuery(
		name = "ActiviteRecent.find5RecentActivite", 
		query = "SELECT c FROM ActiviteRecent as c order by c.dateAjout"),
	@NamedQuery(
		name = "ActiviteRecent.deleteTheLatest", 
		query = "DELETE FROM ActiviteRecent as c WHERE c.dateAjout < ':dateCompare'")
	})
public class ActiviteRecent {

	public ActiviteRecent() {
		
	}

	public ActiviteRecent(Long id, String definition, String dateAjout) {
		super();
		this.id = id;
		this.definition = definition;
		this.dateAjout = dateAjout;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;	
	
	private String definition;
	private String dateAjout;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(String dateAjout) {
		this.dateAjout = dateAjout;
	}
	
		
}
