package pl.edu.agh.iwum;

import environment.ActionList;
import environment.IAction;
import environment.IEnvironment;
import environment.IState;

public class EnemyState implements IState {

	private static final long serialVersionUID = -3643819132494631724L;

	private double distance;

	public EnemyState(double distance) {
		this.distance = Math.round(distance);
	}

	public double getDistance() {
		return distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnemyState other = (EnemyState) obj;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
			return false;
		return true;
	}

	@Override
	public ActionList getActionList() {
		ActionList actionList = new ActionList(new EnemyState(0));
		for (double shotPower : ShotAction.AVAILABLE_SHOT_POWERS) {
			actionList.add(new ShotAction(shotPower));
		}
		return actionList;
	}

	@Override
	public IState copy() {
		return new EnemyState(distance);
	}

	@Override
	public int nnCodingSize() {
		return 1;
	}

	@Override
	public double[] nnCoding() {
		return new double[] { distance };
	}

	@Override
	public void setEnvironment(IEnvironment c) {
		// TODO Auto-generated method stub
		Logger.getInstance().error("EnemyState:setEnvironment: I'm not implemented yet.");
	}

	@Override
	public IState modify(IAction a) {
		// TODO Auto-generated method stub
		Logger.getInstance().error("EnemyState:modify: I'm not implemented yet.");
		return null;
	}

	@Override
	public IEnvironment getEnvironment() {
		// TODO Auto-generated method stub
		Logger.getInstance().error("EnemyState:getEnvironment: I'm not implemented yet.");
		return null;
	}

	@Override
	public double getReward(IState old, IAction a) {
		// TODO Auto-generated method stub
		Logger.getInstance().error("EnemyState:getReward: I'm not implemented yet.");
		return 0;
	}

	@Override
	public boolean isFinal() {
		// TODO Auto-generated method stub
		Logger.getInstance().error("EnemyState:isFinal: I'm not implemented yet.");
		return false;
	}

}
