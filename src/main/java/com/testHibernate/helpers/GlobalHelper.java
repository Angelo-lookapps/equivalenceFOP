package com.testHibernate.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.testHibernate.model.historique.ActiviteRecent;

public class GlobalHelper {
	 
	public static String _ArticleContent = "<hr />\r\n" +
			"<p style=\"margin-left:40px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;MINISTERE DE LA FONCTION PUBLIQUE,</span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:40px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; DE LA REFORME DE L&rsquo;ADMINISTRATION,&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:40px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&nbsp;&nbsp; DU TRAVAIL, DE L&rsquo;EMPLOI ET DES LOIS SOCIALES&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:600px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&laquo;&nbsp;POUR COPIE CONFORME&nbsp;&raquo;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:80px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&nbsp; &nbsp;-----------------------------&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Antananarivo, le&nbsp;</span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:80px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">SECRETARIAT GENERAL &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:80px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&nbsp; &nbsp; -----------------------------&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:80px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\">&nbsp;DIRECTION GENERALE</span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:80px; margin-right:0cm; text-align:justify\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\">DE LA FONCTION PUBLIQUE</span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:80px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&nbsp; &nbsp;-----------------------------&nbsp; </span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:80px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&nbsp;&nbsp;DIRECTION DE LA FORMATION &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;</span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:80px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&nbsp; ET DU PERFECTIONNEMENT </span></span></span></p>\r\n" + 
			"\r\n" + 
			"<p style=\"margin-left:80px; margin-right:0cm\"><span style=\"font-size:8px\"><span style=\"font-family:&quot;Times New Roman&quot;,serif\"><span style=\"color:#333333\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;DES AGENTS DE L&rsquo;ETAT</span></span></span></p>\r\n" + 
			"<hr />\r\n" + 
			"<p>&nbsp;</p>\r\n" + 
			"";
	
	
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
	@SuppressWarnings("null")
	public List<TempActivite> splitActivityTime(List<ActiviteRecent> list) throws Exception {
		List<TempActivite> ret = new ArrayList<TempActivite>();
		String[] tab1 = null, tab2 = null;
		if(list.size()!=0) {
			String[] subs = {"", ""};
			
			for(ActiviteRecent act : list) { 
			TempActivite temp = new TempActivite();
				
				if(act.getDateAjout().split(" ").length!=0) { 
					tab1 =  act.getDateAjout().split(" "); 
				}
				else {
					subs[1] = act.getDateAjout();
					tab1 = subs;
				}
				temp.setDateAjout1(tab1[0]);
				temp.setDateAjout2(tab1[1]);
				
				if(act.getDefinition().split(" ").length != 0) {
					tab2 = act.getDefinition().split(":"); 
				}
				else {
					subs[1] = act.getDefinition();
					tab2 = subs;
				}
				temp.setDefinition1(tab2[0]);
				temp.setDefinition2(tab2[1]);
						 
				temp.setActiviteRecent(act); 
				ret.add(temp);
				
			System.out.println("\n\n act = "+temp.getActiviteRecent().getDateAjout());
			System.out.println("\n\n definitions = " + temp.getActiviteRecent().getDefinition());
			}
		}
		else {
			throw new Exception("ERREUR dans la méthode splitActivityTime : taille List invalide!");
		}
		
		return ret;
	} 
	 
}
