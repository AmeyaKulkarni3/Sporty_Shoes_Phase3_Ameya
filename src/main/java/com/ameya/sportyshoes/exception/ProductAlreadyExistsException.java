package com.ameya.sportyshoes.exception;

public class ProductAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -5782726928746653993L;

	public ProductAlreadyExistsException() {
		super();
	}
	
	public ProductAlreadyExistsException(String errors) {
		super(errors);
	}

}
