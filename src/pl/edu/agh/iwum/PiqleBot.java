package pl.edu.agh.iwum;

import java.awt.Color;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

public class PiqleBot extends Robot {

	private PiqleConfiguration configuration = PiqleConfiguration.getInstance();

	public void run() {
		setAllColors(Color.ORANGE);
		configuration.getEnvironment().setBot(this);
		play(10);
	}

	public BotState getState() {
		return new BotState(configuration.getEnvironment(), this);
	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		play(10);
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		play(10);
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		play(10);
	}

	private void play(int numberOfActions) {
		configuration.getReferee().setMaxIter(numberOfActions);
		configuration.getReferee().episode(getState());
	}

}
