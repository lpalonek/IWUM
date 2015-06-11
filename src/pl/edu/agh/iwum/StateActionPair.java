package pl.edu.agh.iwum;

import environment.IAction;
import environment.IState;

public class StateActionPair {

	private IState state;
	private IAction action;

	public StateActionPair(IState state, IAction action) {
		this.state = state;
		this.action = action;
	}

	public IState getState() {
		return state;
	}

	public IAction getAction() {
		return action;
	}

}
