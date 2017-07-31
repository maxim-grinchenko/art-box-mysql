package com.manager;

import java.util.ResourceBundle;

public class PropertiesLoader {

	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

	private PropertiesLoader() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
