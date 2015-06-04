package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class FireBullet extends BotAction {

	private static final long serialVersionUID = -6339379386608867531L;

	public FireBullet(double firedBulletPower) {
		super(firedBulletPower, 0, 0, 0, 0, 0);
	}

	@Override
	public FireBullet execute(Robot robot) {
		robot.fireBullet(firedBulletPower);
		return this;
	}

	@Override
	public Object copy() {
		return new FireBullet(firedBulletPower);
	}

}
