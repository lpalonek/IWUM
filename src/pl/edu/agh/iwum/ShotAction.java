package pl.edu.agh.iwum;

import robocode.Bullet;
import robocode.Robot;
import environment.IAction;

public class ShotAction implements IAction {

	private static final long serialVersionUID = -7692341571501280588L;

	private double shotPower;

	public ShotAction(double shotPower) {
		this.shotPower = shotPower;
	}

	@Override
	public Object copy() {
		return new ShotAction(shotPower);
	}

	@Override
	public int nnCodingSize() {
		return 1;
	}

	@Override
	public double[] nnCoding() {
		return new double[] { shotPower };
	}

	public Bullet execute(Robot robot) {
		if (shotPower < 0.1) {
			return null;
		}
		Bullet bullet = robot.fireBullet(shotPower);
		return bullet;
	}

	public double getShotPower() {
		return shotPower;
	}

}
