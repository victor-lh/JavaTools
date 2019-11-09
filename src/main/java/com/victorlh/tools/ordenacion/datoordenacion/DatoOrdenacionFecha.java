package com.victorlh.tools.ordenacion.datoordenacion;

import com.victorlh.tools.fecha.Fecha;

/**
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 14 feb. 2019
 *
 */
public class DatoOrdenacionFecha extends DatoOrdenacion {

	public DatoOrdenacionFecha(final Fecha dato) {
		super(dato, new Comparable<Object>() {
			@Override
			public int compareTo(Object o) {
				return dato.compareTo((Fecha) o);
			}
		});
	}

}
