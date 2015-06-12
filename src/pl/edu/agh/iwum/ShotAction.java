package pl.edu.agh.iwum;

import java.util.Random;

import robocode.Bullet;
import robocode.Robot;
import environment.IAction;

public class ShotAction implements IAction {

	private static final long serialVersionUID = -7692341571501280588L;

	public static double[] AVAILABLE_SHOT_POWERS = new double[] { 0.0, /*0.5, 1.0, 1.5, 2.0, 2.5,*/ 3.0 };

	private static Random random = new Random();

	public static double GetRandomShotPower() {
		int maxShotPowerIndex = AVAILABLE_SHOT_POWERS.length;
		double shotPower = AVAILABLE_SHOT_POWERS[random.nextInt(maxShotPowerIndex)];
		return shotPower;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(shotPower);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShotAction other = (ShotAction) obj;
		if (Double.doubleToLongBits(shotPower) != Double.doubleToLongBits(other.shotPower))
			return false;
		return true;
	}

}
