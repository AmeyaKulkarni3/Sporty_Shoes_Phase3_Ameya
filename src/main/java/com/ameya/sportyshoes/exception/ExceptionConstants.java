package com.ameya.sportyshoes.exception;

public enum ExceptionConstants {
	
	USER_NOT_FOUND("user.not.found"),
	GENERAL_EXCEPTION("general.exception"),
	USER_ALREADY_EXISTS("user.already.exists"),
	USER_UPDATE_SUCCESS("user.update.success"),
	USER_CREATION_FAILED("user.creation.failed"),
	CATEGORY_ALREADY_EXISTS("category.already.exists"),
	CATEGORY_NOT_FOUND("category.not.found"),
	PRODUCT_ALREADY_EXISTS("product.already.exists"),
	PRODUCT_NOT_FOUND("product.not.found"),
	CATEGORY_DELETE_SUCCESS("category.delete.success"),
	NOT_ENOUGH_BALANCE("not.enough.balance"),
	PRODUCT_OUTOF_STOCK("product.outof.stock"),
	USER_DELETE_SUCCESS("user.delete.success");
	
	private final String type;

	private ExceptionConstants(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
