package com.ameya.sportyshoes.exception;

public class NoSuchCategoryException extends Exception{

	private static final long serialVersionUID = 2594332263957391198L;

	public NoSuchCategoryException() {
		super();
	}
	
	public NoSuchCategoryException(String errors) {
		super(errors);
	}

}
