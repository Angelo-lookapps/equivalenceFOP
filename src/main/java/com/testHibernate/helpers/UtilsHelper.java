package com.testHibernate.helpers;

public class UtilsHelper {
	
	public static String[] getTabBySplit(String field, String  separator) {
		String[] ret = null; 
		if(field.split(separator).length!=0) {
			ret = field.split(separator);
		}
		return ret;
	}
	public static boolean compareTwoTabs(String[] compare1, String[] compare2) {
		boolean ret = false; 
		int compteurEquals = 1;
		for(int i=0; i<compare1.length ; i++) {
			System.out.println("Compare\t "+compare1[i]+" == "+compare2[i]);
			if(compare1[i].toUpperCase().equals(compare2[i].toUpperCase())) { 
				compteurEquals++;
			}
		}
		for(int i = compare1.length-1, k = 0 ; i>=0 ; i--, k++) {
			System.out.println("Compare\t "+compare1[k]+" == "+compare2[i]);
			if(compare1[k].toUpperCase().equals(compare2[i].toUpperCase())) { 
				compteurEquals++;
			}
		} 
		if(compteurEquals >= (int)compare1.length) {
			ret = true;
		} 
		return ret;
	}
}
