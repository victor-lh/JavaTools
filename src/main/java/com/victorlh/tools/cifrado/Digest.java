package com.victorlh.tools.cifrado;

import com.victorlh.tools.Transform;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * Cifra contenido mediante algoritmos digest
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 12 jun. 2017
 *
 */
public final class Digest {

	public enum AlgoritmosDigest {
		MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256"), SHA512("SHA-512");

		private String algoritmo;

		AlgoritmosDigest(String algoritmo) {
			this.algoritmo = algoritmo;
		}
	}

	/**
	 * 
	 * Cifra un array de {@link Byte} mediante el algoritmo indicado
	 * 
	 * @param algoritmo - {@link AlgoritmosDigest} con el que se cifrara
	 * @param entrada   - Contenido que cifrar
	 * @return Contenido cifrado
	 */
	public static byte[] digest(AlgoritmosDigest algoritmo, byte[] entrada) {
		if (algoritmo == null) {
			throw new RuntimeException("No existe algoritmo");
		}
		try {
			MessageDigest md = MessageDigest.getInstance(algoritmo.algoritmo);
			md.update(entrada);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * Cifra un {@link String} mediante el algoritmo indicado
	 * 
	 * @param algoritmo - {@link AlgoritmosDigest} con el que se cifrara
	 * @param entrada   - Contenido que cifrar
	 * @return Contenido cifrado
	 */
	public static byte[] digest(AlgoritmosDigest algoritmo, String entrada) {
		byte[] bytes = entrada.getBytes();
		return digest(algoritmo, bytes);
	}

	/**
	 * 
	 * Cifra un {@link String} mediante el algoritmo indicado y lo devuelve
	 * condificado en una cadena hexadecimal
	 * 
	 * @param algoritmo - {@link AlgoritmosDigest} con el que se cifrara
	 * @param entrada   - Contenido que cifrar
	 * @return Contenido cifrado y codificado en hexadecimal
	 */
	public static String digestHex(AlgoritmosDigest algoritmo, String entrada) {
		byte[] digest = digest(algoritmo, entrada);
		return Transform.toHexadecimal(digest);
	}

	/**
	 * 
	 * Cifra un {@link String} mediante el algoritmo {@link AlgoritmosDigest#SHA1} y
	 * lo devuelve condificado en una cadena hexadecimal
	 * 
	 * @param entrada - Contenido que cifrar
	 * @return Contenido cifrado en SHA-1 y codificado en hexadecimal
	 */
	public static String getSHA1(String entrada) {
		return digestHex(AlgoritmosDigest.SHA1, entrada);
	}
}
