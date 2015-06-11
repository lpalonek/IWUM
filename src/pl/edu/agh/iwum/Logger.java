package pl.edu.agh.iwum;

public class Logger {

	private static Logger instance = null;

	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

	private LogLevel logLevel = LogLevel.INFO;

	private void print(String message) {
		System.err.println(">>>>>>>> " + message);
	}

	public void log(String message) {
		print(message);
	}

	public void info(String message) {
		if (logLevel.getValue() <= LogLevel.LOG.getValue()) {
			print("INFO: " + message);
		}
	}

	public void debug(String message) {
		if (logLevel.getValue() <= LogLevel.DEBUG.getValue()) {
			print("DBUG: " + message);
		}
	}

	private enum LogLevel {
		LOG(2), INFO(1), DEBUG(0);

		private int value;

		private LogLevel(int value) {
			this.value = value;
		}

		private int getValue() {
			return value;
		}
	}

}
