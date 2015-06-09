package pl.edu.agh.iwum;

import java.awt.Color;

import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;
import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.RoundEndedEvent;
import robocode.ScannedRobotEvent;

public class PiqleBot extends Robot {

	private PiqleConfiguration configuration = PiqleConfiguration.getInstance();
	private BotState state;

	public void run() {
		state = new BotState(configuration.getEnvironment(), this);
		setAllColors(Color.ORANGE);
		configuration.getEnvironment().setBot(this);
		while (true) {
			play(1000);
		}
	}

	public BotState getState() {
		return state;
	}

	private void updateState() {
		state = new BotState(configuration.getEnvironment(), this);
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		state.addPenalty(0.8);
		Logger.log("onHitByBullet");
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		state.addPenalty(0.1);
		Logger.log("onHitWall");
	}

	@Override
	public void onBulletHit(BulletHitEvent event) {
		state.addReward(100.0);
		Logger.log("onBulletHit");
	}

	@Override
	public void onBulletMissed(BulletMissedEvent event) {
		state.addPenalty(0.1);
		Logger.log("onBulletMissed");
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		state.setLastEnemyInfo(event);
		state.addReward(0.05);
		Logger.log("onScannedRobot");
	}

	private void play(int numberOfActions) {
		configuration.getReferee().setMaxIter(numberOfActions);
		Logger.log("executing episode with maxIter: " + numberOfActions);
		configuration.getReferee().episode(getState());
		Logger.log("episodes done");
	}

	@Override
	public void onRoundEnded(RoundEndedEvent event) {
		Logger.log("onRoundEnded");
	}

}
