package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class Turn extends BotAction {

	private static final long serialVersionUID = 9221742304979741354L;

	public Turn() {
		super();
	}

	public Turn(double turn) {
		this();
		this.turn = turn;
	}

	@Override
	public Turn execute(Robot robot, double argument) {
		turn = -180 + argument * 360;
		robot.turnRight(turn);
		return this;
	}

	@Override
	public Object copy() {
		return new Turn(turn);
	}

}
