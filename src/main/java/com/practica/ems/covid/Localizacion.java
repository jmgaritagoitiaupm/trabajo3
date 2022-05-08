package com.practica.ems.covid;

import java.util.Iterator;
import java.util.LinkedList;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsLocalizationNotFoundException;
import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class Localizacion {
	LinkedList<PosicionPersona> lista;

	public Localizacion() {
		super();
		this.lista = new LinkedList<PosicionPersona>();
	}

	public LinkedList<PosicionPersona> getLista() {
		return lista;
	}

	public void setLista(LinkedList<PosicionPersona> lista) {
		this.lista = lista;
	}

	public void addLocalizacion(PosicionPersona p) throws EmsDuplicateLocationException {
		try {
			findLocalizacion(p.getDocumento(), p.getFechaPosicion().getFecha().toString(),
					p.getFechaPosicion().getHoraFormatted());
			throw new EmsDuplicateLocationException();
		} catch (EmsLocalizationNotFoundException e) {
			lista.add(p);
		}
	}

	public int findLocalizacion(String documento, String fecha, String hora) throws EmsLocalizationNotFoundException {
		int cont = 0;
		Iterator<PosicionPersona> it = lista.iterator();
		while (it.hasNext()) {
			cont++;
			PosicionPersona pp = it.next();
			if (pp.getDocumento().equals(documento)
					&& pp.getFechaPosicion().equals(FechaHora.parsearFecha(fecha, hora))) {
				return cont;
			}
		}
		throw new EmsLocalizationNotFoundException();
	}

	public void delLocalizacion(String documento, String fecha, String hora) throws EmsLocalizationNotFoundException {
		int pos = -1;
		try {
			pos = findLocalizacion(documento, fecha, hora);
		} catch (EmsLocalizationNotFoundException e) {
			throw new EmsLocalizationNotFoundException();
		}
		this.lista.remove(pos);
	}

	void printLocalizacion() {
		for (int i = 0; i < this.lista.size(); i++) {
			System.out.printf("%d;%s;", i, lista.get(i).getDocumento());
			FechaHora fecha = lista.get(i).getFechaPosicion();
			System.out.printf(fecha.getFechaHora());
			System.out.printf("%.4f;%.4f\n", lista.get(i).getCoordenada().getLatitud(),
					lista.get(i).getCoordenada().getLongitud());
		}
	}

	@Override
	public String toString() {
		String cadena = "";
		for (int i = 0; i < this.lista.size(); i++) {
			PosicionPersona pp = lista.get(i);
			cadena += String.format("%s;", pp.getDocumento());
			FechaHora fecha = pp.getFechaPosicion();
			cadena += fecha.getFechaHora();
			cadena += String.format("%.4f;%.4f\n", pp.getCoordenada().getLatitud(), pp.getCoordenada().getLongitud());
		}
		return cadena;
	}

}
