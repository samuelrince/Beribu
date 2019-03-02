
public class Vmax implements Card{
	private User user;
	
	public Vmax(User user) {
		super();
		this.user = user;
	}
	
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return this.user;
	}
	@Override
	public double cost(double duration, String bicycleType) {
		// TODO Auto-generated method stub
		this.user.setTimeCreditBalance(0);
		return 0;
		
	}
	
}

