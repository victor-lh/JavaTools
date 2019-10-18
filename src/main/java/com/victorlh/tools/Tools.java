package com.victorlh.tools;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.UUID;

import com.victorlh.tools.ficheros.ToolsFichero;

public class Tools {

	final static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static URL getUrlRecurso(String recurso) {
		recurso = recurso.trim();
		URL url = null;
		char[] cars = recurso.toCharArray();
		int puntos = 0, barras = 0;
		for (char car : cars) {
			if (car == '.') {
				puntos++;
			} else if (car == '/') {
				barras++;
			}
		}
		boolean plantillaEnPaquete = false;
		if (puntos > 1 || barras > 0) {
			plantillaEnPaquete = true;
		}
		if (plantillaEnPaquete) {
			String extension = ToolsFichero.getExtension(recurso);
			String fileName = recurso.substring(0, recurso.length() - extension.length() - 1);
			if (puntos > 1)
				recurso = "/" + fileName.replace('.', '/') + "." + extension;
			url = Tools.class.getResource(recurso);
		}

		return url;
	}

	public static InputStream getRecurso(String recurso) {
		URL url = getUrlRecurso(recurso);
		if (url != null) {
			try {
				return url.openStream();
			} catch (IOException ignored) {
			}
		}
		return null;
	}

	public static String getCadenaRandon() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Devuelve el indice del objeto en el array o -1 si no existe
	 *
	 * @param valor
	 * @param array
	 * @return
	 */
	public static int indexInArray(Object valor, Object[] array) {
		for (int i = 0; i < array.length; i++) {
			if (valor.equals(array[i])) {
				return i;
			}
		}
		return -1;
	}

	public static String[] separadorCadena(String str, String separator) {
		StringTokenizer tokenizer = new StringTokenizer(str, separator);
		String[] array = new String[tokenizer.countTokens()];
		int i = 0;
		while (tokenizer.hasMoreElements()) {
			array[i] = (String) tokenizer.nextElement();
			i++;
		}
		return array;
	}

	public static int getNumeroAleatorio(int minimo, int maximo) {
		return (int) (Math.random() * maximo) + minimo;
	}

	public static boolean getBooleanAleatorio() {
		int numeroAleatorio = getNumeroAleatorio(1, 2);
		return numeroAleatorio == 1;
	}

	public static String notNull(String nombre) {
		return nombre == null ? "" : nombre;
	}

	public static int getRandomPorcentaje(int length, int... porcentajes) {
		if (porcentajes.length != length) {
			throw new IllegalArgumentException("El numero de porcentajes tiene que ser igual al parametro length");
		}

		int porcentajeTotal = 0;
		int[] bloque = new int[100];

		for (int i = 0; i < porcentajes.length; i++) {
			for (int j = 0; j < porcentajes[i]; j++, porcentajeTotal++) {
				if (porcentajeTotal < 100) {
					bloque[porcentajeTotal] = i;
				}
			}
		}

		if (porcentajeTotal != 100) {
			throw new IllegalArgumentException("El total del porcentaje no es 100 (" + porcentajeTotal + "%)");
		}

		int rand = (int) (Math.random() * 100);
		return bloque[rand];
	}
}
