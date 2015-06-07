package pl.edu.agh.iwum;

import environment.*;
import pl.edu.agh.iwum.actions.BotAction;
import pl.edu.agh.iwum.actions.FireBullet;
import pl.edu.agh.iwum.actions.GunTurn;
import pl.edu.agh.iwum.actions.MoveAhead;
import pl.edu.agh.iwum.actions.MoveBack;
import pl.edu.agh.iwum.actions.RadarTurn;
import pl.edu.agh.iwum.actions.Turn;
import pl.edu.agh.iwum.actions.NoAction;

public class RobocodeEnvironment implements IEnvironmentSingle {

	private static final long serialVersionUID = 39129605484574612L;

	private PiqleBot bot = null;

	@Override
	public ActionList getActionList(IState s) {
		ActionList actionList = new ActionList(s);
		actionList.add(new NoAction());
		for (int i = 10; i <= 100; i += 10) {
			actionList.add(new MoveAhead(i));
			actionList.add(new MoveBack(i));
		}
		for (int i = -45; i <= 45; i += 2) {
			actionList.add(new GunTurn(i));
			actionList.add(new RadarTurn(i));
			actionList.add(new Turn(i));
		}
		for (double i = 1; i <= 3; i += 0.1) {
			actionList.add(new FireBullet(i));
		}
		return actionList;
	}

	@Override
	public IState successorState(IState s, IAction a) {
		BotState botState = (BotState) s;
		BotAction action = (BotAction) a;
		action.execute(bot);
		return bot.getState();
	}

	@Override
	public IState defaultInitialState() {
		return new BotState(this);
	}

	@Override
	public double getReward(IState s1, IState s2, IAction a) {
		BotState previousState = (BotState) s1;
		BotState newState = (BotState) s1;
		BotAction takenAction = (BotAction) a;

		if (newState.getEnergy() <= 0) {
			return -20.0;
		}

		if (newState.getEnergy() < previousState.getEnergy()) {
			return -3.0;
		}

		if (newState.getEnergy() > previousState.getEnergy()) {
			return 3.0;
		}

		return 0.1;
	}

	@Override
	public boolean isFinal(IState s) {
		BotState botState = (BotState) s;
		boolean isEveryoneElseDead = bot.getOthers() == 0;
		boolean amIDead = botState.getEnergy() <= 0;
		return isEveryoneElseDead || amIDead;
	}

	@Override
	public int whoWins(IState s) {
		if (amIDead(s)) {
			return -1;
		}
		if (isEveryoneElseDead()) {
			return 1;
		}
		return 0;
	}

	public void setBot(PiqleBot bot) {
		this.bot = bot;
	}

	private boolean isEveryoneElseDead() {
		return bot.getOthers() == 0;
	}

	private boolean amIDead(IState s) {
		BotState botState = (BotState) s;
		return botState.getEnergy() <= 0;
	}

}
