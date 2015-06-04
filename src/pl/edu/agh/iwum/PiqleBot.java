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
		this.fire(3);
	}
	
	private void scanAround() {
		this.turnGunLeft(90);
	}
	
	private void moveAround() {
		this.ahead(30);
		this.turnLeft(30);
	}
}
