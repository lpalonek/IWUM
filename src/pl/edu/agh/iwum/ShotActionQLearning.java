package pl.edu.agh.iwum;

import algorithms.QLearningSelector;
import environment.IAction;
import environment.IState;

public class ShotActionQLearning {

	private static final int MAX_NUMBER_OF_LEARNINGS = 10000;

	private static ShotActionQLearning instance = null;

	public static ShotActionQLearning getInstance() {
		if (instance == null) {
			instance = new ShotActionQLearning();
		}
		return instance;
	}

	private QLearningSelector selector;
	private int numberOfLearnings;

	public ShotActionQLearning() {
		selector = new QLearningSelector();
	}

	public boolean wantsToLearn() {
		return numberOfLearnings < MAX_NUMBER_OF_LEARNINGS;
	}

	public void learn(IState previousState, IState currentState, IAction action, double reward) {
		++numberOfLearnings;
		selector.learn(previousState, currentState, action, reward);
	}

	public ShotAction getBestAction(IState enemyState) {
		return (ShotAction) selector.bestAction(enemyState);
	}

}
