package com.victorlh.tools.tabla;

/**
 * 
 * 
 * @author VictorLh (<a href=
 *         "https://github.com/victor-lh">https://github.com/victor-lh</a>)
 *         created on 4 ene. 2018
 *
 * @param <T> - Tipo de objeto que contiene la tabla
 */
public class CasillaTabla<T> {

	private T valor;
	public final int posFila;
	public final int posColumna;

	private transient AbstractTabla<T> tabla;

	CasillaTabla(int posFila, int posColumna, AbstractTabla<T> tabla) {
		this.posFila = posFila;
		this.posColumna = posColumna;
		this.tabla = tabla;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public ColumnaTabla<T> getColumna() {
		return tabla.getColumna(posColumna);
	}

	public FilaTabla<T> getFila() {
		return tabla.getFila(posFila);
	}

	public CasillaTabla<T> getCasillaSuperior() {
		return getColumna().getCasilla(posFila - 1);
	}

	public CasillaTabla<T> getCasillaInferior() {
		return getColumna().getCasilla(posFila + 1);
	}

	public CasillaTabla<T> getCasillaIzquierda() {
		return getFila().getCasilla(posColumna - 1);
	}

	public CasillaTabla<T> getCasillaDerecha() {
		return getFila().getCasilla(posColumna + 1);
	}

	public AbstractTabla<T> getTabla() {
		return tabla;
	}
}
