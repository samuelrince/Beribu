
public class Standard implements Card{
	private User user;
	
	public Standard(User user) {
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
		return 0;
	}
	
}