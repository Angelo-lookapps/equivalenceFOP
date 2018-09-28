package com.testHibernate.helpers;

import com.testHibernate.model.historique.ActiviteRecent;

public class TempActivite {
	
	private ActiviteRecent activiteRecent;
	private String dateAjout1;
	private String dateAjout2;
	
	private String definition1;
	private String definition2;
	
	public TempActivite() {}

	
	
	public TempActivite(ActiviteRecent activiteRecent, String dateAjout1, String dateAjout2, String definition1,
			String definition2) {
		super();
		this.activiteRecent = activiteRecent;
		this.dateAjout1 = dateAjout1;
		this.dateAjout2 = dateAjout2;
		this.definition1 = definition1;
		this.definition2 = definition2;
	}



	public ActiviteRecent getActiviteRecent() {
		return activiteRecent;
	}

	public void setActiviteRecent(ActiviteRecent activiteRecent) {
		this.activiteRecent = activiteRecent;
	}

	public String getDateAjout1() {
		return dateAjout1;
	}

	public void setDateAjout1(String dateAjout1) {
		this.dateAjout1 = dateAjout1;
	}

	public String getDateAjout2() {
		return dateAjout2;
	}

	public void setDateAjout2(String dateAjout2) {
		this.dateAjout2 = dateAjout2;
	}

	public String getDefinition1() {
		return definition1;
	}

	public void setDefinition1(String definition1) {
		this.definition1 = definition1;
	}

	public String getDefinition2() {
		return definition2;
	}

	public void setDefinition2(String definition2) {
		this.definition2 = definition2;
	}
	 
	 
	
}
