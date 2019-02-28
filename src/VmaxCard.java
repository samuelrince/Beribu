
public class VmaxCard implements Card{
	private String type;
	private double userId;
	
	public VmaxCard(double userId) {
		super();
		this.type = "Vmax";
		this.userId = userId;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return this.type;
	}
	@Override
	public double getUserId() {
		// TODO Auto-generated method stub
		return this.userId;
	}
	
}

