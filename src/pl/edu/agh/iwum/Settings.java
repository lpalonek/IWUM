package pl.edu.agh.iwum;

import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;

public class Settings {

	public static final int NUMBER_OF_LEARNING_ROUNDS = 100;

	public static double getReward(BulletHitEvent e) {
		return e.getBullet().getPower();
	}

	public static double getReward(BulletMissedEvent e) {
		return -e.getBullet().getPower();
	}

	public static double getRewardForNotShooting() {
		return 15;
	}
}
