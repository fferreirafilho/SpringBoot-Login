package br.com.login.exceptions;

public class SqlError extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public SqlError(String string) {
		super(string);
	}
}
