package pl.edu.agh.iwum;

import pl.edu.agh.iwum.actions.BotAction;
import pl.edu.agh.iwum.actions.FireBullet;
import pl.edu.agh.iwum.actions.GunTurn;
import pl.edu.agh.iwum.actions.MoveAhead;
import pl.edu.agh.iwum.actions.MoveBack;
import pl.edu.agh.iwum.actions.NoAction;
import pl.edu.agh.iwum.actions.RadarTurn;
import pl.edu.agh.iwum.actions.Turn;
import environment.ActionList;
import environment.IAction;
import environment.IEnvironmentSingle;
import environment.IState;

public class RobocodeEnvironment implements IEnvironmentSingle {

	private static final long serialVersionUID = 39129605484574612L;

	private PiqleBot bot = null;

	@Override
	public ActionList getActionList(IState s) {
		ActionList actionList = new ActionList(s);
		actionList.add(new NoAction());
		actionList.add(new MoveAhead());
		actionList.add(new MoveBack());
		actionList.add(new GunTurn());
		actionList.add(new RadarTurn());
		actionList.add(new Turn());
		actionList.add(new FireBullet());
		return actionList;
	}

	@Override
	public IState successorState(IState s, IAction a) {
		BotState botState = (BotState) s;
		BotAction action = (BotAction) a;
		double argument = Double.NaN; //TODO
		action.execute(bot, argument);
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

		return previousState.getRewardAndClearIt();
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
