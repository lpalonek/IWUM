package pl.edu.agh.iwum;

public class PointsStatistics {

	private static PointsStatistics instance = null;

	public static PointsStatistics getInstance() {
		if (instance == null) {
			instance = new PointsStatistics();
		}
		return instance;
	}

	private double numberOfLearningPoints;
	private double numberOfLearningRounds;
	private double numberOfLearnedPoints;
	private double numberOfLearnedRounds;

	public PointsStatistics() {
		numberOfLearningPoints = 0;
		numberOfLearningRounds = 0;
		numberOfLearnedPoints = 0;
		numberOfLearnedRounds = 0;
	}

	public String[] getLogStrings() {
		String averageLearningPoints = "Average learning points: \t" + getAverageLearningPoints();
		String averageLearnedPoints = "Average learned points: \t" + getAverageLearnedPoints();
		return new String[] { averageLearningPoints, averageLearnedPoints };
	}

	public void addLearningPoints(double points) {
		numberOfLearningPoints += points;
		++numberOfLearningRounds;
	}

	public void addLearnedPoints(double points) {
		numberOfLearnedPoints += points;
		++numberOfLearnedRounds;
	}

	public double getAverageLearningPoints() {
		return numberOfLearningPoints / numberOfLearningRounds;
	}

	public double getAverageLearnedPoints() {
		return numberOfLearnedPoints / numberOfLearnedRounds;
	}

}
