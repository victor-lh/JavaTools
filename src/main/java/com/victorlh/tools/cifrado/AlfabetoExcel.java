package com.victorlh.tools.cifrado;

/**
 * @author Victorlh
 * 23 jun. 2018
 * 
 */
public class AlfabetoExcel {

	public static final String ALFABETO = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

	public static String toLetraAlfabeto(int i) {
		if(i < ALPHABET.length) {
			return ALPHABET[i] + "";
		}
		
		String aux = "";
		
		i = i - ALPHABET.length;
		
		int quot = i/ALPHABET.length;
		int rem = i%ALPHABET.length;
		
		aux += toLetraAlfabeto(quot);
		aux += toLetraAlfabeto(rem);
		
		return aux;
	}
	
	public static String toLetraAlfabeto(int i, boolean startWith1) {
		if(startWith1) {
			i--;
		}
		return toLetraAlfabeto(i);
	}
	
	public static int toInt(String alpaheto) {
		char c = alpaheto.charAt(0);
		int position = position(c);

		if(alpaheto.length() == 1) {
			return position;
		}
		
		position++;
		position *= (int) Math.pow(ALPHABET.length, alpaheto.length()-1);
		
		return position + toInt(alpaheto.substring(1));
		
	}
	
	private static int position(char c) {
		for (int i = 0; i < ALPHABET.length; i++) {
			if(ALPHABET[i] == c) {
				return i;
			}
		}
		return -1;
	}
}
