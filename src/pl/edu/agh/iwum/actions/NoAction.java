package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class NoAction extends BotAction {

	private static final long serialVersionUID = 6781324920352449018L;

	@Override
	public BotAction execute(Robot robot, double dummyArgument) {
		return this;
	}

	@Override
	public Object copy() {
		return new NoAction();
	}

}
