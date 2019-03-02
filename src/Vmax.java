
public class Vmax implements Card{
	private User user;
	
	public Vmax(User user) {
		super();
		this.user = user;
		user.subscribe(this);
	}
	
	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return this.user;
	}
	@Override
	public double cost(Duration duration, String bicycleType) {
		// TODO Auto-generated method stub
		int costDuration = duration.getDuration();
		if (costDuration>3600) {
			if (costDuration-3600 > this.user.getTimeCreditBalance().getDuration()) {
				costDuration -= this.user.getTimeCreditBalance().getDuration();
			}
			else {
				costDuration = 3600;
			}
			return (costDuration-1)/3600;
		}
		else {
			return 0;
		}
	}

	@Override
	public void updateTimeCreditBalance(Duration duration, String bicycleType, 
			boolean plusStation) {
		// TODO Auto-generated method stub
		int costDuration = duration.getDuration();
		if (costDuration>3600) {
			if (costDuration-3600 > this.user.getTimeCreditBalance().getDuration()) {
				this.user.setTimeCreditBalance(0);
			}
			else {
				this.user.addTimeCreditBalance(0,3600-costDuration);
			}
		}
		
		if (plusStation) {
			this.user.addTimeCreditBalance(5);
		}
	}
	
}


