package com.pepoc.androidnewtechnique.log;

public class LogImpl implements Log {
	
	private String tag = "Technique";

	private static LogImpl logImpl = null;
	
	private LogImpl() {

	}

	public static LogImpl getLogImpl() {
		if (null == logImpl) {
			logImpl = new LogImpl();
		}
		return logImpl;
	}

	@Override
	public void v(String message, Throwable t) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.v(tag, message, t);
			} else {
				android.util.Log.v(tag, baseMsg + " -> " + message, t);
			}
		}
	}

	@Override
	public void v(String message) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.v(tag, message);
			} else {
				android.util.Log.v(tag, baseMsg + " -> " + message);
			}
		}
	}

	@Override
	public void d(String message, Throwable t) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.d(tag, message, t);
			} else {
				android.util.Log.d(tag, baseMsg + " -> " + message, t);
			}
		}
	}

	@Override
	public void d(String message) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.d(tag, message);
			} else {
				android.util.Log.d(tag, baseMsg + " -> " + message);
			}
		}
	}

	@Override
	public void i(String message, Throwable t) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.i(tag, message, t);
			} else {
				android.util.Log.i(tag, baseMsg + " -> " + message, t);
			}
		}
	}

	@Override
	public void i(String message) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.i(tag, message);
			} else {
				android.util.Log.i(tag, baseMsg + " -> " + message);
			}
		}
	}

	@Override
	public void w(String message, Throwable t) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.w(tag, message, t);
			} else {
				android.util.Log.w(tag, baseMsg + " -> " + message, t);
			}
		}
	}

	@Override
	public void w(String message) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.w(tag, message);
			} else {
				android.util.Log.w(tag, baseMsg + " -> " + message);
			}
		}
	}

	@Override
	public void e(String message, Throwable t) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.e(tag, message, t);
			} else {
				android.util.Log.e(tag, baseMsg + " -> " + message, t);
			}
		}
	}

	@Override
	public void e(String message) {
		if (!LogManager.isRelease) {
			String baseMsg = getLogMessage();
			if (null == baseMsg) {
				android.util.Log.e(tag, message);
			} else {
				android.util.Log.e(tag, baseMsg + " -> " + message);
			}
		}
	}

	@Override
	public String getLogMessage() {
	StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
	if (null == stackTraceElements) {
		return null;
	}
	for (StackTraceElement stackTraceElement : stackTraceElements) {
		if (stackTraceElement.isNativeMethod()) {
			continue;
		}
		if (stackTraceElement.getClassName().equals(Thread.class.getName())) {
			continue;
		}
		if (stackTraceElement.getClassName().equals(this.getClass().getName())) {
			continue;
		}
		if (stackTraceElement.getClassName().equals(LogManager.class.getName())) {
			continue;
		}
		return "[" + Thread.currentThread().getName() + ":("
				+ stackTraceElement.getFileName() + ":"
				+ stackTraceElement.getLineNumber() + "):"
				+ stackTraceElement.getMethodName() + "]";
	}
	return null;
	}
}
