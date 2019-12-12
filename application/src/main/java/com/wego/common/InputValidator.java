package com.wego.common;

public class InputValidator {

	public static boolean validate(double latitude, double longitude, int page, int per_page)
	{
		return page > 0 && per_page > 0;
	}
}
