package pl.edu.agh.iwum;

import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;

public class Settings {

	public static final int NUMBER_OF_LEARNING_ROUNDS = 250;
	public static final int NUMBER_OF_ROUNDS = 500;

	private static Settings instance = null;

	public static Settings getInstance() {
		if (instance == null) {
			instance = new Settings();
		}
		return instance;
	}

	public double getReward(BulletHitEvent e) {
		return logRewardAndReturnIt("bullet hit", Math.pow(e.getBullet().getPower(), 3));
	}

	public double getReward(BulletMissedEvent e) {
		return logRewardAndReturnIt("bullet miss", Math.pow(-e.getBullet().getPower(), 3));
	}

	public double getRewardForNotShooting() {
		return logRewardAndReturnIt("not shooting", 1);
	}

	private double logRewardAndReturnIt(String forWhat, double reward) {
		Logger.getInstance().info("Reward for " + forWhat + ": " + reward);
		return reward;
	}
}
