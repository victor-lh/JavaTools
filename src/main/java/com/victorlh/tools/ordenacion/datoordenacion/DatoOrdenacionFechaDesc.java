package com.victorlh.tools.ordenacion.datoordenacion;

import com.victorlh.tools.fecha.Fecha;

/**
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 14 feb. 2019
 *
 */
public class DatoOrdenacionFechaDesc extends DatoOrdenacion {

	public DatoOrdenacionFechaDesc(final Fecha dato) {
		super(dato, new Comparable<Object>() {
			@Override
			public int compareTo(Object o) {
				int i = dato.compareTo((Fecha) o);
				return i * (-1);
			}
		});
	}

}
