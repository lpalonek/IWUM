package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class MoveBack extends BotAction {

	private static final long serialVersionUID = -6354281943466920179L;

	public MoveBack() {
		super();
	}

	public MoveBack(double back) {
		this();
		this.back = back;
	}

	@Override
	public MoveBack execute(Robot robot, double back) {
		this.back = back;
		robot.back(back);
		return this;
	}

	@Override
	public Object copy() {
		return new MoveBack(back);
	}

}
