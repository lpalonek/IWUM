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

	public void log() {
		Logger.getInstance().log("Learning accuracy: \t" + toPercents(getLearningAccuracy()) + "%");
		Logger.getInstance().log("Learned accuracy: \t" + toPercents(getLearnedAccuracy()) + "%");
		Logger.getInstance().log("Percentage gain: \t" + toPercents(getPercentageGain()) + "%");
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

	private double getPercentageGain() {
		return getLearnedAccuracy() / getLearningAccuracy() - 1.0;
	}

	private double toPercents(double value) {
		return Math.round(value * 10000) / 100.0;
	}

}
