package com.testHibernate.helpers;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.testHibernate.model.cin.CIN; 
import com.testHibernate.model.diplome.ListesDiplome; 
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.model.listePromotion.ListePromotionDetail;

public class GlobalHelper {
	 
    public static SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy"); 
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
		
	public static List<Tag> convert(List<ListesDiplome> listesDiplome) throws Exception{
		List<Tag> ret = new ArrayList<Tag>();
		if(listesDiplome.size()==0) {
			throw new Exception("Error in convertToHashMap : size of listesDiplome is 0 !");
		}
		for(ListesDiplome diplome : listesDiplome) {
			ret.add(new Tag(diplome.getId(), diplome.getEcole() + " " + diplome.getFiliere() + " - " + diplome.getNiveauDiplome().getNiveau()));
		} 
		
		return ret;
	} 
	public List<Tag> convertMultiple(List<ListesDiplome> listesDiplomeByMultiple, String idNiveau) throws Exception{
		List<Tag> ret = new ArrayList<Tag>();
		if(listesDiplomeByMultiple.size()==0) {
			throw new Exception("Error in convertToHashMap : size of listesDiplome is 0 !");
		}
		if(!idNiveau.equals("")){
			for(ListesDiplome diplome : listesDiplomeByMultiple) {
				if(idNiveau.equals(diplome.getNiveauDiplome().getId().toString())) {
					ret.add(new Tag(diplome.getId(), diplome.getEcole() + " " + diplome.getFiliere() + " - " + diplome.getNiveauDiplome().getNiveau()));
				}
			}
		} else{
			for(ListesDiplome diplome : listesDiplomeByMultiple) { 
				ret.add(new Tag(diplome.getId(), diplome.getEcole() + " " + diplome.getFiliere() + " - " + diplome.getNiveauDiplome().getNiveau()));
			}
		} 
		
		return ret;
	} 
	
	public List<Tag> convertDiplomeToListTag(List<ListesDiplome> listesDiplome) throws Exception{
		List<Tag> ret = new ArrayList<Tag>();
		if(listesDiplome.size()==0) {
			throw new Exception("Error in convertToHashMap : size of listesDiplome is 0 !");
		}
		for(ListesDiplome diplome : listesDiplome) {
			ret.add(new Tag(diplome.getId(), diplome.getEcole() + " " + diplome.getFiliere()+ " - " + diplome.getOption() + " - " + diplome.getNiveauDiplome().getNiveau()));
		} 
		
		return ret;
	} 
	
	public List<Tag> convertCINToListTag(List<CIN> cins) throws Exception{
		List<Tag> ret = new ArrayList<Tag>();
		if(cins.size()==0) {
			throw new Exception("Error in convertToHashMap : size of cins is 0 !");
		}
		for(CIN cin : cins) {
			ret.add(new Tag(cin.getId(), cin.getNom() + " " + cin.getPrenom()+ " Née le " + cin.getDateNaissance() + " à " + cin.getLieuNaissance()));
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
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		java.util.Date date = new java.util.Date();
		return  dateFormat.format(date);
	} 
	public static String convertToStringDate(java.sql.Date date) { 
		return  formater.format(date);
	} 
	public static String formatDate(String date) { 
		String[] daty = date.split("-");
		String ret = daty[2]+"-"+daty[1]+"-"+daty[0];
		return  ret;
	}
	public static java.sql.Date convertStringToDate(String date){ 
        java.sql.Date ret = null;
        java.util.Date temp = null;
        try{
            temp =  formater.parse(date);
            ret = new java.sql.Date(temp.getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret; 
    }
    

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
				System.out.println("\n\n SplitActivite1 = "+tab1[0]);
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
				
			}
		}
		else {
			throw new Exception("ERREUR dans la méthode splitActivityTime : taille List invalide!");
		}
		
		return ret;
	} 
	public static List<ListePromotionDetail> searchAtListe(List<ListePromotionDetail> listePromotionDetail, String[] search) throws Exception {
		List<ListePromotionDetail> ret = new ArrayList<ListePromotionDetail>();
		try {
			if(listePromotionDetail.size()==0) {
				throw new Exception("Error dans searchAtListe method : the size of argument List<ListePromotionDetail> listePromotionDetail is 0.");
			}
			for(String i : search) {
				for(ListePromotionDetail temp : listePromotionDetail) {
					
					String concat = temp.getNomComplet() + " - " + temp.getDateNaissance() + " - " + temp.getLieuNaissance();
		 			System.out.println(" contain \"" + i + "\" : "+concat.toUpperCase().contains(i.toUpperCase())+ "\n\t DANS "+concat);	
					if(concat.toUpperCase().contains(i.toUpperCase())) {
						ret.add(temp);
						break;
					}
				}
			}
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	public static List<ListePromotionDetail> listAnotherSuggestion(List<ListePromotionDetail> suggestions, List<ListePromotionDetail> list) throws Exception{
		List<ListePromotionDetail> ret = new ArrayList<ListePromotionDetail>();
		try {
			if( list.size()==0) {
				throw new Exception("Error dans listAnotherSuggestion method : the size of argument List<ListePromotionDetail> listePromotionDetail,list is 0.");
			} 
			else if(suggestions.size()==0) {
				return list;
			}
			for(ListePromotionDetail temp : list) {
				for(ListePromotionDetail suggs : suggestions) { 
					if(temp!=suggs) {
						ret.add(temp);
						break;
					}
				}
			}
			 
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	public static Integer[] getNombrePageMax(int sizeList, int limit) throws Exception {
		Integer[] ret = null; 
		if(limit==0) {
			throw new Exception("Error getNombrePageMax : invalid division by 0 for limit parameter");
		}
		if(sizeList!=0) {
			int length = sizeList%limit==0 ? sizeList/limit : (sizeList/limit)+1; 
			ret = new Integer[length];
			for(Integer k = 0, i = 1; i <= ret.length;i++,k++){
				System.out.println("iteration = "+i);
				ret[k] = i;
			}
		} 
		
		return ret;
	}
}
