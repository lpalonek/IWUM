package pl.edu.agh.iwum;

import robocode.Robot;
import environment.AbstractState;
import environment.IEnvironment;
import environment.IState;

public class BotState extends AbstractState {

	private static final long serialVersionUID = -1884628502581149884L;

	private double energy = 0;
	private double x = 0;
	private double y = 0;
	private double heading = 0;
	private double radarHeading = 0;
	private double gunHeading = 0;

	public BotState(IEnvironment ct, Robot bot) {
		super(ct);
		energy = bot.getEnergy();
		x = bot.getX();
		y = bot.getY();
		heading = bot.getHeading();
		radarHeading = bot.getRadarHeading();
		gunHeading = bot.getGunHeading();
	}

	public BotState(IEnvironment ct) {
		super(ct);
	}

	@Override
	public IState copy() {
		BotState copyOfState = new BotState(getEnvironment());
		copyOfState.energy = energy;
		copyOfState.x = x;
		copyOfState.y = y;
		copyOfState.heading = heading;
		copyOfState.radarHeading = radarHeading;
		copyOfState.gunHeading = gunHeading;
		return copyOfState;
	}

	@Override
	public int nnCodingSize() {
		return 6;
	}

	@Override
	public double[] nnCoding() {
		return new double[] { energy, x, y, heading, radarHeading, gunHeading };
	}

	public double getEnergy() {
		return energy;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getHeading() {
		return heading;
	}

	public double getRadarHeading() {
		return radarHeading;
	}

	public double getGunHeading() {
		return gunHeading;
	}

}
