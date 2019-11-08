package com.victorlh.tools.fecha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase wrapper para fecha
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 23 ago. 2017
 * @see Calendar
 *
 */
public class Fecha implements Cloneable, Comparable<Fecha> {

	/**
	 * Milisegundos que tiene un dia
	 */
	public static final double MILISEGUNDOS_DIA = 86400000;

	private static final int[] DIAS_MES = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static Fecha primerDiaAnyo(int anyo) {
		return new Fecha(1, 1, anyo);
	}

	public static Fecha ultimoDiaAnyo(int anyo) {
		return new Fecha(31, 12, anyo);
	}

	public static boolean isBisiesto(int anyo) {
		return Fecha.primerDiaAnyo(anyo).isBisiesto();
	}

	/**
	 * Devuelve la fecha actual
	 * 
	 * @return Fecha actual
	 */
	public static Fecha getFechaActual() {
		return new Fecha();
	}

	/**
	 * Devuelve un objeto fecha equivalente a null (01/01/1900)
	 * 
	 * @return Fecha null
	 */
	public static Fecha getFechaNull() {
		return new Fecha(1, 1, 1900);
	}

	/**
	 * Devuelve la fecha equivalente a mañada
	 * 
	 * @return Fecha de mañana
	 */
	public static Fecha getTomorrow() {
		Fecha fecha = Fecha.getFechaActual();
		fecha.addDias(1);
		return fecha;
	}

	/**
	 * Devuelve la fecha actual
	 * 
	 * @return Fecha actual
	 */
	public static Fecha getInstance() {
		return new Fecha();
	}

	protected Calendar fecha;

	public Fecha(String fecha) {
		this(fecha, getFormatoFechaDefault());
	}

	public Fecha(String fecha, EFormatoFecha formato) {
		this();
		SimpleDateFormat sdf = new SimpleDateFormat(formato.pattern);
		Date parse;
		try {
			parse = sdf.parse(fecha);
		} catch (ParseException e) {
			parse = Fecha.getFechaNull().getDate();
		}
		setDate(parse);
	}

