package com.ameya.sportyshoes.exception;

public class ProductOutOfStockException extends Exception{

	private static final long serialVersionUID = 2594332263957391198L;

	public ProductOutOfStockException() {
		super();
	}
	
	public ProductOutOfStockException(String errors) {
		super(errors);
	}

}
