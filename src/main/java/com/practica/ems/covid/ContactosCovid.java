package com.practica.ems.covid;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.practica.excecption.EmsDuplicateLocationException;
import com.practica.excecption.EmsDuplicatePersonException;
import com.practica.excecption.EmsInvalidNumberOfDataException;
import com.practica.excecption.EmsInvalidTypeException;
import com.practica.excecption.EmsLocalizationNotFoundException;
import com.practica.excecption.EmsPersonNotFoundException;
import com.practica.genericas.Constantes;
import com.practica.genericas.Coordenada;
import com.practica.genericas.FechaHora;
import com.practica.genericas.Persona;
import com.practica.genericas.PosicionPersona;
import com.practica.lista.ListaContactos;

public class ContactosCovid {
	private Poblacion poblacion;
	private Localizacion localizacion;
	private ListaContactos listaContactos;

	public ContactosCovid() {
		this.poblacion = new Poblacion();
		this.localizacion = new Localizacion();
		this.listaContactos = new ListaContactos();
	}

	public Poblacion getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Poblacion poblacion) {
		this.poblacion = poblacion;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public ListaContactos getListaContactos() {
		return listaContactos;
	}

	public void setListaContactos(ListaContactos listaContactos) {
		this.listaContactos = listaContactos;
	}

	public void reseter() {
		this.poblacion = new Poblacion();
		this.localizacion = new Localizacion();
		this.listaContactos = new ListaContactos();
	}

	public void loadData(String data, boolean reset) throws EmsInvalidTypeException, EmsInvalidNumberOfDataException,
			EmsDuplicatePersonException, EmsDuplicateLocationException {
		if (reset) {
			this.reseter();
		}
		this.dataLoader(data);
	}

	@SuppressWarnings("resource")
	public void loadDataFile(String fichero, boolean reset) {
		BufferedReader br = null;
		String data;
		try {
			br = Files.newBufferedReader(Paths.get(fichero));
			if (reset) {
				this.reseter();
			}
			while ((data = br.readLine()) != null) {
				this.dataLoader(data);
			}
			if (null != br) {
				br.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dataLoader(String data) throws EmsInvalidTypeException, EmsInvalidNumberOfDataException,
			EmsDuplicatePersonException, EmsDuplicateLocationException {
		String datas[] = dividirEntrada(data.trim());
		for (String linea : datas) {
			String datos[] = this.dividirLineaData(linea);
			if (datos[0].equals("PERSONA")) {
				if (datos.length == Constantes.MAX_DATOS_PERSONA) {
					this.poblacion.addPersona(this.crearPersona(datos));
				} else
					throw new EmsInvalidNumberOfDataException("El número de datos para PERSONA es menor de 8");
			} else if (datos[0].equals("LOCALIZACION")) {
				if (datos.length == Constantes.MAX_DATOS_LOCALIZACION) {
					PosicionPersona pp = this.crearPosicionPersona(datos);
					this.localizacion.addLocalizacion(pp);
					this.listaContactos.insertarNodoTemporal(pp);
				} else
					throw new EmsInvalidNumberOfDataException("El número de datos para LOCALIZACION es menor de 6");
			} else
				throw new EmsInvalidTypeException();
		}
	}

	public int findPersona(String documento) throws EmsPersonNotFoundException {
		int pos;
		try {
			pos = this.poblacion.findPersona(documento);
			return pos;
		} catch (EmsPersonNotFoundException e) {
			throw new EmsPersonNotFoundException();
		}
	}

	public int findLocalizacion(String documento, String fecha, String hora) throws EmsLocalizationNotFoundException {

		int pos;
		try {
			pos = localizacion.findLocalizacion(documento, fecha, hora);
			return pos;
		} catch (EmsLocalizationNotFoundException e) {
			throw new EmsLocalizationNotFoundException();
		}
	}

	public List<PosicionPersona> localizacionPersona(String documento) throws EmsPersonNotFoundException {
		int cont = 0;
		List<PosicionPersona> lista = new ArrayList<PosicionPersona>();
		Iterator<PosicionPersona> it = this.localizacion.getLista().iterator();
		while (it.hasNext()) {
			PosicionPersona pp = it.next();
			if (pp.getDocumento().equals(documento)) {
				cont++;
				lista.add(pp);
			}
		}
		if (cont == 0)
			throw new EmsPersonNotFoundException();
		else
			return lista;
	}

	public boolean delPersona(String documento) throws EmsPersonNotFoundException {
		int cont = 0, pos = -1;
		Iterator<Persona> it = this.poblacion.getLista().iterator();
		while (it.hasNext()) {
			Persona persona = it.next();
			if (persona.getDocumento().equals(documento)) {
				pos = cont;
			}
			cont++;
		}
		if (pos == -1) {
			throw new EmsPersonNotFoundException();
		}
		this.poblacion.getLista().remove(pos);
		return false;
	}

	private String[] dividirEntrada(String input) {
		return input.split("\\n");
	}

	private String[] dividirLineaData(String data) {
		return data.split("\\;");
	}

	private Persona crearPersona(String[] data) {
		return new Persona(data[2], data[3], data[1], data[4], data[5], FechaHora.parsearFecha(data[7]), data[6]);
	}

	private PosicionPersona crearPosicionPersona(String[] data) {
		PosicionPersona posicionPersona = new PosicionPersona();
		posicionPersona.setDocumento(data[1]);
		posicionPersona.setFechaPosicion(FechaHora.parsearFecha(data[2], data[3]));
		posicionPersona.setCoordenada(new Coordenada(Float.parseFloat(data[4]), Float.parseFloat(data[5])));
		return posicionPersona;
	}
}
