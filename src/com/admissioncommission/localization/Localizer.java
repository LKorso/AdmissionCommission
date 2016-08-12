package com.admissioncommission.localization;

import java.util.Locale;
import java.util.ResourceBundle;

public class Localizer {
	private static final String RESOURCE_NAME = "com.admissioncommission.localization.content";

	public static ResourceBundle loadResources(Locale locale){
		return ResourceBundle.getBundle(RESOURCE_NAME, locale);
	}
	
	public static Locale getLocale(String country){
		switch (country) {
		case "English":
			return Locale.ENGLISH;
		case "Russian":
			return new Locale("ru", "RU");
		case "Ukrainian":
			return new Locale("ua", "UA");
		default:
			return Locale.ENGLISH;
		}
	}
}
