package com.pepoc.androidnewtechnique.log;

public class LogManager {

	public static boolean isRelease = false;

	public static void i(String msg) {
		LogImpl.getLogImpl().i(msg);
	}

}
