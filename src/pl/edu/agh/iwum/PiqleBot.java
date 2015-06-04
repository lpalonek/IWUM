package pl.edu.agh.iwum;

import java.util.Random;

import pl.edu.agh.iwum.actions.FireBullet;
import pl.edu.agh.iwum.actions.GunTurn;
import pl.edu.agh.iwum.actions.MoveAhead;
import pl.edu.agh.iwum.actions.Turn;
import robocode.HitByBulletEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class PiqleBot extends Robot {

	private Random random = new Random();

	public void run() {
		while (true) {
			scanAround();
			moveAround();
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		attack(event);
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		position90DegToBullet(event);
		moveAway();
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

	private void position90DegToBullet(HitByBulletEvent event) {
		double bulletHeading = event.getHeading();
		double escapeAngle = bulletHeading + (random.nextBoolean() ? 90 : -90);
		double angleToRotate = escapeAngle - this.getHeading();
		Turn position90DegToBullet = new Turn(angleToRotate).execute(this);
	}

	private void moveAway() {
		MoveAhead moveAhead = new MoveAhead(100).execute(this);
	}

}
