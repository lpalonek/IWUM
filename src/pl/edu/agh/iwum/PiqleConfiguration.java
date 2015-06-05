package pl.edu.agh.iwum;

import referees.OnePlayerReferee;
import agents.IAgent;
import agents.LoneAgent;
import algorithms.QLearningSelector;

/*
 * To use this class, one needs to provide the following argument to the Java VM:
 * 		-DNOSECURITY=true
 */
public class PiqleConfiguration {

	private static PiqleConfiguration instance = null;

	public static PiqleConfiguration getInstance() {
		if (instance == null) {
			instance = new PiqleConfiguration();
		}
		return instance;
	}

	private RobocodeEnvironment environment;
	private QLearningSelector selector;
	private IAgent agent;
	private OnePlayerReferee referee;

	private PiqleConfiguration() {
		environment = new RobocodeEnvironment();
		selector = new QLearningSelector();
		selector.setEpsilon(0.5);
		selector.setAlphaDecayPower(0.5);
		agent = new LoneAgent(environment, selector);
		referee = new OnePlayerReferee(agent);
	}

	public RobocodeEnvironment getEnvironment() {
		return environment;
	}

	public QLearningSelector getSelector() {
		return selector;
	}

	public IAgent getAgent() {
		return agent;
	}

	public OnePlayerReferee getReferee() {
		return referee;
	}

}
