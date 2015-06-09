package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class FireBullet extends BotAction {

	private static final long serialVersionUID = -6339379386608867531L;

	public FireBullet() {
		super();
	}

	public FireBullet(double firedBulletPower) {
		this();
		this.firedBulletPower = firedBulletPower;
	}

	@Override
	public FireBullet execute(Robot robot, double argument) {
		firedBulletPower = 0.1 + argument * 2.9;
		robot.fireBullet(firedBulletPower);
		return this;
	}

	@Override
	public Object copy() {
		return new FireBullet(firedBulletPower);
	}

}
