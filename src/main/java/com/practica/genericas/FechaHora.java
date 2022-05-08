package com.practica.genericas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FechaHora implements Comparable<FechaHora> {

	private LocalDateTime date;

	public FechaHora(int dia, int mes, int anio, int hora, int minuto) {
		date = LocalDateTime.of(LocalDate.of(anio, mes, dia), LocalTime.of(hora, minuto));
	}

	public int getDia() {
		return date.getDayOfMonth();
	}

	public int getMes() {
		return date.getMonthValue();
	}

	public int getAnio() {
		return date.getYear();
	}

	public int getHora() {
		return date.getHour();
	}

	public int getMinuto() {
		return date.getMinute();
	}

	public String getHoraFormatted() {
		return date.format(DateTimeFormatter.ofPattern("HH:mm"));
	}

	public String getFecha() {
		return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
		return date.compareTo(fecha.date) == 0 ? true : false;
	}

	@Override
	public int compareTo(FechaHora fecha) {
		return date.compareTo(fecha.date);
	}

}
