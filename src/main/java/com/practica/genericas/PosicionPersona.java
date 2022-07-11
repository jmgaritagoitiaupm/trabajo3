package com.practica.genericas;
import java.util.Arrays;


public class PosicionPersona {
	private Coordenada coordenada;
	private String documento;
	private FechaHora fechaPosicion;
	
	public PosicionPersona(Coordenada coordenada, String documento, FechaHora fecha) {
		this.coordenada= coordenada;
		this.documento= documento;
		this.fechaPosicion=fecha;
	}
	public Coordenada getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public FechaHora getFechaPosicion() {
		return fechaPosicion;
	}
	public void setFechaPosicion(FechaHora fechaPosicion) {
		this.fechaPosicion = fechaPosicion;
	}
	@Override
	public String toString() {
		String cadena = "";
        cadena += String.format("%s;", getDocumento());
        FechaHora fecha = getFechaPosicion();        
        cadena+=FechaHora.desparsearFechaHora(fecha);
        cadena+=String.format("%.4f;%.4f\n", getCoordenada().getLatitud(), 
	        		getCoordenada().getLongitud());
	
		return cadena;
	}
	public static String[] getData(PosicionPersona p) {
		String data[]= new String [3];
		data[0]=p.getDocumento();
		data[1]=p.getFechaPosicion().getFecha().toString();
		data[2]=p.getFechaPosicion().getHora().toString();
		return data;
		
	}
		
}
