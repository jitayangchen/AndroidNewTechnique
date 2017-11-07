package com.pepoc.androidnewtechnique.log;

public interface Log {
	
	void v(String message, Throwable t);
	
	void v(String message);

	void d(String message, Throwable t);
	
	void d(String message);
	
	void i(String message, Throwable t);
	
	void i(String message);

	void i(String tag, String message);
	
	void w(String message, Throwable t);
	
	void w(String message);
	
	void e(String message, Throwable t);
	
	void e(String message);

	String getLogMessage();
}
