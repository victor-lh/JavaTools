package com.victorlh.tools.cifrado;

import com.victorlh.tools.ToolsValidacion;
import com.victorlh.tools.Transform;

import java.security.SecureRandom;

/**
 * @author Victorlh
 * 15 abr. 2018
 * 
 */
public class Password {

	private String hash;
	private String salt;
	
	public Password(String hash, String salt) {
		this.hash = hash;
		this.salt = salt;
	}
	
	public Password(String hash) {
		this(hash, "");
	}
	
	public String getHash() {
		return hash;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public boolean checkEqualPass(String pass) {
		if(ToolsValidacion.isCadenaNoVacia(salt)) {
			pass = pass + salt;
		}
		
		String digest = Digest.digestHex(Digest.AlgoritmosDigest.SHA256, pass);
		
		return hash.equals(digest);
	}
	
	private static String generateSalt() {
		byte[] bytes = new byte[32];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return Transform.toHexadecimal(bytes);
	}
	
	public static Password getPassword(String pass, boolean useSalt) {
		String salt = "";
		if(useSalt) {
			salt = generateSalt();
			pass = pass + salt;
		}
		
		String hash = Digest.digestHex(Digest.AlgoritmosDigest.SHA256, pass);
		
		return new Password(hash, salt);
	}
}
