package com.practica.genericas;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

public class FechaHora implements Comparable<FechaHora>{
	public class Fecha {
		private int dia, mes, anio;
		 
		public Fecha(int dia, int mes, int anio) {
			super();
			this.dia = dia;
			this.mes = mes;
			this.anio = anio;
		}
		public Fecha(){
			super();
			this.dia=0;
			this.mes=0;
			this.anio=0;
		}

		public int getDia() {
			return dia;
		}

		public void setDia(int dia) {
			this.dia = dia;
		}

		public int getMes() {
			return mes;
		}

		public void setMes(int mes) {
			this.mes = mes;
		}

		public int getAnio() {
			return anio;
		}

		public void setAnio(int anio) {
			this.anio = anio;
		}

		@Override
		public String toString() {
			String cadena = String.format("%2d/%02d/%4d",dia,mes,anio);
			return cadena;
		}
		
		

	}

	public class Hora {
		private int hora, minuto;

		public Hora(int hora, int minuto) {
			super();
			this.hora = hora;
			this.minuto = minuto;
		}
		public Hora(){
			super();
			this.hora=0;
			this.minuto=0;
		}

		public int getHora() {
			return hora;
		}

		public void setHora(int hora) {
			this.hora = hora;
		}

		public int getMinuto() {
			return minuto;
		}

		public void setMinuto(int minuto) {
			this.minuto = minuto;
		}

		@Override
		public String toString() {
			return String.format("%02d:%02d", hora,minuto);
		}
		

	}

	Fecha fecha;
	Hora hora;
	
	public FechaHora(Fecha fecha, Hora hora) {
		super();
		this.fecha = fecha;
		this.hora = hora;
	}

	public FechaHora(int dia, int mes, int anio, int hora, int minuto) {
		this.fecha = new Fecha(dia, mes, anio);
		this.hora = new Hora(hora, minuto);
	}
	public FechaHora(){
		this.fecha=new Fecha();
		this.hora=new Hora();
	}
	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((hora == null) ? 0 : hora.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FechaHora fecha = (FechaHora) obj;
		return getFecha().getDia() == fecha.getFecha().getDia() && getFecha().getMes() == fecha.getFecha().getMes()
				&& getFecha().getAnio() == fecha.getFecha().getAnio()
				&& getHora().getHora() == fecha.getHora().getHora()
				&& getHora().getMinuto() == fecha.getHora().getMinuto();
	}

	@Override
	public int compareTo(FechaHora o) {
		LocalDateTime dateTime1= LocalDateTime.of(this.getFecha().getAnio(), this.getFecha().getMes(), this.getFecha().getDia(), 
				this.getHora().getHora(), this.getHora().getMinuto());
		LocalDateTime dateTime2= LocalDateTime.of(o.getFecha().getAnio(), o.getFecha().getMes(), o.getFecha().getDia(), 
				o.getHora().getHora(), o.getHora().getMinuto());
		
		return dateTime1.compareTo(dateTime2);
	}
	
		public static FechaHora parsearFecha (String fecha) {
		int dia, mes, anio;
		String[] valores = fecha.split("\\/");
		dia = Integer.parseInt(valores[0]);
		mes = Integer.parseInt(valores[1]);
		anio = Integer.parseInt(valores[2]);
		FechaHora fechaHora = new FechaHora(dia, mes, anio, 0, 0);
		return fechaHora;
	}
	
	public static FechaHora parsearFecha (String fecha, String hora) {
		int dia, mes, anio;
		FechaHora aux= parsearFecha(fecha);
		Fecha fechaaux= aux.getFecha();
		dia=fechaaux.getDia() ;
		mes=fechaaux.getMes();
		anio=fechaaux.getAnio();
		int minuto, segundo;
		String[] valores = hora.split("\\:");
		minuto = Integer.parseInt(valores[0]);
		segundo = Integer.parseInt(valores[1]);
		FechaHora fechaHora = new FechaHora(dia, mes, anio, minuto, segundo);
		return fechaHora ;
	}
	
	public static String desparsearFechaHora(FechaHora fecha) {
		String cadena=FechaHora.desparsearFecha(fecha);
		cadena+=String.format("%02d:%02d;",
				fecha.getHora().getHora(),
				fecha.getHora().getMinuto());
		return cadena;
		
	}
	public static String desparsearFecha(FechaHora fecha) {
		String cadena="";
		cadena=String.format("%02d/%02d/%04d;", 
				fecha.getFecha().getDia(), 
				fecha.getFecha().getMes(), 
				fecha.getFecha().getAnio());
		return cadena;
		
	}
public boolean compararfecha(FechaHora inicio, FechaHora fin) {
		
		return (this.compareTo(inicio)>=0 && this.compareTo(fin)<=0);
	}
public static String escribir(FechaHora fecha) {
	String cadena="";
	cadena=fecha.getFecha().toString();
	cadena+= ";" +  fecha.getHora().toString();
	return cadena;
}
}
