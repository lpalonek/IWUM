package pl.edu.agh.iwum;

import robocode.BulletHitEvent;
import robocode.BulletMissedEvent;

public class Settings {

	public static final int NUMBER_OF_LEARNING_ROUNDS = 100;

	public static double getReward(BulletHitEvent e) {
		return logRewardAndReturnIt("bullet hit", e.getBullet().getPower() * 2);
	}

	public static double getReward(BulletMissedEvent e) {
		return logRewardAndReturnIt("bullet miss", -e.getBullet().getPower() * 2);
	}

	public static double getRewardForNotShooting() {
		return logRewardAndReturnIt("not shooting", 15);
	}

	private static double logRewardAndReturnIt(String forWhat, double reward) {
		Logger.getInstance().info("Reward for " + forWhat + ": " + reward);
		return reward;
	}
}
