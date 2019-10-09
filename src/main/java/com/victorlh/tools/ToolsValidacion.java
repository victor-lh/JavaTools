package com.victorlh.tools;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ToolsValidacion {

	public static boolean isMismaCadena(String arg1, String arg2) {
		if(arg1 == arg2) {
			return true;
		}
		if(arg1 == null) {
			return false;
		}
		return arg1.equals(arg2);
	}
	
	public static boolean isCorreoValido(String arg1) {
		Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pat.matcher(arg1);
		return matcher.find();
	}

	public static boolean isCadenaVacia(String cadena) {
		if(cadena == null)
			return true;
		String x = cadena.trim();
		return x.equals("");
	}
	
	public static boolean isCadenaNoVacia(String cadena) {
		return !isCadenaVacia(cadena);
	}
	
	public static boolean isArrayNoVacio(Object[] array) {
		return array != null && array.length > 0;
	}
	
	public static boolean arrayContieneValor(Object valor, Object[] array){
		int index = Tools.indexInArray(valor, array);
		return index != -1;
	}
	
	public static boolean isInstanceOfGeneric(Object obj, Type type) {
		return obj.getClass().isAssignableFrom(type.getClass());
	}
	
}
