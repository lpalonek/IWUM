package pl.edu.agh.iwum;

import environment.IAction;

public class BotAction implements IAction {

	private static final long serialVersionUID = -7207421949115364405L;

	private double firedBulletPower = 0;
	private double radarTurn = 0;
	private double gunTurn = 0;
	private double turn = 0;
	private double ahead = 0;
	private double back = 0;

	public BotAction(double firedBulletPower, double radarTurn, double gunTurn, double turn, double ahead, double back) {
		this.firedBulletPower = firedBulletPower;
		this.radarTurn = radarTurn;
		this.gunTurn = gunTurn;
		this.turn = turn;
		this.ahead = ahead;
		this.back = back;
	}

	@Override
	public Object copy() {
		return new BotAction(firedBulletPower, radarTurn, gunTurn, gunTurn, ahead, back);
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
		return firedBulletPower == obj.firedBulletPower 
			&& radarTurn == obj.radarTurn
			&& gunTurn == obj.gunTurn
			&& turn == obj.turn
			&& ahead == obj.ahead
			&& back == obj.back;
	}

	@Override
	public int hashCode() {
		Double hash = firedBulletPower;
		hash = hash * 31 + radarTurn;
		hash = hash * 13 + gunTurn;
		hash = hash * 11 + turn;
		hash = hash * 19 + ahead;
		hash = hash * 17 + back;
		return hash.intValue();
	}
	
}
