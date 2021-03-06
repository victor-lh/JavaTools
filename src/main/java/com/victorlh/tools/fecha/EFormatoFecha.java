package com.victorlh.tools.fecha;

import java.text.SimpleDateFormat;

/**
 * Formatos de fecha preestablecidos
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 23 ago. 2017
 * 
 * @see SimpleDateFormat
 *
 */
public enum EFormatoFecha {

	CORTO("dd/MM/yyyy"),
	INVERTIDO("yyyy/MM/dd"),
	CORTO_GUION("dd-MM-yyyy"),
	INVERTIDO_GUION("yyyy-MM-dd"),
	PLANO("ddMMyyyy"),
	PLANO_INVERTIDO("yyyyMMdd"),
	LARGO("dd 'de' MMMM 'de' yyyy"),
	HORA_SINFECHA("HH:mm:ss"),
	FECHA_HORA("dd/MM/yyyy" + " " + "HH:mm:ss"),
	FECHA_HORA_PLANO("yyyyMMdd_HHmmss"),
	FECHA_HORA_FICHERO("yyyy-MM-dd" + " " + "HH_mm_ss");

	public final String pattern;

	EFormatoFecha(String pattern) {
		this.pattern = pattern;
	}
}