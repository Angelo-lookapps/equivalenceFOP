package com.testHibernate.helpers;

import java.util.ArrayList;
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
}
