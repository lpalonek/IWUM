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
		lastEnemyState = new EnemyState(0);
		shots.clear();
	}

	@Override
	public void onBulletHit(BulletHitEvent e) {
		Bullet bullet = e.getBullet();
		double reward = bullet.getPower() * 10;
		StateActionPair stateActionPair = shots.get(bullet);
		learn(stateActionPair, reward);
		shots.remove(bullet);
	}

	@Override
	public void onBulletMissed(BulletMissedEvent e) {
		Bullet bullet = e.getBullet();
		double reward = -bullet.getPower() / 10;
		StateActionPair stateActionPair = shots.get(bullet);
		learn(stateActionPair, reward);
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
		lastEnemyState = (EnemyState) getEnemyBotState(e);
		numberOfFailedTrackingAttempts = 0;
		gunTurnAmount = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmount);
		tryToAttack(e);
	}

	private void tryToAttack(ScannedRobotEvent e) {
		if (QLearning.getInstance().wantsToLearn()) {
			int maxShotPowerIndex = ShotAction.AVAILABLE_SHOT_POWERS.length;
			double shotPower = ShotAction.AVAILABLE_SHOT_POWERS[random.nextInt(maxShotPowerIndex)];
			ShotAction action = new ShotAction(shotPower);
			Bullet bullet = action.execute(this);
			if (bullet != null) {
				shots.add(bullet, new StateActionPair(getEnemyBotState(), action));
			} else {
				learnNoShotAction(action);
			}
		} else {
			ShotAction bestAttackingAction = getBestAttackingAction(e);
			bestAttackingAction.execute(this);
		}
	}

	private void learnNoShotAction(ShotAction action) {
		double reward = 0.1;
		QLearning.getInstance().learn(getEnemyBotState(), getEnemyBotState(), action, reward);
	}

	private ShotAction getBestAttackingAction(ScannedRobotEvent e) {
		IState currentEnemyState = getEnemyBotState(e);
		ShotAction bestAction = QLearning.getInstance().getBestAction(currentEnemyState);
		if (bestAction.getShotPower() > 0) {
			Logger.getInstance().log("bestAction.getShotPower() == " + bestAction.getShotPower());
		}
		return bestAction;
	}

	private void learn(StateActionPair stateActionPair, double reward) {
		IState previousState = stateActionPair.getState();
		IAction action = stateActionPair.getAction();
		QLearning.getInstance().learn(previousState, getEnemyBotState(), action, reward);
	}

	private EnemyState getEnemyBotState() {
		return new EnemyState(lastEnemyState.getDistance());
	}

	private EnemyState getEnemyBotState(ScannedRobotEvent e) {
		EnemyState currentEnemyState = new EnemyState(e.getDistance());
		return currentEnemyState;
	}

}
