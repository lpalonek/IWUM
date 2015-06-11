package pl.edu.agh.iwum;

import algorithms.QLearningSelector;
import environment.IAction;
import environment.IState;

public class QLearning {

	private static QLearning instance = null;

	public static QLearning getInstance() {
		if (instance == null) {
			instance = new QLearning();
		}
		return instance;
	}

	private QLearningSelector selector;

	public QLearning() {
		selector = new QLearningSelector();
	}

	public boolean wantsToLearn(int currentTurn) {
		return currentTurn < Settings.NUMBER_OF_LEARNING_ROUNDS;
	}

	public void learn(IState previousState, IState currentState, IAction action, double reward) {
		selector.learn(previousState, currentState, action, reward);
	}

	public ShotAction getBestAction(IState enemyState) {
		return (ShotAction) selector.bestAction(enemyState);
	}

}
