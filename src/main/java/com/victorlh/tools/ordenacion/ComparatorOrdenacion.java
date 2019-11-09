package com.victorlh.tools.ordenacion;

import java.util.Comparator;

import com.victorlh.tools.ordenacion.datoordenacion.DatoOrdenacion;

/**
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 14 feb. 2019
 *
 */
class ComparatorOrdenacion<T extends ElementoOrdenado> implements Comparator<T> {

	private KeyOrdenacion keyOrdenacion;

	ComparatorOrdenacion(KeyOrdenacion keyOrdenacion) {
		this.keyOrdenacion = keyOrdenacion;
	}

	@Override
	public int compare(T o1, T o2) {
		DatoOrdenacion datoOrdenacion = o1.getDatoOrdenacion(keyOrdenacion);
		DatoOrdenacion datoOrdenacion2 = o2.getDatoOrdenacion(keyOrdenacion);

		if (datoOrdenacion == null || datoOrdenacion2 == null) {
			return 0;
		}

		return datoOrdenacion.compareTo(datoOrdenacion2);
	}

}
