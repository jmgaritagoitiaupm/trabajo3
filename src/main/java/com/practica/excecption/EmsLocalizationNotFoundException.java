package com.practica.excecption;


public class EmsLocalizationNotFoundException extends Exception{

	public EmsLocalizationNotFoundException() {
		super("LOCALIZACION NO ENCONTRADA!");
	}

	public EmsLocalizationNotFoundException(String message) {
		super(message);
	}
	
}
