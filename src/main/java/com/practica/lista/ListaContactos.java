package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
	private NodoTemporal lista;
	private int size;
	
	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */
	public void insertarNodoTemporal (PosicionPersona p) {
		NodoTemporal aux = lista, ant=null;
		boolean salir=false, encontrado = false;

		while (aux!=null && !salir) {
			int compare = aux.getFecha().compareTo(p.getFechaPosicion());
			if(compare==0) {
				encontrado = true;
				salir = true;
				aux.insertarCoordenadas(p.getCoordenada());
			}else if(compare<0) {
				ant = aux;
				aux=aux.getSiguiente();
			}else if(compare>0) {
				salir = true;
				
			}
		}
		if (!encontrado) {
			insertarNuevo(aux, ant, p);
		}

	}

	
	private void insertarNuevo(NodoTemporal actual, NodoTemporal anterior, PosicionPersona p) {
		NodoTemporal nuevo = new NodoTemporal();
		nuevo.setFecha(p.getFechaPosicion());
		nuevo.insertarCoordenadas(p.getCoordenada());
		if(anterior!=null) {
			nuevo.setSiguiente(actual);
			anterior.setSiguiente(nuevo);
		}else {
			nuevo.setSiguiente(lista);
			lista = nuevo;
		}
		this.size++;	
		
	}
	
	

	public int personasEnCoordenadas () {
		NodoPosicion aux = this.lista.getListaCoordenadas();
		if(aux==null)
			return 0;
		else {
			int cont;
			for(cont=0;aux!=null;) {
				cont += aux.getNumPersonas();
				aux=aux.getSiguiente();
			}
			return cont;
		}
	}
	
	public int tamanioLista () {
		return this.size;
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista;
		String cadena = aux.cadenaFechaLista();
		return cadena;
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		return  numEntreDosInstantes(inicio,fin,0);
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		return numEntreDosInstantes(inicio,fin,1); 
	}
	
	public int numEntreDosInstantes(FechaHora inicio, FechaHora fin, Integer tipo) {
		if(this.size==0) { 
			return 0;
		}
		NodoTemporal aux = lista;
		int cont = 0;
		cont = 0;
		while(aux!=null) {
			if (aux.comparaFechas(inicio, fin)) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = (tipo == 1 ) ? cont + 1 : cont + nodo.getNumPersonas() ;
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}
	
	
	@Override
	public String toString() {
		String cadena="";
		int cont;
		cont=0;
		NodoTemporal aux = lista;
		for(cont=1; cont<size; cont++) {
			cadena += aux.cadenaFechaLista()+ " ";
			aux=aux.getSiguiente();
		}
		cadena += aux.cadenaFechaLista();
		return cadena;
	}
	
	
	
}
