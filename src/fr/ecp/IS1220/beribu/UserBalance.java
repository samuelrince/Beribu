package fr.ecp.IS1220.beribu;

public class UserBalance implements Statistics {
	User user;

	public UserBalance(User user) {
		
	}
	
	public int numberOfRides() {
		if (this.user.isOnRide()) {
			return this.user.getListOfRides().size()-1;
		}
		else {
			return this.user.getListOfRides().size();
		}
	}
	
	public Duration timeSpentOnBicycle() {
		Duration timeSpentOnBicycle = new Duration();
		for ( int i = 0; i < this.numberOfRides(); i++) {
			timeSpentOnBicycle.add(this.user.getListOfRides().get(i).getDuration());
		}
		return timeSpentOnBicycle;
	}
	
	public double totalCharges() {
		double totalCharges = 0;
		for ( int i = 0; i < this.numberOfRides(); i++) {
			totalCharges += this.user.getListOfRides().get(i).getPrice();
		}
		return totalCharges;
	}

}
