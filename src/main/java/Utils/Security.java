package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {
	
	public static String getMD5(String phrase) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			return (messageDigest.digest(phrase.getBytes())).toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	} 
}
