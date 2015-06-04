package pl.edu.agh.iwum;

import pl.edu.agh.iwum.actions.FireBullet;
import pl.edu.agh.iwum.actions.GunTurn;
import pl.edu.agh.iwum.actions.MoveAhead;
import pl.edu.agh.iwum.actions.Turn;
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
		GunTurn gunTurn = new GunTurn(360).execute(this);
	}

	private void moveAround() {
		MoveAhead moveAhead = new MoveAhead(45).execute(this);
		Turn turn = new Turn(45).execute(this);
	}

	private void attack(ScannedRobotEvent e) {
		FireBullet fireBullet = new FireBullet(3).execute(this);
	}

}
