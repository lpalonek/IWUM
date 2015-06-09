package pl.edu.agh.iwum;

public class Logger {

	private static boolean isOn = false;

	public static void log(String message) {
		if (isOn) {
			System.err.println(">>>>>>>> " + message);
		}
	}
}
