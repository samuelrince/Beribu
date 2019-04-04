package fr.ecp.IS1220.myVelib.core.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class contains a static method hashPassword(String) which is used
 * to hash User's password. The hash is performed by SHA-512 algorithm.
 * @author Samuel
 *
 */
public class PasswordHash {
	private static final String hashAlgorithm = "SHA-512";
	
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
			MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
			md.update(password.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++) {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
			return sb.toString();
	}
}
