package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
	private NodoTemporal lista;
	private NodoTemporal aux;
	private int size;

	@SuppressWarnings("null")
	public void insertarNodoTemporal(PosicionPersona pers) {
		NodoTemporal ant = null;
		aux = lista;
		while (aux != null && aux.getFecha().compareTo(pers.getFechaPosicion()) < 0) {
			ant = aux;
			aux = aux.getSiguiente();
		}
		if (aux != null && aux.getFecha().compareTo(pers.getFechaPosicion()) == 0) {
			aux.nodoTemporalNuevo(pers);
		} else {
			insertarNodoNoEncontrado(pers, aux, ant);
			this.size++;
		}
	}

	private void insertarNodoNoEncontrado(PosicionPersona pers, NodoTemporal aux, NodoTemporal ant) {
		NodoTemporal nuevo = nodoTemporalNoEncontrado(pers);

		if (ant == null) {
			nuevo.setSiguiente(lista);
			lista = nuevo;
		} else {

			nuevo.setSiguiente(aux);
			ant.setSiguiente(nuevo);
		}
	}

	private NodoTemporal nodoTemporalNoEncontrado(PosicionPersona pers) {
		aux = new NodoTemporal();
		aux.setFecha(pers.getFechaPosicion());

		NodoPosicion npActual = aux.getListaCoordenadas();
		NodoPosicion npAnt = null;

		if (npActual == null || !npActual.npEncontrado(pers.getCoordenada())) {
			aux.nodoFuncion1(pers, aux, npAnt);
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

	public int numElementosEntreDosInstantes(FechaHora inicio, FechaHora fin, boolean tipoElemento) {
		if (tipoElemento == false && this.size == 0) {
			return 0;
		}
		return lista.contarPersonasEntreInstantes(inicio, fin, tipoElemento);
	}

	@Override
	public String toString() {
		final StringBuilder cadena = new StringBuilder("");
		int cont;
		aux = lista;
		for (cont = 1; cont < size; cont++) {
			cadena.append(aux.getFecha().getFechaHora()).append(' ');
			aux = aux.getSiguiente();
		}
		cadena.append(aux.getFecha().getFechaHora());
		return cadena.toString();
	}
}
