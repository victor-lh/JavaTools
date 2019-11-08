package com.victorlh.tools.reflexion;

import com.victorlh.tools.ToolsValidacion;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 8 nov. 2019
 */
public class Reflexion {

	private static final String METODO_INSTANCE = "getInstance";

	/**
	 * Devuelve el {@link Class} correspondiente al nombre de la clase pasado Ej.
	 * com.victorlh.tools.reflexion.Reflexion
	 * 
	 * @param clase - nombre de la clase totalmente cualificado
	 * @param       <T> - Tipo de la clase
	 * @return {@link Class} correspondiente al nombre indicado
	 */
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClass(String clase) {
		if (ToolsValidacion.isCadenaVacia(clase)) {
			throw new ErrorReflexion(clase);
		}

		try {
			return (Class<T>) Class.forName(clase);
		} catch (ClassNotFoundException e) {
			throw new ErrorReflexion(e, clase);
		}
	}

	/**
	 * Crea una nueva instancia de la clase indicada
	 * 
	 * @param clase - nombre de la clase totalmente cualificado
	 * @return nueva instancia de la clase
	 * @see Reflexion#getClass()
	 */
	public static Object newInstance(String clase) {
		Class<?> c = Reflexion.getClass(clase);
		return Reflexion.newInstance(c);
	}

	/**
	 * Crea una nueva instancia de la clase indicada
	 * 
	 * @param clase - {@link Class} de la que crear nueva instancia
	 * @param       <T> - Tipo de la clase
	 * @return nueva instancia de la clase
	 */
	public static <T> T newInstance(Class<T> clase) {
		Class<?>[] classArgs = new Class<?>[0];
		Object[] args = new Object[0];
		return Reflexion.newInstance(clase, classArgs, args);
	}

	/**
	 * Crea una nueva instancia de la clase indicada con el constructor
	 * correspondiente a los parametros indicados
	 * 
	 * @param clase     - nombre de la clase totalmente cualificado
	 * @param classArgs - Clases de los parametros del constructor
	 * @param args      - Valores de los parametros del constructor
	 * @return nueva instancia de la clase
	 */
	public static Object newInstance(String clase, Class<?>[] classArgs, Object[] args) {
		Class<?> claseRefle = Reflexion.getClass(clase);
		return Reflexion.newInstance(claseRefle, classArgs, args);
	}

	/**
	 * Crea una nueva instancia de la clase indicada con el constructor
	 * correspondiente a los parametros indicados
	 * 
	 * @param clase     - {@link Class} de la que crear nueva instancia
	 * @param classArgs - Clases de los parametros del constructor
	 * @param args      - Valores de los parametros del constructor
	 * @param           <T> - Tipo de la clase
	 * @return nueva instancia de la clase
	 */
	public static <T> T newInstance(Class<T> clase, Class<?>[] classArgs, Object[] args) {
		try {
			Constructor<T> constructor = clase.getConstructor(classArgs);
			if (constructor != null) {
				return constructor.newInstance(args);
			}
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ErrorReflexion(e, clase);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T> T ejecutarMetodoStatico(Class<T> clase, String metodo, Class<?>[] classArgs, Object[] args) {
		try {
			Method method = clase.getMethod(metodo, classArgs);
			return (T) method.invoke(null, args);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ErrorReflexion(e, clase);
		}
	}

	public static Object ejecutarMetodoStatico(String clase, String metodo, Class<?>[] classArgs, Object[] args) {
		Class<?> c = Reflexion.getClass(clase);
		return Reflexion.ejecutarMetodoStatico(c, metodo, classArgs, args);
	}

	public static <T> T ejecutarMetodoStatico(Class<T> clase, String metodo) {
		Class<?>[] classArgs = new Class<?>[0];
		Object[] args = new Object[0];
		return Reflexion.ejecutarMetodoStatico(clase, metodo, classArgs, args);
	}

	public static Object ejecutarMetodoStatico(String clase, String metodo) {
		Class<?> c = Reflexion.getClass(clase);
		return Reflexion.ejecutarMetodoStatico(c, metodo);
	}

	public static <T> Object ejecutarGetInstance(Class<T> clase) {
		return Reflexion.ejecutarMetodoStatico(clase, METODO_INSTANCE);
	}

	public static Object ejecutarGetInstance(String clase) {
		return Reflexion.ejecutarMetodoStatico(clase, METODO_INSTANCE);
	}

}
