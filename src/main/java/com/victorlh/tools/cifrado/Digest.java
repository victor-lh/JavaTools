package com.victorlh.tools.cifrado;

import com.victorlh.tools.Transform;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by victor on 12/06/2017.
 */

public final class Digest {

	public enum AlgoritmosDigest {
		MD5("MD5"), SHA1("SHA-1"), SHA256("SHA-256"), SHA512("SHA-512");

		private String algoritmo;
		AlgoritmosDigest(String algoritmo) {
			this.algoritmo = algoritmo;
		}
	}

	public static byte[] digest(AlgoritmosDigest algoritmo, byte[] entrada) {
		if(algoritmo == null) {
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

	public static byte[] digest(AlgoritmosDigest algoritmo, String entrada) {
		byte[] bytes = entrada.getBytes();
		return digest(algoritmo, bytes);
	}

	public static String digestHex(AlgoritmosDigest algoritmo, String entrada) {
		byte[] digest = digest(algoritmo, entrada);
		return Transform.toHexadecimal(digest);
	}

	/**
	 * @param entrada
	 * @return Entrada cifraza con SHA-1 formateada en hexa
	 */
	public static String getSHA1(String entrada) {
		return digestHex(AlgoritmosDigest.SHA1, entrada);
	}
}
