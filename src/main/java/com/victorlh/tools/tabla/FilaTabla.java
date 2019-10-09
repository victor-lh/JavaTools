package com.victorlh.tools.tabla;

/**
 * @author Victorlh
 * 4 ene. 2018
 */
public class FilaTabla<T> {

	private CasillaTabla<T>[] casillas;
	public final int numFila;
	
	private transient AbstractTabla<T> tabla;
	
	public FilaTabla(CasillaTabla<T>[] casillas, int numFila, AbstractTabla<T> tabla) {
		this.casillas = casillas;
		this.numFila = numFila;
		this.tabla = tabla;
	}
	
	public int getNumeroColumnas() {
		return casillas.length;
	}
	
	public AbstractTabla<T> getTabla() {
		return tabla;
	}
	
	public FilaTabla<T> getFilaAnterior() {
		return tabla.getFila(numFila-1);
	}
	
	public FilaTabla<T> getFilaSiguiente() {
		return tabla.getFila(numFila+1);
	}
	
	public CasillaTabla<T> getCasilla(int pos) {
		return casillas[pos];
	}
	
	public T getValorCasilla(int pos) {
		return getCasilla(pos).getValor();
	}
}
