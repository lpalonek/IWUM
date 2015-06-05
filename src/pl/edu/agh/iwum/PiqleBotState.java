package pl.edu.agh.iwum;

import robocode.Robot;
import environment.IEnvironment;

public class PiqleBotState extends BotState {

	private static final long serialVersionUID = 2867050376890984821L;

	private BotState enemyState = null;

	public PiqleBotState(IEnvironment ct, Robot bot) {
		super(ct, bot);
	}

	public void setEnemyState(BotState enemyState) {
		this.enemyState = enemyState;
	}

}
