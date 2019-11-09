package com.victorlh.tools.ordenacion.datoordenacion;

/**
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 14 feb. 2019
 *
 */
public class DatoOrdenacion implements Comparable<DatoOrdenacion> {

	private Object dato;
	private Comparable<Object> comparable;

	public DatoOrdenacion(Object dato, Comparable<Object> comparable) {
		this.dato = dato;
		this.comparable = comparable;
	}

	@Override
	public int compareTo(DatoOrdenacion o) {
		return comparable.compareTo(o.getDato());
	}

	public Object getDato() {
		return dato;
	}
}
