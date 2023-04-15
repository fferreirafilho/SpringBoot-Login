package br.com.login.exceptions;

public class InvalidDataException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidDataException(String string) {
		super(string);
	}

}
