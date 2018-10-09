package com.testHibernate.helpers;

import java.util.Base64;  
/**
 *
 * @author Angelo-KabyLake
 */ 
public class Encrypt {
    public static String encode(String message) throws Exception{ 
	String originalInput = message,encodedString = "";
        
	try {   
            encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes()); 
	    } catch (Exception e) {
		e.printStackTrace();
		throw new Exception(e);
            }
	return encodedString;
    }
    public static String decode(String encodedMessage) throws Exception{
	byte[] decodedBytes = Base64.getDecoder().decode(encodedMessage);
        String decodedString = "";
        try {
            decodedString = new String(decodedBytes);
        } catch (Exception e) {
		e.printStackTrace();
		throw new Exception(e);
        }
        return decodedString;
    }
}
