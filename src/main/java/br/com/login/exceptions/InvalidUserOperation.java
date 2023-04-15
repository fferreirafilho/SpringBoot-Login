package br.com.login.exceptions;

public class InvalidUserOperation extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidUserOperation(String string) {
		super(string);
	}

}
