package br.com.login.exceptions;

public class InvalidOperationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidOperationException(String string) {
		super(string);
	}
}
