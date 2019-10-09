package com.victorlh.tools.tabla;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Victorlh
 * 4 ene. 2018
 */
public abstract class AbstractTabla<T> {
	
	private FilaTabla<T>[] filas;
	private transient ColumnaTabla<T>[] columnas;

	
	public AbstractTabla(int alto, int ancho) {
		loadTabla(alto, ancho);
	}
	
	@SuppressWarnings("unchecked")
	private void loadTabla(int alto, int ancho) {
		ArrayList<FilaTabla<T>> filas = new ArrayList<>();
		HashMap<Integer, ArrayList<CasillaTabla<T>>> columnasMap = new HashMap<>();
		
		for (int i = 0; i < alto; i++) {
			ArrayList<CasillaTabla<T>> filaList = new ArrayList<>();
			for (int j = 0; j < ancho; j++) {
				CasillaTabla<T> casilla = new CasillaTabla<>(i, j, this);
				filaList.add(casilla);
				
				ArrayList<CasillaTabla<T>> columnaList = columnasMap.get(j);
				if(columnaList == null) {
					columnaList = new ArrayList<>();
					columnasMap.put(j, columnaList);
				}
				columnaList.add(casilla);
			}
			CasillaTabla<T>[] casillas = (CasillaTabla<T>[]) filaList.toArray(new CasillaTabla<?>[0]);
			FilaTabla<T> fila = new FilaTabla<>(casillas, i, this);
			filas.add(fila);
		}
		
		ArrayList<ColumnaTabla<T>> columnas = new ArrayList<>();
		for (int i = 0; i < ancho; i++) {
			ArrayList<CasillaTabla<T>> columnaList = columnasMap.get(i);
			CasillaTabla<T>[] casillas = (CasillaTabla<T>[]) columnaList.toArray(new CasillaTabla<?>[0]);
			ColumnaTabla<T> columna = new ColumnaTabla<>(casillas, i, this);
			columnas.add(columna);
		}
		
		this.filas = (FilaTabla<T>[]) filas.toArray(new FilaTabla<?>[0]);
		this.columnas = (ColumnaTabla<T>[]) columnas.toArray(new ColumnaTabla<?>[0]);
		
	}
	
	public FilaTabla<T> getFila(int numFila) {
		return filas[numFila];
	}
	
	public ColumnaTabla<T> getColumna(int numColumna) {
		return columnas[numColumna];
	}
	
	public CasillaTabla<T> getCasilla(int posFila, int posColumna) {
		FilaTabla<T> fila = getFila(posFila);
		return fila.getCasilla(posColumna);
	}
	
	public T getValorCasilla(int posFila, int posColumna) {
		return getCasilla(posFila, posColumna).getValor();
	}
	
	public int getAlto() {
		return filas.length;
	}
	
	public int getAncho() {
		return columnas.length;
	}
}
