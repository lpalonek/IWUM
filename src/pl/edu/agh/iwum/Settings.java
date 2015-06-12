package pl.edu.agh.iwum;

import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;

public class Settings {

	public static final int NUMBER_OF_LEARNING_ROUNDS = 100;
	public static final int NUMBER_OF_ROUNDS = 200;

	private static Settings instance = null;

	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

	public double getReward(BulletHitEvent e) {
		return logRewardAndReturnIt("bullet hit", e.getBullet().getPower() * 2);
	}

	public double getReward(BulletMissedEvent e) {
		return logRewardAndReturnIt("bullet miss", -e.getBullet().getPower() * 2);
	}

	public double getRewardForNotShooting() {
		return logRewardAndReturnIt("not shooting", 1);
	}

	private double logRewardAndReturnIt(String forWhat, double reward) {
		Logger.getInstance().info("Reward for " + forWhat + ": " + reward);
		return reward;
	}
}
