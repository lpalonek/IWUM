package pl.edu.agh.iwum.actions;

import robocode.Robot;
import environment.IAction;

public abstract class BotAction implements IAction {

	private static final long serialVersionUID = -7207421949115364405L;

	protected double firedBulletPower = 0;
	protected double radarTurn = 0;
	protected double gunTurn = 0;
	protected double turn = 0;
	protected double ahead = 0;
	protected double back = 0;

	public BotAction(double firedBulletPower, double radarTurn, double gunTurn, double turn, double ahead, double back) {
		this.firedBulletPower = firedBulletPower;
		this.radarTurn = radarTurn;
		this.gunTurn = gunTurn;
		this.turn = turn;
		this.ahead = ahead;
		this.back = back;
	}

	public abstract BotAction execute(Robot robot);

	@Override
	public abstract Object copy();

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