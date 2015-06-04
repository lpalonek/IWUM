package pl.edu.agh.iwum;

import robocode.Robot;
import robocode.ScannedRobotEvent;

public class PiqleBot extends Robot {

	public void run() {
		while (true) {
			scanAround();
			moveAround();
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		attack(e);
	}

	private void scanAround() {
		turnGunLeft(30);
	}

	private void moveAround() {
		ahead(45);
		turnLeft(45);
	}

	private void attack(ScannedRobotEvent e) {
		fire(3);
	}

}
