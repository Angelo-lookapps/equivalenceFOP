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
import com.testHibernate.model.equivalence.ArreteEqRef;
import com.testHibernate.model.equivalence.InfoArrete;
import com.testHibernate.model.historique.ActiviteRecent;
import com.testHibernate.model.listePromotion.ListePromotion;
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
	public static String _contentChamp = " <p style=\"margin-left:0cm; margin-right:0cm\">---------------------------------------------------</p>\r\n" + 
			"\r\n" + 
			"                                                        <p style=\"margin-left:0cm; margin-right:0cm\"><span style=\"font-size:9pt\"><span\r\n" + 
			"                                                                    style=\"font-family:&quot;Times New Roman&quot;,serif\"><strong><span\r\n" + 
			"                                                                            style=\"font-size:9.0pt\"><span style=\"color:#333333\">N&deg;\r\n" + 
			"                                                                                <span th:text=\"'####'\"></span> /<span\r\n" + 
			"                                                                                    th:text=\"'##'\"></span> -\r\n" + 
			"                                                                                MFPRATELS/SG/DGFOP/DFPAE</span></span></strong></span></span></p>\r\n" + 
			"\r\n" + 
			"                                                        <p style=\"margin-left:0cm; margin-right:0cm\"><span style=\"font-size:9pt\"><span\r\n" + 
			"                                                                    style=\"font-family:&quot;Times New Roman&quot;,serif\"><strong><span\r\n" + 
			"                                                                            style=\"font-size:9.0pt\"><span style=\"color:#333333\">D&eacute;livr&eacute;\r\n" + 
			"                                                                                &agrave; M/Mr&nbsp;: <span th:text=\"'...'\"></span>\r\n" + 
			"                                                                                &nbsp;&nbsp;&nbsp;&nbsp;</span></span></strong></span></span></p>\r\n" + 
			"\r\n" + 
			"                                                        <p style=\"margin-left:0cm; margin-right:0cm\"><span style=\"font-size:9pt\"><span\r\n" + 
			"                                                                    style=\"font-family:&quot;Times New Roman&quot;,serif\"><strong><span\r\n" + 
			"                                                                            style=\"font-size:9.0pt\"><span style=\"color:#333333\">Titulaire\r\n" + 
			"                                                                                de : <span th:text=\"'...'\"></span>\r\n" + 
			"                                                                            </span></span></strong></span></span></p>\r\n" + 
			"\r\n" + 
			"                                                        <p style=\"margin-left:0cm; margin-right:0cm\"><span style=\"font-size:9pt\"><span\r\n" + 
			"                                                                    style=\"font-family:&quot;Times New Roman&quot;,serif\"><strong><span\r\n" + 
			"                                                                            style=\"font-size:9.0pt\"><span style=\"color:#333333\">Sp&eacute;cialit&eacute;&nbsp;:\r\n" + 
			"                                                                                <span th:text=\"'...'\"></span></span></span></strong></span></span></p>\r\n" + 
			"														<p style=\"margin-left:0cm; margin-right:0cm\"><span style=\"font-size:12pt\">\r\n" + 
			"															<span style=\"font-family:&quot;Times New Roman&quot;,serif\">\r\n" + 
			"																<span style=\"font-size:9.0pt\">www.dgfop.gov.mg</span></span>\r\n" + 
			"																</span></p>";
	
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
			case 4:
				ret = "Mise en rejet : " + query; break; 
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
	public static Date convertToDateUtil(String date) throws ParseException { 
		 Date ret = formater.parse(date);
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
				//System.out.println("\n\n SplitActivite1 = "+tab1[0]);
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
	public static List<TempListePromotion> getTempListPromotion(List<ListePromotion> listePromotion, Integer[] effectifTotal)throws Exception{
		List<TempListePromotion> ret = new ArrayList<TempListePromotion>();
		try {
			if(listePromotion.size()!=0 && effectifTotal.length!=0){
				int i = 0;
				for(ListePromotion temp : listePromotion) {
					ret.add(new TempListePromotion(temp, effectifTotal[i]));
					i++;
				}
			}
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	
	public static InfoArrete getInitialInfoArrete(ArreteEqRef arreteEqRef) {
		InfoArrete ret = new InfoArrete();
		try {
			if(arreteEqRef==null) {
				System.out.println("arreteEqRef === NULLLL");
			}
			System.out.println("arreteEqRef === "+arreteEqRef.getTitre());
			
			ret.setArreteEqRef(arreteEqRef);  
			ret.setNumeroArrete("000");
			ret.setDateSortieArrete(new Date()); 
			ret.setDecretsArrete("Vu la Constitution,\r\n" + 
					"Vu la loi n°2003-011 du 03 septembre  2003 portant  Statut Général des  Fonctionnaires, et les textes subséquent ; \r\n" + 
					"Vu le décret n° 96-745 du 27 août 1996 portant classement hiérarchique des corps de fonctionnaires ;\r\n" + 
					"Vu le décret n° 2005-074 du 01 février 2005 fixant les missions, la composition et les règles de fonctionnement de la Commission Nationale des Equivalences Administratives des Titres ;\r\n" + 
					"Vu le décret n° 2014-367  du 10 Janvier 2012  fixant les attributions du Ministre de la Fonction Publique, du Travail et des Lois Sociales ainsi que l’organisation générale de son Ministère ; \r\n" + 
					"Vu le décret n°2014-200 du11 avril 2014 portant nomination du Premier Ministre, Chef du Gouvernement ;\r\n" + 
					"Vu le décret n°2014-235 du18 avril 2014 portant nomination des Membres du Gouvernement ;\r\n" + 
					"Vu l’arrêté n°8176 /2014 - CNEAT du 07 Février 2014 et l’arrêté n°25.047 /2014-CNEAT portant nomination des membres de la Commission Nationale des Equivalences Administratives des Titres ;\r\n" + 
					"Vu l’avis de la Commission Nationale  des Equivalences Administratives des Titres de la session du 11 septembre  2014.\r\n" + 
					"");
			ret.setTitreTableau("SANS TITRE");
			ret.setOrganismePaysTableau("VIDE");
			ret.setCadreTableau( "VIDE");
			ret.setEchelleTableau( "VIDE");
			ret.setCategorieTableau( "VIDE");
	    	ret.setDiplomeEquivalentDecret( "VIDE");
	    	ret.setCorpsFonctionnaireDecret( "VIDE");
	    	ret.setIndiceDecret( "VIDE");
	    	ret.setDateSignature(new Date());
	    	ret.setNomMinistreSignature( "VIDE");
			 
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	
	public static String getMoisBy(int mois) {
		String ret = "";
		
		switch(mois) {
			case 1:
				ret = "Janvier";break;
			case 2:
				ret = "Février";break;
			case 3:
				ret = "Mars";break;
			case 4:
				ret = "Avril";break;
			case 5:
				ret = "Mai";break;
			case 6:
				ret = "Juin";break;
			case 7:
				ret = "Juillet";break;
			case 8:
				ret = "Août";break;
			case 9:
				ret = "Septembre";break;
			case 10:
				ret = "Octobre";break;
			case 11:
				ret = "Novembre";break;
			default: 
				ret = "Décembre";break;
		}
		return ret;
	}
}
