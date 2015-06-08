package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class RadarTurn extends BotAction {

	private static final long serialVersionUID = -4352354628896459475L;

	public RadarTurn() {
		super();
	}

	public RadarTurn(double radarTurn) {
		this();
		this.radarTurn = radarTurn;
	}

	@Override
	public RadarTurn execute(Robot robot, double radarTurn) {
		this.radarTurn = radarTurn;
		robot.turnRadarRight(radarTurn);
		return this;
	}

	@Override
	public Object copy() {
		return new RadarTurn(radarTurn);
	}

}
