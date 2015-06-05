package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class NoAction extends BotAction {

	public NoAction() {
		super(0, 0, 0, 0, 0, 0);
	}

	private static final long serialVersionUID = 6781324920352449018L;

	@Override
	public BotAction execute(Robot robot) {
		return this;
	}

	@Override
	public Object copy() {
		return new NoAction();
	}

}
