package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Config {
	
	static Properties baseConfig = null;
	String path = null;

	public Config(String path) {
		this.path = path;
		this.init();
	}

	void init() {
		File baseConfigFile = new File(this.path);
		try {
			InputStream input = new FileInputStream(baseConfigFile);
			baseConfig = new Properties();
			baseConfig.load(input);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static String get(String propName) {
		System.out.println("Set " + propName +" as " + baseConfig.getProperty(propName));
		return baseConfig.getProperty(propName);
	}
}
