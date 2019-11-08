package com.victorlh.tools.fecha;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 23 ago. 2017
 * 
 */
public final class FechaHora extends Fecha {

	public static final int MILISEGUNDOS_HORA = 60 * 60 * 1000;

	public FechaHora() {
		super();
	}

	public FechaHora(String fecha) {
		super(fecha, fecha.length() == 10 ? getFormatoFechaDefault() : getFormatoFechaHoraDefault());
	}

	public FechaHora(int year, int month, int date, int hour, int minute, int second) {
		super(year, month, date, hour, minute, second);
	}

	public FechaHora(Date date) {
		super(date);
	}

	public FechaHora(Fecha fecha) {
		super(fecha.getDate());
	}

	public static FechaHora getHoraNull() {
		return new FechaHora(1900, 1, 1, 0, 0, 0);
	}

	public static FechaHora getFechaHoraSistema() {
		return new FechaHora();
	}

	public int getHora() {
		return fecha.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinuto() {
		return fecha.get(Calendar.MINUTE);
	}

	public int getSegundo() {
		return fecha.get(Calendar.SECOND);
	}

	@Override
	public FechaHora clone() {
		return new FechaHora(this.getDate());
	}

	@Override
	protected EFormatoFecha getFormatoDefault() {
		return getFormatoFechaHoraDefault();
	}

	public static EFormatoFecha getFormatoFechaHoraDefault() {
		return EFormatoFecha.FECHA_HORA;
	}

}