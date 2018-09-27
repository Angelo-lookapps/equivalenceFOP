package com.testHibernate.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GlobalHelper {
	 
	
	public static List<String> getMentionList(){
		List<String> ret = new ArrayList<String>();
		ret.add("Passable");
		ret.add("Assez bien");
		ret.add("Bien");
		ret.add("Très bien");
		
		return ret;
	} 
	
	public static String getQueryStringActivities(int typeActivity, String query) {
		String ret = "Non definit!"; 
		switch(typeActivity) {
			case 1:
				ret = "Vous avez récemment ajouter : " + query; break;
			case 2:
				ret = "Vous avez supprimé : " + query; break; 
			default:
				ret = "Vous avez mis à jour : " + query; break; 
		}
		return ret;
	}
	
	public static HashMap<String, String> getChampDemande(){
		HashMap<String, String> ret = new HashMap<String, String>();
		ret.put("fi.id", "N° d'Enregistrement");
		ret.put("fi.dateAjout", "Date d'ajout");
		ret.put("fi.cin", "CIN");
		ret.put("fi.listesDiplome", "Diplome");
		ret.put("fi.statusEnregistrement", "Status");
		
		return ret;
	} 
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return  dateFormat.format(date);
	} 
}
