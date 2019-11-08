package com.victorlh.tools.cifrado;

/**
 * Codifica un numero en letras del alfabeto, <code>A, B,...,Z,AA,AB,...</code>
 * Ej: <br>
 * <code>
 * 0  -&gt; 'A' <br>
 * 1  -&gt; 'B' <br>
 * 25 -&gt; 'Z' <br>
 * 26 -&gt; 'AA'
 * </code>
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 23 jun. 2018
 *
 */
public class AlfabetoExcel {

	public static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final char[] ALPHABET = ALFABETO.toCharArray();

	/**
	 * Codifica el numero positivo pasado como parmetro con caracteres del alfabeto
	 * 
	 * @param i - Numero que codificar (positivo)
	 * @return {@link String} con el numero codificado
	 */
	public static String toLetraAlfabeto(int i) {
		if (i < 0) {
			throw new IndexOutOfBoundsException("El parametro tiene que ser positivo");
		}

		if (i < ALPHABET.length) {
			return ALPHABET[i] + "";
		}

		String aux = "";

		i = i - ALPHABET.length;

		int quot = i / ALPHABET.length;
		int rem = i % ALPHABET.length;

		aux += toLetraAlfabeto(quot);
		aux += toLetraAlfabeto(rem);

		return aux;
	}

	/**
	 * Decodifica el texto pasado como parametro en su correspondiente valor en
	 * entero
	 * 
	 * @param alpaheto - {@link String} codificado en formato AlphabetoExcel
	 * @return {@link Integer} con el numero decodificado
	 */
	public static int toInt(String alpaheto) {
		char c = alpaheto.charAt(0);
		int position = position(c);

		if (alpaheto.length() == 1) {
			return position;
		}

		position++;
		position *= (int) Math.pow(ALPHABET.length, alpaheto.length() - 1);

		return position + toInt(alpaheto.substring(1));

	}

	private static int position(char c) {
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i] == c) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(position('B'));
	}
}
