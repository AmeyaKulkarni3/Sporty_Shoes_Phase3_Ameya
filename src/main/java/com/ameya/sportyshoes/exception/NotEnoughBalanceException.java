package com.ameya.sportyshoes.exception;

public class NotEnoughBalanceException extends Exception{

	private static final long serialVersionUID = 2594332263957391198L;

	public NotEnoughBalanceException() {
		super();
	}
	
	public NotEnoughBalanceException(String errors) {
		super(errors);
	}

}
