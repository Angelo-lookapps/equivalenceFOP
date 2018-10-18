package com.testHibernate.helpers;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateHelper {
	public static List<Integer> getAnneeList(int debut, int fin) throws Exception{
		List<Integer> ret = new ArrayList<Integer>();
		int i = fin;
		try {
			if(fin > debut) {
				while(i>debut) {
					ret.add(i);
					i--;
				}
				return ret;
			}
			else {
				throw new Exception("Erreur: annee fin invalide!");
			}
		}catch(Exception e) {
			throw e;
		}
		
	}
	public static List<Integer> getAllDaysInYear(){
		List<Integer> ret = new ArrayList<Integer>();
		int mois = 12;
		try {
			for(int i=1 ; i<=mois ; i++) {
				Integer daysOfMonth =  DateHelper.getNombreJoursParMois(mois);
				ret.add(daysOfMonth);
			}
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	public static int getNombreJoursParMois(int mois){
		int ret = 0;
		try {
			
			/*Mettre ici les instructions */
			
			int year = (int)(new Date().getYear()+1900); 
			YearMonth yearMonthObject = YearMonth.of(year, mois);
			
		    ret = yearMonthObject.lengthOfMonth(); 
		}catch(Exception e) {
			throw e;
		}
		return ret;
	}
	
}
