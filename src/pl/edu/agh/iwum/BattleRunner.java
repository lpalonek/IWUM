package pl.edu.agh.iwum;

/**
 * Created by lpalonek on 11/06/15.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.events.BattleMessageEvent;
import robocode.control.events.RoundStartedEvent;
import robocode.control.snapshot.IScoreSnapshot;

public class BattleRunner {

	private static final String PACKAGE_PATH = "pl/edu/agh/iwum/";

	private static final String SOURCE_DIRECTORY_PREFIX = "out/production/IWUM/"; // "bin/";

	private static final String TARGET_DIRECTORY_PREFIX = "out/robots/";

	private static final String PIQLE_BOT = "PiqleBot.class";

	private static final String DUMMY_BOT = "DummyBot.class";

	public static void main(String[] args) {
		copyRobots();
		RobocodeEngine.setLogMessagesEnabled(true);
		RobocodeEngine.setLogErrorsEnabled(true);
		RobocodeEngine engine = new RobocodeEngine(new File("out")); // Run from
																		// C:/Robocode
		// Add our own battle listener to the RobocodeEngine
		engine.addBattleListener(new BattleObserver());

		RobotSpecification[] robots = engine.getLocalRepository();
		for (int i = 0; i < robots.length; ++i) {
			System.err.println(robots[i].getClassName());
		}

		// Show the Robocode battle view
		engine.setVisible(true);

		// Setup the battle specification

		int numberOfRounds = Settings.NUMBER_OF_ROUNDS;
		BattlefieldSpecification battlefield = new BattlefieldSpecification(800, 600); // 800x600
		RobotSpecification[] selectedRobots = engine.getLocalRepository("pl.edu.agh.iwum.PiqleBot*,"
				+ "pl.edu.agh.iwum.DummyBot*");
		RobotSetup robotSetup1 = new RobotSetup(150.0, 150.0, 1.0);
		RobotSetup robotSetup2 = new RobotSetup(650.0, 350.0, 1.0);
		RobotSetup[] arr = new RobotSetup[2];
		arr[0] = robotSetup1;
		arr[1] = robotSetup2;

		// BattleSpecification battleSpec = new
		// BattleSpecification(numberOfRounds, battlefield, selectedRobots);
		BattleSpecification battleSpec = new BattleSpecification(battlefield, numberOfRounds, 50, 0.1, 50, true,
				robots, arr);

		// Run our specified battle and let it run till it is over
		engine.runBattle(battleSpec, true); // waits till the battle finishes

		// Cleanup our RobocodeEngine
		engine.close();

		// Make sure that the Java VM is shut down properly
		System.exit(0);
	}

	public static void copyRobots() {
		try {
			Files.createDirectories(Paths.get(TARGET_DIRECTORY_PREFIX + PACKAGE_PATH + ""));

			Files.copy(Paths.get(SOURCE_DIRECTORY_PREFIX + PACKAGE_PATH + PIQLE_BOT),
					Paths.get(TARGET_DIRECTORY_PREFIX + PACKAGE_PATH + PIQLE_BOT), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(Paths.get(SOURCE_DIRECTORY_PREFIX + PACKAGE_PATH + DUMMY_BOT),
					Paths.get(TARGET_DIRECTORY_PREFIX + PACKAGE_PATH + DUMMY_BOT), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

//
// Our private battle listener for handling the battle event we are interested
// in.
//
class BattleObserver extends BattleAdaptor {
	private static double prevPiqle = 0;

	private static double prevDummy = 0;

	// Called when the battle is completed successfully with battle results
	public void onBattleCompleted(BattleCompletedEvent e) {
		System.out.println("-- Battle has completed --");

		// Print out the sorted results with the robot names
		System.out.println("Battle results:");
		for (robocode.BattleResults result : e.getSortedResults()) {
			System.out.println("  " + result.getTeamLeaderName() + ": " + result.getScore());
		}

		for (String string : ShotStatistics.getInstance().getLogStrings()) {
			System.out.println(string);
		}

		for (String string : PointsStatistics.getInstance().getLogStrings()) {
			System.out.println(string);
		}
	}

	// Called when the game sends out an information message during the battle
	public void onBattleMessage(BattleMessageEvent e) {
		// System.out.println("Msg> " + e.getMessage());
	}

	// Called when the game sends out an error message during the battle
	public void onBattleError(BattleErrorEvent e) {
		System.out.println("Err> " + e.getError());
	}

	public void onRoundStarted(RoundStartedEvent event) {
		IScoreSnapshot[] snapshots = event.getStartSnapshot().getIndexedTeamScores();
		List<IScoreSnapshot> list = Arrays.asList(snapshots);
		double totalPiqle = snapshots[0].getTotalScore();
		double totalDummy = snapshots[1].getTotalScore();
		StringBuilder sb = new StringBuilder();
		sb.append(event.getRound() + ",");
		sb.append(totalPiqle - prevPiqle + ",");
		sb.append(totalDummy - prevDummy);
		if (event.getRound() < Settings.NUMBER_OF_LEARNING_ROUNDS) {
			PointsStatistics.getInstance().addLearningPoints(totalPiqle - prevPiqle);
		} else {
			PointsStatistics.getInstance().addLearnedPoints(totalPiqle - prevPiqle);
		}

		prevPiqle = totalPiqle;
		prevDummy = totalDummy;
		// list.forEach(elem -> System.out.println(elem.getTotalScore()));
		System.out.println(sb.toString());
	}
}
