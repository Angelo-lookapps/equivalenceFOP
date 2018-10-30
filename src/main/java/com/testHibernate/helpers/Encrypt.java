package com.testHibernate.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;  
/**
 *
 * @author Angelo-KabyLake
 */ 
public class Encrypt {
	private static MessageDigest md;
	
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
    
    public static String toSHA1(byte[] convertme) throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("SHA-1");
        return Base64.getEncoder().encodeToString((md.digest(convertme)));
    }
}
