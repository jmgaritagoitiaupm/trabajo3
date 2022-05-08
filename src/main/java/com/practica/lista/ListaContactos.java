package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
	private NodoTemporal lista;
	private NodoTemporal aux;
	private int size;

	@SuppressWarnings("null")
	public void insertarNodoTemporal(PosicionPersona p) {
		NodoTemporal ant = null;
		aux = lista;
		while (aux != null && aux.getFecha().compareTo(p.getFechaPosicion()) < 0) {
			ant = aux;
			aux = aux.getSiguiente();
		}
		if (aux != null && aux.getFecha().compareTo(p.getFechaPosicion()) == 0) {
			aux.nodoTemporalNuevo(p);
		} else {
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
		aux = new NodoTemporal();
		aux.setFecha(p.getFechaPosicion());

		NodoPosicion npActual = aux.getListaCoordenadas();
		NodoPosicion npAnt = null;

		if (npActual == null || !npActual.npEncontrado(p.getCoordenada())) {
			aux.nodoFuncion1(p, aux, npAnt);
		}
		return aux;
	}

	public int tamanioLista() {
		return this.size;
	}

	public String getPrimerNodo() {
		FechaHora fecha = lista.getFecha();
		return fecha.getFecha() + ";" + fecha.getHoraFormatted();
	}
	
	public int numElementosEntreDosInstantes(FechaHora inicio, FechaHora fin,boolean tipoElemento) {
		if(tipoElemento == false) {
			if (this.size == 0)
				return 0;
		}
		aux = lista;
		int cont = 0;
		while (aux != null) {
			if (aux.getFecha().compareTo(inicio) >= 0 && aux.getFecha().compareTo(fin) <= 0) {
				if(tipoElemento == false)
					cont = aux.getListaCoordenadas().contadorPersonas(cont);
				else
					cont = aux.contadorCoordenadas(cont);
			}
			aux = aux.getSiguiente();
		}
		return cont;
	}

	@Override
	public String toString() {
		FechaHora fecha;
		String cadena = "";
		int cont;
		aux = lista;
		for (cont = 1; cont < size; cont++) {
			fecha = aux.getFecha();
			cadena += fecha.getFecha();
			cadena += ";" + fecha.getHoraFormatted() + " ";
			aux = aux.getSiguiente();
		}
		cadena += aux.getFecha().getFecha().toString();
		cadena += ";" + aux.getFecha().getHoraFormatted();
		return cadena;
	}

}
