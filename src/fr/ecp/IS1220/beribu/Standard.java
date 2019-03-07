package fr.ecp.IS1220.beribu;


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
	public double cost(Duration duration, String bicycleType) {
		// TODO Auto-generated method stub
		double hourCost = 0;
		if (bicycleType.equalsIgnoreCase("MECHANICAL")) {
			hourCost = 1;
		}
		else if (bicycleType.equalsIgnoreCase("ELECTRICAL")) {
			hourCost = 2;
		}
		return hourCost*((duration.getDuration()-1)/3600 + 1);
	}

	@Override
	public void updateTimeCreditBalance(Duration duration, String bicycleType, 
			boolean plusStation) {
		// TODO Auto-generated method stub
	}	
	
}