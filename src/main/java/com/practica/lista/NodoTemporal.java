package com.practica.lista;

import com.practica.genericas.FechaHora;


public class NodoTemporal {
	private NodoPosicion listaCoordenadas;
	private FechaHora fecha;
	private NodoTemporal siguiente;
	
	
	public NodoTemporal() {
		super();
		siguiente = null;
		listaCoordenadas=null;	
	}
	public NodoPosicion getListaCoordenadas() {
		return listaCoordenadas;
	}
	public void setListaCoordenadas(NodoPosicion listaCoordenadas) {
		this.listaCoordenadas = listaCoordenadas;
	}
	public FechaHora getFecha() {
		return fecha;
	}
	public void setFecha(FechaHora fecha) {
		this.fecha = fecha;
	}
	public NodoTemporal getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(NodoTemporal siguiente) {
		this.siguiente = siguiente;
	}	
	public static String escribirFecha(NodoTemporal aux) {
		String cadena="";
		cadena=FechaHora.escribir(aux.getFecha());
		return cadena;
	}
	public boolean comparar(FechaHora inicio, FechaHora fin) {
		return fecha.compararfecha(inicio, fin);
	}
}
