package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

/**
 * Nodo para guardar un instante de tiempo. Además guardamos una lista con las
 * coordeandas y las personas (solo número) que en ese instante están en una
 * coordeanda en concreto
 *
 */
public class NodoTemporal {
	private NodoPosicion listaCoordenadas;
	private FechaHora fecha;
	private NodoTemporal siguiente;

	public NodoTemporal() {
		super();
		siguiente = null;
		listaCoordenadas = null;
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
	
	public void nodoTemporalNuevo(PosicionPersona p) {
		NodoPosicion npActual = this.getListaCoordenadas();
		NodoPosicion npAnt = null;
		while (npActual != null && !npActual.getCoordenada().equals(p.getCoordenada())) {
			npAnt = npActual;
			npActual = npActual.getSiguiente();
		}
		if (npActual != null)
			npActual.setNumPersonas(npActual.getNumPersonas() + 1);
		else
			nodoFuncion1(p, this, npAnt);
	}
	
	public void nodoFuncion1(PosicionPersona p,NodoTemporal temp, NodoPosicion npAnt) {
		NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(), 1, null);
		if (temp.listaCoordenadas == null)
			temp.setListaCoordenadas(npNuevo);
		else
			npAnt.setSiguiente(npNuevo);
	}
	
	public int contadorCoordenadas(int cont) {
		NodoPosicion nodo = listaCoordenadas;
		while (nodo != null) {
			cont = cont + 1;
			nodo = nodo.getSiguiente();
		}
		return cont;
	}
}
