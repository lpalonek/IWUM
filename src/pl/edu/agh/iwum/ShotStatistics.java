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
	private int numberOfShotsRound;
	private int numberOfAccuredShotsRound;


	public ShotStatistics() {
		numberOfLearningShots = 0;
		numberOfLearningAccurateShots = 0;
		numberOfLearnedShots = 0;
		numberOfLearnedAccurateShots = 0;
		numberOfShotsRound = 0;
		numberOfAccuredShotsRound = 0;
	}

	public String[] getLogStrings() {
		String learningAccuracy = "Learning accuracy: \t" + toPercents(getLearningAccuracy()) + "%";
		String learnedAccuracy = "Learned accuracy: \t" + toPercents(getLearnedAccuracy()) + "%";
		String percentageGain = "Percentage gain: \t" + toPercents(getPercentageGain()) + "%";
		return new String[] { learningAccuracy, learnedAccuracy, percentageGain };
	}

	public void log() {
		for (String string : getLogStrings()) {
			Logger.getInstance().log(string);
		}
	}

	public void learningShotHit() {
		++numberOfLearningShots;
		++numberOfLearningAccurateShots;
		increaseShotRoundHit();
	}

	public void learningShotMiss() {
		++numberOfLearningShots;
		increaseShotRoundMiss();
	}

	public void learnedShotHit() {
		++numberOfLearnedShots;
		++numberOfLearnedAccurateShots;
		increaseShotRoundHit();
	}

	public void learnedShotMiss() {
		++numberOfLearnedShots;
		increaseShotRoundMiss();
	}


	public double getLearningAccuracy() {
		return (1.0 * numberOfLearningAccurateShots) / numberOfLearningShots;
	}

	public double getLearnedAccuracy() {
		return (1.0 * numberOfLearnedAccurateShots) / numberOfLearnedShots;
	}

	public String getRoundAccuracy(){
		String accuracy = String.valueOf(toPercents(1.0 * numberOfAccuredShotsRound / numberOfShotsRound));
		numberOfAccuredShotsRound = numberOfShotsRound = 0;
		return accuracy;
	}

	private void increaseShotRoundHit(){
		++numberOfShotsRound;
		++numberOfAccuredShotsRound;
	}

	private void increaseShotRoundMiss(){
		++numberOfShotsRound;
	}

	private double getPercentageGain() {
		return getLearnedAccuracy() / getLearningAccuracy() - 1.0;
	}

	private double toPercents(double value) {
		return Math.round(value * 10000) / 100.0;
	}

}