	public Fecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}

	/**
	 * Crea un objeto fecha con la fecha actual
	 */
	public Fecha() {
		this(new GregorianCalendar());
	}

	public Fecha(int dia, int mes, int anio) {
		this(new GregorianCalendar(anio, (mes - 1), dia));
	}

	protected Fecha(int anio, int mes, int dia, int hora, int min, int seg) {
		this(new GregorianCalendar(anio, (mes - 1), dia, hora, min, seg));
	}

	public Fecha(Date date) {
		this();
		if (date == null) {
			this.setDate(1900, 1, 1);
		} else {
			this.setDate(date);
		}
	}

	public Fecha(Fecha fecha) {
		this();
		this.setDate(fecha.getDate());
	}

	public Fecha addAnyos(int numeroDeAnyos) {
		Fecha clone = this.clone();
		clone.fecha.add(Calendar.YEAR, numeroDeAnyos);
		return clone;
	}

	public Fecha addMeses(int numeroDeMeses) {
		Fecha clone = this.clone();
		clone.fecha.add(Calendar.MONTH, numeroDeMeses);
		return clone;
	}

	public Fecha addDias(int numeroDeDias) {
		Fecha clone = this.clone();
		clone.fecha.add(Calendar.DATE, numeroDeDias);
		return clone;
	}

	public long getMilisegundos() {
		return fecha.getTimeInMillis();
	}

	public Date getDate() {
		return fecha.getTime();
	}

	private void setDate(Date date) {
		fecha.setTime(date);
	}

	private void setDate(int anyo, int mes, int dia) {
		fecha.set(anyo, (mes - 1), dia);
	}

	public int getDia() {
		return fecha.get(Calendar.DATE);
	}

	public int getDiaSemana() {
		switch (fecha.get(Calendar.DAY_OF_WEEK)) {
		case Calendar.MONDAY:
			return 0;
		case Calendar.TUESDAY:
			return 1;
		case Calendar.WEDNESDAY:
			return 2;
		case Calendar.THURSDAY:
			return 3;
		case Calendar.FRIDAY:
			return 4;
		case Calendar.SATURDAY:
			return 5;
		case Calendar.SUNDAY:
			return 6;
		default:
			return -1;
		}
	}

	public int getDiasDiferencia(Fecha fecha) {
		double a = this.getDate().getTime();
		double b = fecha.getDate().getTime();
		double dif = (b - a) / MILISEGUNDOS_DIA;
		int dias = (int) Math.round(dif);
		return dias;
	}

	public int getDiasHabilesDiferencia(Fecha fecha) {
		int difDias = 0;
		if (this.isMismaFecha(fecha)) {
			return 0;
		}
		Fecha fechaOrigen = this.clone();
		Fecha fechaDestino = fecha;
		boolean positivo = true;
		if (this.isMayorQue(fecha)) {
			fechaOrigen = fecha;
			fechaDestino = this.clone();
			positivo = false;
		}

		do {
			fechaOrigen.addDias(1);
			if (!fechaOrigen.isDomingo() && !fechaOrigen.isSabado()) {
				difDias++;
			}
		} while (fechaOrigen.isMenorQue(fechaDestino));
		return difDias * (positivo ? 1 : -1);
	}

	public int getMes() {
		return fecha.get(Calendar.MONTH) + 1;
	}

	public int getDiasMes() {
		int mes = this.getMes() - 1;
		int ann = this.getAnyo();
		int dias = DIAS_MES[mes];
		if (mes == 1 && ann % 4 == 0) {
			dias++;
		}
		return dias;
	}

	public int getAnyo() {
		return fecha.get(Calendar.YEAR);
	}

	public int getDiaAnyo() {
		return getDiasDiferencia(Fecha.primerDiaAnyo(getAnyo()));
	}

	public int getDiasAnyo() {
		Fecha a = Fecha.primerDiaAnyo(getAnyo());
		Fecha b = Fecha.ultimoDiaAnyo(getAnyo());
		return b.getDiasDiferencia(a);
	}

	public Fecha getAyer() {
		return addDias(-1);
	}

	public Fecha getManyana() {
		return addDias(1);
	}

	public Fecha getPrimerDiaSemana() {
		int diaSemana = getDiaSemana();
		return addDias(-1 * diaSemana);
	}

	public Fecha getUltimoDiaSemana() {
		int diaSemana = getDiaSemana();
		return addDias(6 - diaSemana);
	}

	public Fecha getPrimerDiaMes() {
		return new Fecha(1, getMes(), getAnyo());
	}

	public Fecha getUltimoDiaMes() {
		return new Fecha(getDiasMes(), getMes(), getAnyo());
	}

	public boolean isSabado() {
		return fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}

	public boolean isDomingo() {
		return fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	public boolean isBisiesto() {
		return getDiasAnyo() == 366;
	}

	public boolean isFinMes() {
		return getDiasMes() == getDia();
	}

	public boolean isPrincipioMes() {
		return getDia() == 1;
	}

	public boolean isPrincipioAnyo() {
		return (getMes() - 1) == 0 && isPrincipioMes();
	}

	public boolean isFinAnyo() {
		return getMes() == 12 && isFinMes();
	}

	public boolean isMismaFecha(Fecha fecha) {
		if (this == fecha) {
			return true;
		}
		if (this.getDia() != fecha.getDia()) {
			return false;
		}
		if (this.getMes() != fecha.getMes()) {
			return false;
		}
		return this.getAnyo() == fecha.getAnyo();
	}

	public boolean isHoy() {
		Fecha fecha = new Fecha();
		return this.isMismaFecha(fecha);
	}

	public boolean isNull() {
		if (fecha == null)
			return true;
		Fecha f = Fecha.getFechaNull();
		return this.isMismaFecha(f);
	}

	public boolean isMayorQue(Fecha fecha) {
		return this.getMiliDif(fecha) > 0;
	}

	public boolean isMayorIgualQue(Fecha fecha) {
		return this.getMiliDif(fecha) >= 0;
	}

	public boolean isMenorQue(Fecha fecha) {
		return this.getMiliDif(fecha) < 0;
	}

	public boolean isMenorIgualQue(Fecha fecha) {
		return this.getMiliDif(fecha) <= 0;
	}

	public String getFechaFormateada(EFormatoFecha formato) {
		SimpleDateFormat sdf = new SimpleDateFormat(formato.pattern);
		return sdf.format(getDate());
	}

	public String getFechaFormateada() {
		return getFechaFormateada(getFormatoDefault());
	}

	private long getMiliDif(Fecha fecha) {
		long a = this.getMilisegundos();
		long b = fecha.getMilisegundos();
		return a - b;
	}

	@Override
	public String toString() {
		return getFechaFormateada();
	}

	@Override
	public Fecha clone() {
		return new Fecha(this.getDate());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Fecha) {
			Fecha aux = (Fecha) obj;
			return this.isMismaFecha(aux);
		}
		return false;
	}

	protected EFormatoFecha getFormatoDefault() {
		return getFormatoFechaDefault();
	}

	public static EFormatoFecha getFormatoFechaDefault() {
		return EFormatoFecha.CORTO;
	}

	public Fecha truncar() {
		return new Fecha(getAnyo(), getMes(), getDia());
	}

	@Override
	public int compareTo(Fecha o) {
		long miliDif = this.getMiliDif(o);
		return miliDif < 0 ? -1 : (miliDif == 0) ? 0 : 1;
	}

}
