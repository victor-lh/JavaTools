package com.victorlh.tools.ordenacion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 14 feb. 2019
 *
 */
public class UtilOrdenacion {

	public static <T extends ElementoOrdenado> void ordenar(T[] lista, KeyOrdenacion keyOrdenacion) {
		ComparatorOrdenacion<T> comparator = new ComparatorOrdenacion<>(keyOrdenacion);
		Arrays.sort(lista, comparator);
	}

	public static <T extends ElementoOrdenado> void ordenar(List<T> lista, KeyOrdenacion keyOrdenacion) {
		ComparatorOrdenacion<T> comparator = new ComparatorOrdenacion<>(keyOrdenacion);
		Collections.sort(lista, comparator);
	}
}
