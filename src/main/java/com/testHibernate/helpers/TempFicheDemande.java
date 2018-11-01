package com.testHibernate.helpers;

import com.testHibernate.model.demande.FicheDemande;
import com.testHibernate.model.equivalence.ArreteEqRef;

public class TempFicheDemande {
	private ArreteEqRef arreteEqRef;
	private FicheDemande ficheDemande;
	
	
	public ArreteEqRef getArreteEqRef() {
		return arreteEqRef;
	}
	public void setArreteEqRef(ArreteEqRef arreteEqRef) {
		this.arreteEqRef = arreteEqRef;
	}
	public FicheDemande getFicheDemande() {
		return ficheDemande;
	}
	public void setFicheDemande(FicheDemande ficheDemande) {
		this.ficheDemande = ficheDemande;
	}
	public TempFicheDemande(ArreteEqRef arreteEqRef, FicheDemande ficheDemande) {
		super();
		this.arreteEqRef = arreteEqRef;
		this.ficheDemande = ficheDemande;
	}
	
	
}
