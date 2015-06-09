package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class MoveAhead extends BotAction {

	private static final long serialVersionUID = 143588101187122119L;

	public MoveAhead() {
		super();
	}

	public MoveAhead(double ahead) {
		this();
		this.ahead = ahead;
	}

	@Override
	public MoveAhead execute(Robot robot, double argument) {
		ahead = argument * 100;
		robot.ahead(ahead);
		return this;
	}

	@Override
	public Object copy() {
		return new MoveAhead(ahead);
	}

}
