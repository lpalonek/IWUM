package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class MoveBack extends BotAction {

	private static final long serialVersionUID = -6354281943466920179L;

	public MoveBack(double back) {
		super(0, 0, 0, 0, 0, back);
	}

	@Override
	public MoveBack execute(Robot robot) {
		robot.back(back);
		return this;
	}

	@Override
	public Object copy() {
		return new MoveBack(back);
	}

}
