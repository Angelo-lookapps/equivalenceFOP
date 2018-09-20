package com.testHibernate.helpers;

import java.util.ArrayList;
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
}
