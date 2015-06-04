package pl.edu.agh.iwum.actions;

import robocode.Robot;

public class Turn extends BotAction {

	private static final long serialVersionUID = 9221742304979741354L;

	public Turn(double turn) {
		super(0, 0, 0, turn, 0, 0);
	}
	
	@Override
	public Turn execute(Robot robot) {
		robot.turnRight(turn);
		return this;
	}

	@Override
	public Object copy() {
		return new Turn(turn);
	}

}
