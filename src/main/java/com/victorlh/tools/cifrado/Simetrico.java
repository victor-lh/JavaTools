package com.victorlh.tools.cifrado;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by victor on 16/06/2017.
 */

public class Simetrico {

	private final static String AES = "AES";
	private final static String MODO = "AES/CBC/PKCS5Padding";


	public static byte[] encriptar(String key, String iv, String cleartext) throws Exception {
		Cipher cipher = Cipher.getInstance(MODO);
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
		return cipher.doFinal(cleartext.getBytes(StandardCharsets.UTF_8));
	}

	public static String desencriptar(String key, String iv, byte[] encrypted) throws Exception {
		Cipher cipher = Cipher.getInstance(MODO);
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), AES);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
		byte[] decrypted = cipher.doFinal(encrypted);
		return new String(decrypted, StandardCharsets.UTF_8);
	}
}

