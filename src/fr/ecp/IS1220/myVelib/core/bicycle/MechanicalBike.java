package fr.ecp.IS1220.myVelib.core.bicycle;

/**
 * This class represents a bicycle of type "mechanical".
 * @author Valentin
 *
 */
public class MechanicalBike extends Bicycle {
	private long id;
	private String type = "MECHANICAL";
	private double speed = 15;
	
	public MechanicalBike() {
		super();
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}

}
