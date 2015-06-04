package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class RadarTurn extends BotAction {

	private static final long serialVersionUID = -4352354628896459475L;

	public RadarTurn(double radarTurn) {
		super(0, radarTurn, 0, 0, 0, 0);
	}

	@Override
	public RadarTurn execute(Robot robot) {
		robot.turnRadarRight(radarTurn);
		return this;
	}

	@Override
	public Object copy() {
		return new RadarTurn(radarTurn);
	}

}
