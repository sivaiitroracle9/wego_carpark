package com.wego.common;

public class InputValidator {

	public static boolean validate(String latitude, String longitude, String page, String per_page)
	{
		try{
			Double.valueOf(latitude);
			Double.valueOf(longitude);
			return Integer.valueOf(page) > 0 && Integer.valueOf(per_page) > 0;
		}
		catch (NumberFormatException exception)
		{
			return false;
		}
	}
}
