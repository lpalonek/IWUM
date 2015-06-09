package pl.edu.agh.iwum.actions;

import robocode.Robot;
import environment.IAction;

public abstract class BotAction implements IAction {

	private static final long serialVersionUID = -7207421949115364405L;

	protected double firedBulletPower;
	protected double radarTurn;
	protected double gunTurn;
	protected double turn;
	protected double ahead;
	protected double back;

	public BotAction(double firedBulletPower, double radarTurn, double gunTurn, double turn, double ahead, double back) {
		this.firedBulletPower = firedBulletPower;
		this.radarTurn = radarTurn;
		this.gunTurn = gunTurn;
		this.turn = turn;
		this.ahead = ahead;
		this.back = back;
	}

	public BotAction() {
		this(0, 0, 0, 0, 0, 0);
	}

	/**
	 * 
	 * @param robot
	 * @param argument
	 *            Number in range [0.0, 1.0]
	 * @return
	 */
	public abstract BotAction execute(Robot robot, double argument);

	@Override
	public abstract Object copy();

	public BotAction combine(final BotAction action) {
		final BotAction myAction = (BotAction) copy();
		final BotAction combinedActions = new BotAction() {
			@Override
			public BotAction execute(Robot robot, double argument) {
				myAction.execute(robot, argument);
				action.execute(robot, argument);
				return this;
			}

			@Override
			public Object copy() {
				// TODO: implement me
				return this;
			}
		};

		combinedActions.firedBulletPower += action.firedBulletPower;
		combinedActions.radarTurn += action.radarTurn;
		combinedActions.gunTurn += action.gunTurn;
		combinedActions.turn += action.turn;
		combinedActions.ahead += action.ahead;
		combinedActions.ahead -= action.back;
		combinedActions.back = 0;

		return combinedActions;
	}

	@Override
	public int nnCodingSize() {
		return 6;
	}

	@Override
	public double[] nnCoding() {
		return new double[] { firedBulletPower, radarTurn, gunTurn, turn, ahead, back };
	}

	public boolean equals(BotAction obj) {
		return firedBulletPower == obj.firedBulletPower && radarTurn == obj.radarTurn && gunTurn == obj.gunTurn && turn == obj.turn && ahead == obj.ahead && back == obj.back;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + hashDouble(firedBulletPower);
		hash = 31 * hash + hashDouble(radarTurn);
		hash = 31 * hash + hashDouble(gunTurn);
		hash = 31 * hash + hashDouble(turn);
		hash = 31 * hash + hashDouble(ahead);
		hash = 31 * hash + hashDouble(back);
		return hash;
	}

	private int hashDouble(double value) {
		long longValue = Double.doubleToLongBits(value);
		int hash = (int) (longValue ^ (longValue >>> 32));
		return hash;
	}

}
