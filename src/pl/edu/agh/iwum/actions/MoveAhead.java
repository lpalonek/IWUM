package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class MoveAhead extends BotAction {

	private static final long serialVersionUID = 143588101187122119L;

	public MoveAhead(double ahead) {
		super(0, 0, 0, 0, ahead, 0);
	}

	@Override
	public MoveAhead execute(Robot robot) {
		robot.ahead(ahead);
		return this;
	}

	@Override
	public Object copy() {
		return new MoveAhead(ahead);
	}

}
