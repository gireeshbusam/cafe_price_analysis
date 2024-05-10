package com.finalproject.cafePriceAnalysis.common;

import java.util.regex.Pattern;

public class ValidateUtil {
	public boolean isEmptyString(String text) {
		String regex = "^\\s*$";

		boolean emptyFlag = Pattern.matches(regex, text);
		
		return emptyFlag;
	}
}
