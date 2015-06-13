package pl.edu.agh.iwum;

import environment.ActionList;
import environment.IAction;
import environment.IEnvironment;
import environment.IState;

import java.util.Objects;

public class EnemyState implements IState {

	private static final long serialVersionUID = -3643819132494631724L;

	private double distance;
	private double bearing;
	private double heading;
	private double velocity;

	public EnemyState(double distance, double bearing,
					  double heading, double velocity) {
		this.distance = Math.round(distance);
		this.bearing = Math.round(bearing);
		this.heading = Math.round(heading);
		this.velocity = Math.round(velocity);
	}

	public double getDistance() {
		return distance;
	}

	public double getBearing() {
		return bearing;
	}

	@Override
	public int hashCode() {
		return Objects.hash(distance, bearing, heading, velocity);
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
		if (Double.doubleToLongBits(bearing) != Double.doubleToLongBits(other.bearing))
			return false;
		if (Double.doubleToLongBits(distance) != Double.doubleToLongBits(other.distance))
			return false;
		return true;
	}

	@Override
	public ActionList getActionList() {
		ActionList actionList = new ActionList(new EnemyState(0, 0, 0, 0));
		for (double shotPower : ShotAction.AVAILABLE_SHOT_POWERS) {
			actionList.add(new ShotAction(shotPower));
		}
		return actionList;
	}

	@Override
	public EnemyState copy() {
		return new EnemyState(distance, bearing, heading, velocity);
	}

	@Override
	public int nnCodingSize() {
		return 4;
	}

	@Override
	public double[] nnCoding() {
		return new double[] { distance, bearing, heading, velocity };
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
