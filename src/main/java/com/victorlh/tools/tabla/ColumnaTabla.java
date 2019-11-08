package com.victorlh.tools.tabla;

/**
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 4 ene. 2018
 *
 * @param <T>
 */
public class ColumnaTabla<T> {

	private CasillaTabla<T>[] casillas;
	public final int numColumna;

	private transient AbstractTabla<T> tabla;

	public ColumnaTabla(CasillaTabla<T>[] casillas, int numColumna, AbstractTabla<T> tabla) {
		this.casillas = casillas;
		this.numColumna = numColumna;
		this.tabla = tabla;
	}

	public int getNumeroFilas() {
		return casillas.length;
	}

	public ColumnaTabla<T> getColumnaAnterior() {
		return tabla.getColumna(numColumna - 1);
	}

	public ColumnaTabla<T> getColumnaSiguiente() {
		return tabla.getColumna(numColumna + 1);
	}

	public CasillaTabla<T> getCasilla(int pos) {
		return casillas[pos];
	}

	public T getValorCasilla(int pos) {
		return getCasilla(pos).getValor();
	}

	public AbstractTabla<T> getTabla() {
		return tabla;
	}

}
