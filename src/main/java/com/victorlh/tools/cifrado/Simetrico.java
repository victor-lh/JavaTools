package com.victorlh.tools.cifrado;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by victor on 16/06/2017.
 */

/**
 * Cifra y descifra informaci�n mediante el algoritmo de cifrado simetrico AES
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 16 jun. 2017
 *
 */
public class Simetrico {

	private final static String AES = "AES";
	private final static String MODO = "AES/CBC/PKCS5Padding";

	/**
	 * Encripta la informaci�n
	 * 
	 * @param key       - Clave con la que cifrar
	 * @param iv        - vector de inicializaci�n
	 * @param cleartext - Texto que cifrar
	 * @return resultado del cifrado del texto
	 * @throws Exception - En caso de error al cifrar
	 */
	public static byte[] encriptar(String key, String iv, String cleartext) throws Exception {
		return encriptar(key, iv, cleartext.getBytes());
	}

	/**
	 * Encripta la informaci�n
	 * 
	 * @param key  - Clave con la que cifrar
	 * @param iv   - vector de inicializaci�n
	 * @param data - informaci�n que cifrar
	 * @return resultado del cifrado de la informaci�n
	 * @throws Exception - En caso de error al cifrar
	 */
	public static byte[] encriptar(String key, String iv, byte[] data) throws Exception {
		Cipher cipher = Cipher.getInstance(MODO);
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), AES);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
		return cipher.doFinal(data);
	}

	/**
	 * Desencripta la informaci�n
	 * 
	 * @param key       - Clave con la que se cifro el contenido
	 * @param iv        - vector de inicializaci�n
	 * @param encrypted - informaci�n encriptada
	 * @return Informaci�n desencriptada
	 * @throws Exception - En caso de error al descifrar
	 */
	public static byte[] desencriptar(String key, String iv, byte[] encrypted) throws Exception {
		Cipher cipher = Cipher.getInstance(MODO);
		SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), AES);
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
		return cipher.doFinal(encrypted);
	}
}
