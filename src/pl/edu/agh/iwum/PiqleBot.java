package pl.edu.agh.iwum;

import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.Color;

import robocode.BattleEndedEvent;
import robocode.Bullet;
import robocode.BulletHitBulletEvent;
import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import environment.IAction;
import environment.IState;

public class PiqleBot extends Robot {

    private static StateActionPairMap<Bullet> shots = new StateActionPairMap<Bullet>();
    private static int currentRound = 0;

    private int numberOfSkippedShotsInRow = 0;
    private int numberOfFailedTrackingAttempts;
    private double gunTurnAmount;
    private EnemyState lastEnemyState;

    public void run() {
        initializeParameters();
        setAdjustGunForRobotTurn(true);
        setAllColors(Color.ORANGE);
        ++currentRound;

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
        lastEnemyState = new EnemyState(0, 0, 0, 0);
        shots.clear();
    }

    @Override
    public void onBattleEnded(BattleEndedEvent event) {
        ShotStatistics.getInstance().log();
    }

    @Override
    public void onBulletHit(BulletHitEvent e) {
        if (QLearning.getInstance().wantsToLearn(currentRound)) {
            ShotStatistics.getInstance().learningShotHit();
        } else {
            ShotStatistics.getInstance().learnedShotHit();
        }

        Bullet bullet = e.getBullet();
        StateActionPair stateActionPair = shots.get(bullet);
        double reward = Settings.getInstance().getReward(e);
        learn(stateActionPair, reward);
        shots.remove(bullet);
    }

    @Override
    public void onBulletMissed(BulletMissedEvent e) {
        if (QLearning.getInstance().wantsToLearn(currentRound)) {
            ShotStatistics.getInstance().learningShotMiss();
        } else {
            ShotStatistics.getInstance().learnedShotMiss();
        }

        Bullet bullet = e.getBullet();
        StateActionPair stateActionPair = shots.get(bullet);
        double reward = Settings.getInstance().getReward(e);
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
        ShotAction bestAttackingAction = getBestAttackingAction(e);

        Bullet bullet = bestAttackingAction.execute(this);
        if (bullet != null) {
            shots.add(bullet, new StateActionPair(getEnemyBotState(), bestAttackingAction));
            numberOfSkippedShotsInRow = 0;
        } else {
            ++numberOfSkippedShotsInRow;
            learnNoShotAction(bestAttackingAction);
        }
//		}
    }

    private void learnNoShotAction(ShotAction action) {
        double reward;
        if (numberOfSkippedShotsInRow < shots.size()) {
            reward = Settings.getInstance().getRewardForNotShooting();
        } else {
            reward = -1;
        }
        QLearning.getInstance().learn(getEnemyBotState(), getEnemyBotState(), action, reward);
    }

    private ShotAction getBestAttackingAction(ScannedRobotEvent e) {
        IState currentEnemyState = getEnemyBotState(e);
        ShotAction bestAction = QLearning.getInstance().getBestAction(currentEnemyState);
        Logger.getInstance().debug("bestAction.getShotPower() == " + bestAction.getShotPower());
        return bestAction;
    }

    private void learn(StateActionPair stateActionPair, double reward) {
        IState previousState = stateActionPair.getState();
        IAction action = stateActionPair.getAction();
        QLearning.getInstance().learn(previousState, getEnemyBotState(), action, reward);
    }

    private EnemyState getEnemyBotState() {
        return lastEnemyState.copy();
    }

    private EnemyState getEnemyBotState(ScannedRobotEvent e) {
        EnemyState currentEnemyState = new EnemyState(e.getDistance(), e.getBearing(), e.getHeading(), e.getVelocity());
        return currentEnemyState;
    }

}
