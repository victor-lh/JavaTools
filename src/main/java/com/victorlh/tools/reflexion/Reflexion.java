package com.victorlh.tools.reflexion;

import com.victorlh.tools.ToolsValidacion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflexion {
	
	private static final String METODO_INSTANCE = "getInstance";
	
	public static Class<?> getClass(String clase) {
		if(ToolsValidacion.isCadenaVacia(clase)){
			throw new ErrorReflexion(clase);
		}
		
		try {
			return Class.forName(clase);
		} catch (ClassNotFoundException e) {
			throw new ErrorReflexion(e, clase);
		}
	}
	
	public static Object newInstance(String clase) {
		Class<?> c = Reflexion.getClass(clase);
		return Reflexion.newInstance(c);
	}
	
	public static <T> T newInstance(Class<T> clase) {
		Class<?>[] classArgs = new Class<?>[0];
		Object[] args = new Object[0];
		return Reflexion.newInstance(clase, classArgs, args);
	}
	
	public static Object newInstance(String clase, Class<?>[] classArgs, Object[] args) {
		Class<?> claseRefle = Reflexion.getClass(clase);
		return Reflexion.newInstance(claseRefle, classArgs, args);
	}
	
	public static <T> T newInstance(Class<T> clase, Class<?>[] classArgs, Object[] args) {
		try {
			Constructor<T> constructor = clase.getConstructor(classArgs);
			if(constructor != null) {
				return constructor.newInstance(args);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ErrorReflexion(e, clase);
		}
		return null;
	}
	
	public static Object ejecutarMetodoStatico(Class<?> clase, String metodo, Class<?>[] classArgs, Object[] args) {
		try {
			Method method = clase.getMethod(metodo, classArgs);
			return method.invoke(null, args);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ErrorReflexion(e, clase);
		}
	}
	
	public static Object ejecutarMetodoStatico(String clase, String metodo, Class<?>[] classArgs, Object[] args) {
		Class<?> c = Reflexion.getClass(clase);
		return Reflexion.ejecutarMetodoStatico(c, metodo, classArgs, args);
	}
	
	public static Object ejecutarMetodoStatico(Class<?> clase, String metodo) {
		Class<?>[] classArgs = new Class<?>[0];
		Object[] args = new Object[0];
		return Reflexion.ejecutarMetodoStatico(clase, metodo, classArgs, args);
	}
	
	public static Object ejecutarMetodoStatico(String clase, String metodo) {
		Class<?> c = Reflexion.getClass(clase);
		return Reflexion.ejecutarMetodoStatico(c, metodo);
	}
	
	public static Object ejecutarGetInstance(Class<?> clase) {
		return Reflexion.ejecutarMetodoStatico(clase, METODO_INSTANCE);
	}
	
	public static Object ejecutarGetInstance(String clase) {
		return Reflexion.ejecutarMetodoStatico(clase, METODO_INSTANCE);
	}
	
}
