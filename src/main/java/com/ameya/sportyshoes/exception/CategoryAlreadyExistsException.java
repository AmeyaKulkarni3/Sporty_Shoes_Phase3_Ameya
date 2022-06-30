package com.ameya.sportyshoes.exception;

public class CategoryAlreadyExistsException extends Exception{

	private static final long serialVersionUID = -5782726928746653993L;

	public CategoryAlreadyExistsException() {
		super();
	}
	
	public CategoryAlreadyExistsException(String errors) {
		super(errors);
	}

}
