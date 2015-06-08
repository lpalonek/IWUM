package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class GunTurn extends BotAction {

	private static final long serialVersionUID = -2548342872511312512L;

	public GunTurn() {
		super();
	}

	public GunTurn(double gunTurn) {
		this();
		this.gunTurn = gunTurn;
	}

	@Override
	public GunTurn execute(Robot robot, double gunTurn) {
		this.gunTurn = gunTurn;
		robot.turnGunRight(gunTurn);
		return this;
	}

	@Override
	public Object copy() {
		return new GunTurn(gunTurn);
	}

}
