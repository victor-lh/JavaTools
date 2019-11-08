package com.victorlh.tools.fecha;

import com.victorlh.tools.Transform;

import java.util.Date;

public class Hora {

	private int hora;
	private int minutos;
	private int segundos;
	
	public Hora() {
		this(FechaHora.getFechaHoraSistema());
	}
	
	public Hora(int hora, int minuto, int segundos) {
		this.hora = hora;
		this.minutos = minuto;
		this.segundos = segundos;
	}
	
	public Hora(String hora) {
		String[] split = hora.split(":");
		if(split.length > 0) {
			this.hora = Transform.toInt(split[0]);
		}
		if(split.length > 1) {
			this.minutos = Transform.toInt(split[1]);
		}
		if(split.length > 2) {
			this.segundos = Transform.toInt(split[2]);
		}
	}
	
	public Hora(FechaHora fechaHora) {
		this.hora = fechaHora.getHora();
		this.minutos = fechaHora.getMinuto();
		this.segundos = fechaHora.getSegundo();
	}
	
	public Hora(Date date) {
		this(new FechaHora(date));
	}
	
	public static Hora getHoraActual() {
		return new Hora();
	}
	
	public int getHora() {
		return hora;
	}
	
	public int getMinutos() {
		return minutos;
	}
	
	public int getSegundos() {
		return segundos;
	}
	
	public int toSegundos() {
		int segundos = this.segundos;
		segundos += this.minutos * 60;
		segundos += this.hora *60*60;
		return segundos;
	}
	
	public boolean isMismaHora(Hora hora) {
		return toSegundos() == hora.toSegundos();
	}
	
	public boolean isMayoQue(Hora hora) {
		return toSegundos() > hora.toSegundos();
	}
	
	public boolean isMayoIgualQue(Hora hora) {
		return toSegundos() >= hora.toSegundos();
	}
	
	public boolean isMenorQue(Hora hora) {
		return toSegundos() < hora.toSegundos();
	}
	
	public boolean isMenorIgualQue(Hora hora) {
		return toSegundos() <= hora.toSegundos();
	}
	
	public FechaHora getFechaHoraHoy() {
		Fecha fechaActual = Fecha.getFechaActual();
		return new FechaHora(fechaActual.getAnyo(), fechaActual.getMes(), fechaActual.getDia(), hora, minutos, segundos);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj instanceof Hora) {
			Hora aux = (Hora) obj;
			return isMismaHora(aux);
		}
		return false;
	}

	public String getHoraFormateada(){
		String mins = ""+minutos;
		String segs = ""+segundos;
		if(minutos < 10){
			mins = "0"+mins;
		}
		if(segundos < 10){
			segs = "0"+segs;
		}
		return hora + ":" + mins + ":" + segs;
	}

	public String getHoraFormateadaSinSegundos(){
		String mins = ""+minutos;
		if(minutos < 10){
			mins = "0"+mins;
		}
		return hora + ":" + mins;
	}
	
	@Override
	public String toString() {
		return getHoraFormateada();
	}
}
