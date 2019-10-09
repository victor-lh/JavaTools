package com.victorlh.tools.grupodatos;

import com.victorlh.tools.fecha.Fecha;
import com.victorlh.tools.fecha.FechaHora;
import com.victorlh.tools.fecha.Hora;

import java.util.Set;

public interface IGrupoDatos {

	void addDato(String clave, Object valor);

	void addDato(String clave, Object valor, ETiposDatos tipo);

	Set<String> getClaves();

	ETiposDatos getTipo(String clave);

	Object getDato(String clave);

	int getInt(String clave);

	long getLong(String clave);

	double getDouble(String clave);

	String getString(String clave);

	boolean getBoolean(String clave);

	Fecha getFecha(String clave);

	FechaHora getFechaHora(String clave);

	Hora getHora(String clave);

	byte[] getBytes(String clave);

	void clear();
}
