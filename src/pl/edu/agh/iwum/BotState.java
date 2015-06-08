package pl.edu.agh.iwum;

import robocode.Robot;
import robocode.ScannedRobotEvent;
import environment.AbstractState;
import environment.IEnvironment;

public class BotState extends AbstractState {

	private static final long serialVersionUID = -1884628502581149884L;

	private double energy = 0;
	private double x = 0;
	private double y = 0;
	private double heading = 0;
	private double radarHeading = 0;
	private double gunHeading = 0;

	private double lastEnemyEnergy = 0;
	private double lastEnemyDistance = 0;
	private double lastEnemyHeading = 0;

	private double reward = 0;

	public BotState(IEnvironment ct) {
		super(ct);
	}

	public BotState(IEnvironment ct, Robot bot) {
		this(ct);
		energy = bot.getEnergy();
		x = bot.getX();
		y = bot.getY();
		heading = bot.getHeading();
		radarHeading = bot.getRadarHeading();
		gunHeading = bot.getGunHeading();
	}

	@Override
	public BotState copy() {
		BotState copyOfState = new BotState(getEnvironment());
		copyOfState.energy = energy;
		copyOfState.x = x;
		copyOfState.y = y;
		copyOfState.heading = heading;
		copyOfState.radarHeading = radarHeading;
		copyOfState.gunHeading = gunHeading;
		copyOfState.lastEnemyEnergy = lastEnemyEnergy;
		copyOfState.lastEnemyDistance = lastEnemyDistance;
		copyOfState.lastEnemyHeading = lastEnemyHeading;
		return copyOfState;
	}

	@Override
	public int nnCodingSize() {
		return 9;
	}

	@Override
	public double[] nnCoding() {
		return new double[] { energy, x, y, heading, radarHeading, gunHeading, lastEnemyEnergy, lastEnemyDistance, lastEnemyHeading };
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

	public double getLastEnemyEnergy() {
		return lastEnemyEnergy;
	}

	public double getLastEnemyDistance() {
		return lastEnemyDistance;
	}

	public double getLastEnemyHeading() {
		return lastEnemyHeading;
	}

	public void setLastEnemyInfo(ScannedRobotEvent event) {
		this.lastEnemyEnergy = event.getEnergy();
		this.lastEnemyDistance = event.getDistance();
		this.lastEnemyHeading = event.getHeading();
	}

	public void addPenalty(double penalty) {
		this.reward -= penalty;
	}

	public void addReward(double reward) {
		this.reward += reward;
	}

	public double getRewardAndClearIt() {
		double result = reward;
		reward = 0;
		return result;
	}

}
