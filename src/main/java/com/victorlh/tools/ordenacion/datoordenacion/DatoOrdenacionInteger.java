package com.victorlh.tools.ordenacion.datoordenacion;

/**
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 14 feb. 2019
 *
 */
public class DatoOrdenacionInteger extends DatoOrdenacion {

	public DatoOrdenacionInteger(final Integer dato) {
		super(dato, new Comparable<Object>() {
			@Override
			public int compareTo(Object o) {
				return dato.compareTo((Integer) o);
			}
		});
	}

}
