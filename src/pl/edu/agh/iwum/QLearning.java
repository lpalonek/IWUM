package pl.edu.agh.iwum;

import algorithms.QLearningSelector;
import environment.IAction;
import environment.IState;

public class QLearning {

	private static final int MAX_NUMBER_OF_LEARNINGS = 1000;

	private static QLearning instance = null;

	public static QLearning getInstance() {
		if (instance == null) {
			instance = new QLearning();
		}
		return instance;
	}

	private QLearningSelector selector;
	private int numberOfLearnings;

	public QLearning() {
		selector = new QLearningSelector();
	}

	public boolean wantsToLearn() {
		return numberOfLearnings < MAX_NUMBER_OF_LEARNINGS;
	}

	public void learn(IState previousState, IState currentState, IAction action, double reward) {
		++numberOfLearnings;
		selector.learn(previousState, currentState, action, reward);
		Logger.getInstance().log("Learning: " + numberOfLearnings + "/" + MAX_NUMBER_OF_LEARNINGS);
	}

	public ShotAction getBestAction(IState enemyState) {
		return (ShotAction) selector.bestAction(enemyState);
	}

}
