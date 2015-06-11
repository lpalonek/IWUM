package pl.edu.agh.iwum;

public class Logger {

	private static Logger instance = null;

	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

	private LogLevel logLevel = LogLevel.ERROR;

	public void log(String message) {
		logIfAppropriateLevel(LogLevel.LOG, "LOG  : " + message);
	}

	public void info(String message) {
		logIfAppropriateLevel(LogLevel.INFO, "INFO: " + message);
	}

	public void debug(String message) {
		logIfAppropriateLevel(LogLevel.DEBUG, "DEBUG: " + message);
	}
	
	public void error(String message) {
		logIfAppropriateLevel(LogLevel.DEBUG, "ERROR: " + message);
	}

	private void logIfAppropriateLevel(LogLevel level, String message) {
		if(logLevel.getValue() <= level.getValue()) {
			print(message);
		}
	}
	
	private void print(String message) {
		System.err.println(">>>>>>>> " + message);
	}

	private enum LogLevel {
		LOG(3), ERROR(2), INFO(1), DEBUG(0);

		private int value;

		private LogLevel(int value) {
			this.value = value;
		}

		private int getValue() {
			return value;
		}
	}

}
