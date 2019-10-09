package com.victorlh.tools.grupodatos;

import com.victorlh.tools.fecha.Fecha;
import com.victorlh.tools.fecha.FechaHora;
import com.victorlh.tools.fecha.Hora;

import java.util.HashMap;
import java.util.Set;

public abstract class AbstractGrupoDatos implements IGrupoDatos {

	private HashMap<String, Object> datos;
	private HashMap<String, ETiposDatos> tipos;

	public AbstractGrupoDatos() {
		this.datos = new HashMap<>();
		this.tipos = new HashMap<>();
	}

	@Override
	public void addDato(String clave, Object valor) {
		Class<?> clase = valor.getClass();
		ETiposDatos tipoDato = ETiposDatos.getTipoDato(clase);
		addDato(clave, valor, tipoDato);
	}

	@Override
	public void addDato(String clave, Object valor, ETiposDatos tipo) {
		this.datos.put(clave, valor);
		this.tipos.put(clave, tipo);
	}

	@Override
	public Set<String> getClaves() {
		return datos.keySet();
	}

	@Override
	public ETiposDatos getTipo(String clave) {
		return tipos.get(clave);
	}

	@Override
	public Object getDato(String clave) {
		return datos.get(clave);
	}

	@Override
	public int getInt(String clave) {
		if (tipos.get(clave) == ETiposDatos.INT) {
			return (int) datos.get(clave);
		}
		return 0;
	}

	@Override
	public long getLong(String clave) {
		if (tipos.get(clave) == ETiposDatos.LONG) {
			return (long) datos.get(clave);
		}
		return 0;
	}

	@Override
	public double getDouble(String clave) {
		if (tipos.get(clave) == ETiposDatos.DOUBLE) {
			return (double) datos.get(clave);
		}
		return 0;
	}

	@Override
	public String getString(String clave) {
		if (tipos.get(clave) == ETiposDatos.STRING) {
			return (String) datos.get(clave);
		}
		return "";
	}

	@Override
	public boolean getBoolean(String clave) {
		if (tipos.get(clave) == ETiposDatos.BOOLEAN) {
			return (boolean) datos.get(clave);
		}
		return false;
	}

	@Override
	public Fecha getFecha(String clave) {
		if (tipos.get(clave) == ETiposDatos.FECHA) {
			return (Fecha) datos.get(clave);
		}
		return Fecha.getFechaNull();
	}

	@Override
	public FechaHora getFechaHora(String clave) {
		if (tipos.get(clave) == ETiposDatos.FECHAHORA) {
			return (FechaHora) datos.get(clave);
		}
		return FechaHora.getHoraNull();
	}

	@Override
	public Hora getHora(String clave) {
		if (tipos.get(clave) == ETiposDatos.HORA) {
			return (Hora) datos.get(clave);
		}
		return null;
	}

	@Override
	public byte[] getBytes(String clave) {
		if (tipos.get(clave) == ETiposDatos.BYTES) {
			return (byte[]) datos.get(clave);
		}
		return new byte[0];
	}

	@Override
	public void clear() {
		this.tipos.clear();
		this.datos.clear();
	}
}
