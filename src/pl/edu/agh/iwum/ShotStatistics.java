package pl.edu.agh.iwum;

public class ShotStatistics {

	private static ShotStatistics instance = null;

	public static ShotStatistics getInstance() {
		if (instance == null) {
			instance = new ShotStatistics();
		}
		return instance;
	}

	private int numberOfLearningShots;
	private int numberOfLearningAccurateShots;
	private int numberOfLearnedShots;
	private int numberOfLearnedAccurateShots;

	public ShotStatistics() {
		numberOfLearningShots = 0;
		numberOfLearningAccurateShots = 0;
		numberOfLearnedShots = 0;
		numberOfLearnedAccurateShots = 0;
	}

	public void learningShotHit() {
		++numberOfLearningShots;
		++numberOfLearningAccurateShots;
	}

	public void learningShotMiss() {
		++numberOfLearningShots;
	}

	public void learnedShotHit() {
		++numberOfLearnedShots;
		++numberOfLearnedAccurateShots;
	}

	public void learnedShotMiss() {
		++numberOfLearnedShots;
	}

	public double getLearningAccuracy() {
		return (1.0 * numberOfLearningAccurateShots) / numberOfLearningShots;
	}

	public double getLearnedAccuracy() {
		return (1.0 * numberOfLearnedAccurateShots) / numberOfLearnedShots;
	}

	public void log() {
		Logger.getInstance().info("Learning accuracy: \t" + getLearningAccuracy());
		Logger.getInstance().info("Learned accuracy: \t" + getLearnedAccuracy());
	}

}
