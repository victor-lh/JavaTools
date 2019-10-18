package com.victorlh.tools.cifrado;

import com.victorlh.tools.ToolsValidacion;
import com.victorlh.tools.Transform;
import com.victorlh.tools.cifrado.Digest.AlgoritmosDigest;

import java.security.SecureRandom;

/**
 * Representacion de una contrase�a cifrada con {@link AlgoritmosDigest#SHA256}
 * y con la posibilidad de a�adir salt
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 15 abr. 2018
 *
 */
public class Password {

	private String hash;
	private String salt;

	/**
	 * Inicializa la contrase�a con salt
	 * 
	 * @param hash - Contrase�a cifrada con {@link AlgoritmosDigest#SHA256}
	 * @param salt - Salt usado en la contrase�a
	 */
	public Password(String hash, String salt) {
		this.hash = hash;
		this.salt = salt;
	}

	/**
	 * Inicializa la contrase�a
	 * 
	 * @param hash - Contrase�a cifrada con {@link AlgoritmosDigest#SHA256}
	 */
	public Password(String hash) {
		this(hash, "");
	}

	/**
	 * 
	 * @return Contrase�a cifrada con {@link AlgoritmosDigest#SHA256}
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * 
	 * @return Salt usado en la contrase�a (Cadena vacia si no tiene salt)
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * Comprueba si la contrase�a plana pasada se corresponde con la contrase�a
	 * cifrada de la instancia
	 * 
	 * @param pass - {@link String} de la contrase�a en plano
	 * @return <code><b>true</b></code> si es igual y <code><b>false</b></code> en
	 *         caso contrario
	 */
	public boolean checkEqualPass(String pass) {
		if (ToolsValidacion.isCadenaNoVacia(salt)) {
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

	/**
	 * Genera una instancia de una contrase�a pasada en plano, usando o no salt
	 * 
	 * @param pass    - {@link String} de la contrase�a en plano
	 * @param useSalt - Indica si la contrase�a debe usar o no salt (Generado
	 *                aleatoriamente)
	 * @return Instancia de {@link Password} con la contrase�a cifrada
	 */
	public static Password getPassword(String pass, boolean useSalt) {
		String salt = "";
		if (useSalt) {
			salt = generateSalt();
			pass = pass + salt;
		}

		String hash = Digest.digestHex(Digest.AlgoritmosDigest.SHA256, pass);

		return new Password(hash, salt);
	}
}
