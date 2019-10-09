package com.victorlh.tools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Transform {

	public static String toString(InputStream is) {
		if (is == null) {
			return "";
		}
		String salida = "";

		try (InputStreamReader isr = new InputStreamReader(is)) {
			try (BufferedReader br = new BufferedReader(isr)) {
				String linea;
				while ((linea = br.readLine()) != null) {
					salida = salida.concat(linea).concat(System.lineSeparator());
				}
			}
		} catch (IOException e) {
			salida = null;
		}
		return salida;
	}

	public static String toString(boolean bool) {
		return toString(bool, true);
	}

	public static String toString(boolean bool, boolean getSN) {
		if (getSN) {
			return bool ? "S" : "N";
		}
		return Boolean.toString(bool);
	}

	public static String toString(long id) {
		return Long.toString(id);
	}

	public static String toString(double id) {
		return Double.toString(id);
	}

	public static String toString(Object[] array, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				sb.append(separator);
			}
			sb.append(array[i]);
		}
		return sb.toString();
	}

	public static String toString(Object[] array) {
		return toString(array, ",");
	}

	public static InputStream toInputStream(String cadena) {
		byte[] bytes = cadena.getBytes();
		return new ByteArrayInputStream(bytes);
	}

	public static boolean toBoolean(String valor) {
		if (ToolsValidacion.isCadenaVacia(valor)) {
			return false;
		}
		if (valor.equalsIgnoreCase("S")) {
			return true;
		}
		return valor.equalsIgnoreCase("TRUE");
	}

	public static int toInt(String valor) {
		if (ToolsValidacion.isCadenaVacia(valor)) {
			return 0;
		}
		return Integer.parseInt(valor);
	}

	public static long toLong(String valor) {
		if (ToolsValidacion.isCadenaVacia(valor)) {
			return 0;
		}
		return Long.parseLong(valor);
	}

	public static double toDouble(String valor) {
		if (ToolsValidacion.isCadenaVacia(valor)) {
			return 0;
		}
		return Double.parseDouble(valor);
	}

	public static String toHexadecimal(String str) {
		return toHexadecimal(str.getBytes());
	}

	public static String toHexadecimal(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = Tools.hexArray[v >>> 4];
			hexChars[j * 2 + 1] = Tools.hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public static double toDouble(double value, int decimales) {
		String format = "#.";
		for (int i = 0; i < decimales; i++) {
			format += "0";
		}
		DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
		separadoresPersonalizados.setDecimalSeparator('.');
		DecimalFormat df = new DecimalFormat(format, separadoresPersonalizados);
		String result = df.format(value);
		return Double.parseDouble(result);
	}

}
