package com.victorlh.tools.ordenacion.datoordenacion;

/**
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 14 feb. 2019
 *
 */
public class DatoOrdenacionDouble extends DatoOrdenacion {

	public DatoOrdenacionDouble(final Double dato) {
		super(dato, new Comparable<Object>() {
			@Override
			public int compareTo(Object o) {
				return dato.compareTo((Double) o);
			}
		});
	}

}
