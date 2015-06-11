package pl.edu.agh.iwum;

import environment.ActionList;
import environment.IAction;
import environment.IEnvironment;
import environment.IState;

public class EnemyState implements IState {

	private static final long serialVersionUID = -3643819132494631724L;

	private double distance;
	private double bearing;

	public EnemyState(double distance, double bearing) {
		this.distance = distance;
		this.bearing = bearing;
	}

	public double getDistance() {
		return distance;
	}

	public double getBearing() {
		return bearing;
	}

	@Override
	public ActionList getActionList() {
		ActionList actionList = new ActionList(new EnemyState(0, 0));
		actionList.add(new ShotAction(3.0));
		actionList.add(new ShotAction(0.0));
		return actionList;
	}

	@Override
	public void setEnvironment(IEnvironment c) {
		// TODO Auto-generated method stub
		Logger.getInstance().log("EnemyState:setEnvironment: I'm not implemented yet.");
	}

	@Override
	public IState modify(IAction a) {
		// TODO Auto-generated method stub
		Logger.getInstance().log("EnemyState:modify: I'm not implemented yet.");
		return null;
	}

	@Override
	public IEnvironment getEnvironment() {
		// TODO Auto-generated method stub
		Logger.getInstance().log("EnemyState:getEnvironment: I'm not implemented yet.");
		return null;
	}

	@Override
	public double getReward(IState old, IAction a) {
		// TODO Auto-generated method stub
		Logger.getInstance().log("EnemyState:getReward: I'm not implemented yet.");
		return 0;
	}

	@Override
	public boolean isFinal() {
		// TODO Auto-generated method stub
		Logger.getInstance().log("EnemyState:isFinal: I'm not implemented yet.");
		return false;
	}

	@Override
	public IState copy() {
		return new EnemyState(distance, bearing);
	}

	@Override
	public int nnCodingSize() {
		return 2;
	}

	@Override
	public double[] nnCoding() {
		return new double[] { distance, bearing };
	}

}
