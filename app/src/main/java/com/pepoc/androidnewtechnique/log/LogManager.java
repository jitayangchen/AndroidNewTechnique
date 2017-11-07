package com.pepoc.androidnewtechnique.log;

public class LogManager {

	public static boolean isRelease = false;

	public static void i(String message) {
		LogImpl.getLogImpl().i(message);
	}

	public static void i(String tag, String message) {
		LogImpl.getLogImpl().i(tag, message);
	}

	public static void d(String message) {
		LogImpl.getLogImpl().d(message);
	}

	public static void e(String message, Throwable t) {
		LogImpl.getLogImpl().e(message, t);
	}

}
