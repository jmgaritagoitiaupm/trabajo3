package com.practica.excecption;


public class EmsInvalidTypeException extends Exception{

	public EmsInvalidTypeException() {
		super("TIPO DE ENTRADA NO VALIDA!");
	}
	
	public EmsInvalidTypeException(String message) {
		super(message);
	}
	
}
