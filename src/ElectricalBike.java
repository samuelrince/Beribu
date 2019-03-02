
public class ElectricalBike extends Bicycle {
	private long id;
	private String type = "Electrical";
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

