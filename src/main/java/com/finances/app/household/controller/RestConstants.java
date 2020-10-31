package com.finances.app.household.controller;

public class RestConstants {

	public static final String SLASH = "/";
	public static final String ID = "id";
	public static final String ID_PATH = SLASH + "{" + ID + "}";

	public static final String TRANSACTION = "transaction";
	public static final String TRANSACTION_PATH = SLASH + TRANSACTION;
	public static final String TRANSACTION_PATH_BY_ID = SLASH + TRANSACTION + ID_PATH;

	private RestConstants() {
		// constants class
		throw new AssertionError();
	}
}
