package com.practica.lista;

import com.practica.genericas.Coordenada;
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
		boolean salir=false,  encontrado = false;
		
		FechaHora fecha = new FechaHora();
		int i=0;
		while (aux!=null && !salir) {
			fecha= aux.getFecha();
			i=fecha.compareTo(p.getFechaPosicion());
			if(i==0) {
				encontrado = true;
				salir = true;
				InsertarLista(p, aux);
			}else if(i<0) {
				ant = aux;
				aux=aux.getSiguiente();
			}else if(i>0) {
				salir=true;
			}
		}	
		MeterNodoLista(encontrado, p, aux, ant);
	}
	
	private boolean buscarPersona (String documento, NodoPersonas nodo) {
		NodoPersonas aux = nodo;
		while(aux!=null) {
			if(aux.getDocumento().equals(documento)) {
				return true;				
			}else {
				aux = aux.getSiguiente();
			}
		}
		return false;
	}
	
	private void insertarPersona (String documento, NodoPersonas nodo) {
		NodoPersonas aux = nodo, nuevo = new NodoPersonas(documento, null);
		while(aux.getSiguiente()!=null) {				
			aux = aux.getSiguiente();				
		}
		aux.setSiguiente(nuevo);		
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
		String cadena = NodoTemporal.escribirFecha(aux); 
		return cadena;
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
	
		return numEntreDosInstantes(inicio,fin,1);
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
	
		return numEntreDosInstantes(inicio,fin,0);
	}
	
	
	
	@Override
	public String toString() {
		String cadena="";
		int a,cont;
		cont=0;
		NodoTemporal aux = lista;
		for(cont=1; cont<size; cont++) {
			cadena += NodoTemporal.escribirFecha(aux) + " ";
			aux=aux.getSiguiente();
		}
		cadena += NodoTemporal.escribirFecha(aux);
		return cadena;
	}	
	
	public void InsertarLista( PosicionPersona p, NodoTemporal aux){
		NodoPosicion ant=null;		
		boolean encontrado = false;
		NodoPosicion actual = aux.getListaCoordenadas();
		while (actual!=null && !encontrado) {
			if(actual.getCoordenada().equals(p.getCoordenada())) {
				encontrado=true;
				actual.setNumPersonas(actual.getNumPersonas()+1);
			}else {
				ant = actual;
				actual = actual.getSiguiente();
			}}
		if(!encontrado)
			NoEncontrado(aux,ant,p);			
	}
	public void NoEncontrado(NodoTemporal aux, NodoPosicion ant, PosicionPersona p){
	NodoPosicion nuevo = new NodoPosicion(p.getCoordenada(),1, null);
			if(aux.getListaCoordenadas()==null)
				aux.setListaCoordenadas(nuevo);
			else
				ant.setSiguiente(nuevo);
	}

	public void MeterNodoLista(boolean encontrado, PosicionPersona p, NodoTemporal aux, NodoTemporal ant){
		if(!encontrado) {
			NodoTemporal nuevo = new NodoTemporal();
			nuevo.setFecha(p.getFechaPosicion());
			InsertarLista(p, nuevo);
			if(ant!=null) {
				nuevo.setSiguiente(aux);
				ant.setSiguiente(nuevo);
			}else {
				nuevo.setSiguiente(lista);
				lista = nuevo;
			}
			this.size++;
		}
	}

	public int numEntreDosInstantes(FechaHora inicio, FechaHora fin, int n){
	if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int contP = 0;
		int a;
		int contI = 0;
		while(aux!=null) {
			if(aux.comparar(inicio, fin)) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					contI=contI+1;
					contP = contP + nodo.getNumPersonas();
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		if(n==1)
		return contP;
		else
		return contI;
	}
	
}
