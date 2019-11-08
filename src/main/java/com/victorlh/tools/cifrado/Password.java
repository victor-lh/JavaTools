package com.victorlh.tools.cifrado;

import com.victorlh.tools.ToolsValidacion;
import com.victorlh.tools.Transform;
import com.victorlh.tools.cifrado.Digest.AlgoritmosDigest;

import java.security.SecureRandom;

/**
 * Representacion de una contraseña cifrada con {@link AlgoritmosDigest#SHA256}
 * y con la posibilidad de añadir salt
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
	 * Inicializa la contraseña con salt
	 * 
	 * @param hash - Contraseña cifrada con {@link AlgoritmosDigest#SHA256}
	 * @param salt - Salt usado en la contraseña
	 */
	public Password(String hash, String salt) {
		this.hash = hash;
		this.salt = salt;
	}

	/**
	 * Inicializa la contraseña
	 * 
	 * @param hash - Contraseña cifrada con {@link AlgoritmosDigest#SHA256}
	 */
	public Password(String hash) {
		this(hash, "");
	}

	/**
	 * 
	 * @return Contraseña cifrada con {@link AlgoritmosDigest#SHA256}
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * 
	 * @return Salt usado en la contraseña (Cadena vacia si no tiene salt)
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * Comprueba si la contraseña plana pasada se corresponde con la contraseña
	 * cifrada de la instancia
	 * 
	 * @param pass - {@link String} de la contraseña en plano
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
	 * Genera una instancia de una contraseña pasada en plano, usando o no salt
	 * 
	 * @param pass    - {@link String} de la contraseña en plano
	 * @param useSalt - Indica si la contraseña debe usar o no salt (Generado
	 *                aleatoriamente)
	 * @return Instancia de {@link Password} con la contraseña cifrada
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
