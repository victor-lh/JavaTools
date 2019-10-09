package com.victorlh.tools.grupodatos;

import com.victorlh.tools.fecha.Fecha;
import com.victorlh.tools.fecha.FechaHora;
import com.victorlh.tools.fecha.Hora;

public enum ETiposDatos {
	OBJECT(0, "Object", Object.class),
	INT(1, "int", Integer.class),
	LONG(2, "long", Long.class),
	DOUBLE(3, "double", Double.class),
	STRING(4, "String", String.class),
	BOOLEAN(5, "boolean", Boolean.class),
	FECHA(6, "Fecha", Fecha.class),
	FECHAHORA(7, "FechaHora", FechaHora.class),
	HORA(8, "Hora", Hora.class),
	BYTES(9, "Bytes", byte[].class);
	
	
	private int claveInterna;
	private String nombre;
	private Class<?> clase;
	ETiposDatos(int claveInterna, String nombre, Class<?> clase) {
		this.claveInterna = claveInterna;
		this.nombre = nombre;
		this.clase = clase;
	}
	
	public int getClaveInterna() {
		return claveInterna;
	}

	public String getNombre() {
		return nombre;
	}

	public Class<?> getClase() {
		return clase;
	}

	public static ETiposDatos getTipoDatoClave(int clave) {
		ETiposDatos[] values = ETiposDatos.values();
		for (ETiposDatos value : values) {
			if (value.getClaveInterna() == clave) {
				return value;
			}
		}
		return null;
	}
	
	public static ETiposDatos getTipoDato(Class<?> tipo) {
		ETiposDatos[] values = ETiposDatos.values();
		for (int i = 0; i < values.length; i++) {
			if(values[i].getClase() == tipo){
				return values[i];
			}
		}
		return ETiposDatos.OBJECT;
	}
}
