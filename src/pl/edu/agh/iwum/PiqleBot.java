package pl.edu.agh.iwum;

import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.Color;
import java.util.Random;

import robocode.Bullet;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import environment.IAction;
import environment.IState;

public class PiqleBot extends Robot {

	private static Random random;
	private static StateActionPairMap<Bullet> shots;

	static {
		random = new Random();
		shots = new StateActionPairMap<Bullet>();
	}

	private int numberOfFailedTrackingAttempts;
	private double gunTurnAmount;
	private EnemyState lastEnemyState;

	public void run() {
		initializeParameters();
		setAdjustGunForRobotTurn(true);
		setAllColors(Color.ORANGE);
		while (true) {
			turnGunRight(gunTurnAmount);
			++numberOfFailedTrackingAttempts;

			if (numberOfFailedTrackingAttempts > 2) {
				gunTurnAmount = -10;
			}

			if (numberOfFailedTrackingAttempts > 5) {
				gunTurnAmount = 10;
			}

			if (numberOfFailedTrackingAttempts > 23) {
				gunTurnAmount = -10;
			}
		}
	}

	private void initializeParameters() {
		numberOfFailedTrackingAttempts = 0;
		gunTurnAmount = 10;
		lastEnemyState = new EnemyState(0, 0);
	}

	@Override
	public void onBulletHit(BulletHitEvent e) {
		Bullet bullet = e.getBullet();
		StateActionPair stateActionPair = shots.get(bullet);
		learn(stateActionPair, 1.0);
		shots.remove(bullet);
	}

	@Override
	public void onBulletMissed(BulletMissedEvent e) {
		Bullet bullet = e.getBullet();
		StateActionPair stateActionPair = shots.get(bullet);
		learn(stateActionPair, -1.0);
		shots.remove(bullet);
	}

	@Override
	public void onBulletHitBullet(BulletHitBulletEvent event) {
		Bullet bullet = event.getBullet();
		// this event is treated as noise data
		// therefore there's no learn() call in here
		shots.remove(bullet);
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		lastEnemyState = new EnemyState(e.getDistance(), e.getBearing());
		numberOfFailedTrackingAttempts = 0;
		gunTurnAmount = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmount);
		tryToAttack(e);
	}

	private void tryToAttack(ScannedRobotEvent e) {
		if (ShotActionQLearning.getInstance().wantsToLearn()) {
			double shotPower = random.nextDouble() > 0.5 ? 3.0 : 0.0;
			ShotAction action = new ShotAction(shotPower);
			Bullet bullet = action.execute(this);
			if (bullet != null) {
				shots.add(bullet, new StateActionPair(getEnemyBotState(), action));
			} else {
				ShotActionQLearning.getInstance().learn(getEnemyBotState(), getEnemyBotState(), action, 0.1);
			}
		} else {
			Logger.getInstance().log("I'm using my knowledge.");
			ShotAction bestAttackingAction = getBestAttackingAction(e);
			bestAttackingAction.execute(this);
		}
	}

	private ShotAction getBestAttackingAction(ScannedRobotEvent e) {
		IState currentEnemyState = new EnemyState(e.getDistance(), e.getBearing());
		ShotAction bestAction = ShotActionQLearning.getInstance().getBestAction(currentEnemyState);
		Logger.getInstance().log("bestAction.getShotPower() == " + bestAction.getShotPower());
		return bestAction;
	}

	private void learn(StateActionPair stateActionPair, double reward) {
		IState previousState = stateActionPair.getState();
		IAction action = stateActionPair.getAction();
		ShotActionQLearning.getInstance().learn(previousState, getEnemyBotState(), action, reward);
	}

	private IState getEnemyBotState() {
		return new EnemyState(lastEnemyState.getDistance(), lastEnemyState.getBearing());
	}

}
