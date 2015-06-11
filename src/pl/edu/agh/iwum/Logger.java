package pl.edu.agh.iwum;

public class Logger {
	private static Logger instance = null;

	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

	public void log(String message) {
		System.err.println(">>>>>>>> " + message);
	}
}
