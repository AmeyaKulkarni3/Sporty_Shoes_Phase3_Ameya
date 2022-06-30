package com.ameya.sportyshoes.exception;

public class NoSuchProductException extends Exception{

	private static final long serialVersionUID = 2594332263957391198L;

	public NoSuchProductException() {
		super();
	}
	
	public NoSuchProductException(String errors) {
		super(errors);
	}

}
