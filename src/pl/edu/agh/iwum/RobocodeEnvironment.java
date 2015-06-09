package pl.edu.agh.iwum;

import java.util.Random;

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
	private Random random = new Random();

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
		double argument = random.nextDouble();
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
		double reward = previousState.getRewardAndClearIt();
		Logger.log("my reward: " + reward);
		return reward;
	}

	@Override
	public boolean isFinal(IState s) {
		BotState botState = (BotState) s;
		return isEveryoneElseDead() || amIDead(s);
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
