package fr.ecp.IS1220.myVelib.app;

/**
 * This class represents a bicycle of type "electrical".
 * @author Valentin
 *
 */
public class ElectricalBike extends Bicycle {
	private long id;
	private String type = "ELECTRICAL";
	private double speed = 20;
	
	public ElectricalBike() {
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

