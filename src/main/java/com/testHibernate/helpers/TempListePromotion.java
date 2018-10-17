package com.testHibernate.helpers;

import com.testHibernate.model.listePromotion.ListePromotion;

public class TempListePromotion {
	private ListePromotion listePromotion;
	private Integer effectiftotal;
	
	public TempListePromotion(ListePromotion listePromotion, Integer effectiftotal) {
		super();
		this.listePromotion = listePromotion;
		this.effectiftotal = effectiftotal;
	}
	public ListePromotion getListePromotion() {
		return listePromotion;
	}
	public void setListePromotion(ListePromotion listePromotion) {
		this.listePromotion = listePromotion;
	}
	public Integer getEffectiftotal() {
		return this.effectiftotal;
	}
	public void setEffectiftotal(Integer effectiftotal) {
		this.effectiftotal = effectiftotal;
	}
	
	
}
