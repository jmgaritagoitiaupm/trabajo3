package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
	private NodoTemporal lista;
	private int size;

	@SuppressWarnings("null")
	public void insertarNodoTemporal(PosicionPersona p) {
		NodoTemporal aux = lista, ant = null;
		while (aux != null && aux.getFecha().compareTo(p.getFechaPosicion()) < 0) {
				ant = aux;
				aux = aux.getSiguiente();
		}
		if (aux != null && aux.getFecha().compareTo(p.getFechaPosicion()) == 0) {
			nodoTemporalNuevo(p, aux);
		}
		else {
			insertarNodoNoEncontrado(p, aux, ant);
			this.size++;
		}
	}

	private void insertarNodoNoEncontrado(PosicionPersona p, NodoTemporal aux, NodoTemporal ant) {
		NodoTemporal nuevo = nodoTemporalNoEncontrado(p);

		if (ant != null) {
			nuevo.setSiguiente(aux);
			ant.setSiguiente(nuevo);
		} else {
			nuevo.setSiguiente(lista);
			lista = nuevo;
		}
	}


	private NodoTemporal nodoTemporalNoEncontrado(PosicionPersona p) {
		NodoTemporal nuevo = new NodoTemporal();
		nuevo.setFecha(p.getFechaPosicion());

		NodoPosicion npActual = nuevo.getListaCoordenadas();
		NodoPosicion npAnt = null;

		if (npActual == null  || !npActual.npEncontrado(p.getCoordenada())) {
			nodoFuncion1(p, nuevo, npAnt);
		}
		return nuevo;
	}

	private void nodoTemporalNuevo(PosicionPersona p, NodoTemporal aux) {
		NodoPosicion npActual = aux.getListaCoordenadas();
		NodoPosicion npAnt = null;
		while (npActual != null && !npActual.getCoordenada().equals(p.getCoordenada())) {

				npAnt = npActual;
				npActual = npActual.getSiguiente();
		}
		if (npActual != null && npActual.getCoordenada().equals(p.getCoordenada())) npActual.setNumPersonas(npActual.getNumPersonas() + 1);
		else nodoFuncion1(p, aux, npAnt);
	}
	
	private void nodoFuncion1(PosicionPersona p, NodoTemporal nuevo, NodoPosicion npAnt) {
		NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(), 1, null);
		if (nuevo.getListaCoordenadas() == null)
			nuevo.setListaCoordenadas(npNuevo);
		else
			npAnt.setSiguiente(npNuevo);
	}

	public int tamanioLista() {
		return this.size;
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista;
		String cadena = aux.getFecha().getFecha().toString();
		cadena += ";" + aux.getFecha().getHora().toString();
		return cadena;
	}

	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if (this.size == 0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		while (aux != null) {
			if (aux.getFecha().compareTo(inicio) >= 0 && aux.getFecha().compareTo(fin) <= 0) {
				cont = contadorPersonas(cont, aux.getListaCoordenadas());
			}
			aux = aux.getSiguiente();
		}
		return cont;
	}

	private int contadorPersonas(int cont, NodoPosicion nodo) {
		while (nodo != null) {
			cont = cont + nodo.getNumPersonas();
			nodo = nodo.getSiguiente();
		}
		return cont;
	}

	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		NodoTemporal aux = lista;
		int cont = 0;
		while (aux != null) {
			if (aux.getFecha().compareTo(inicio) >= 0 && aux.getFecha().compareTo(fin) <= 0) {
				cont = contadorCoordenadas(aux, cont); 
			}
				aux = aux.getSiguiente();
		}
		return cont;
	}

	private int contadorCoordenadas(NodoTemporal aux, int cont) {
		NodoPosicion nodo = aux.getListaCoordenadas();
		while (nodo != null) {
			cont = cont + 1;
			nodo = nodo.getSiguiente();
		}
		return cont;
	}

	@Override
	public String toString() {
		String cadena = "";
		int cont;
		NodoTemporal aux = lista;
		for (cont = 1; cont < size; cont++) {
			cadena += aux.getFecha().getFecha().toString();
			cadena += ";" + aux.getFecha().getHora().toString() + " ";
			aux = aux.getSiguiente();
		}
		cadena += aux.getFecha().getFecha().toString();
		cadena += ";" + aux.getFecha().getHora().toString();
		return cadena;
	}

}
