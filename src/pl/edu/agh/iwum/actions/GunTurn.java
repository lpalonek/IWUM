package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class GunTurn extends BotAction {

	private static final long serialVersionUID = -2548342872511312512L;

	public GunTurn(double gunTurn) {
		super(0, 0, gunTurn, 0, 0, 0);
	}

	@Override
	public GunTurn execute(Robot robot) {
		robot.turnGunRight(gunTurn);
		return this;
	}

	@Override
	public Object copy() {
		return new GunTurn(gunTurn);
	}

}
