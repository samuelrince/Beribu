package fr.ecp.IS1220.beribu;

public class UserBalance implements Statistics {
	
	public static int numberOfRides(User user) {
		if (user.isOnRide()) {
			return user.getListOfRides().size()-1;
		}
		else {
			return user.getListOfRides().size();
		}
	}
	
	public static int numberOfRides(User user, Date begin, Date end) {
		int numberOfRides = 0;
		int maxIndex;
		if (user.isOnRide()) {
			maxIndex = user.getListOfRides().size()-1;
		}
		else {
			maxIndex = user.getListOfRides().size();
		}
		for (int i = 0; i < maxIndex; i++) {
			if (user.getListOfRides().get(i).getStartTime().isAfter(begin)
					&& end.isAfter(user.getListOfRides().get(i).getEndTime())) {
				numberOfRides++;
			}
		}
		return numberOfRides;
	}
	
	public static Duration timeSpentOnBicycle(User user) {
		Duration timeSpentOnBicycle = new Duration();
		for ( int i = 0; i < UserBalance.numberOfRides(user); i++) {
			timeSpentOnBicycle.add(user.getListOfRides().get(i).getDuration());
		}
		return timeSpentOnBicycle;
	}
	
	public static double totalCharges(User user) {
		double totalCharges = 0;
		for ( int i = 0; i < UserBalance.numberOfRides(user); i++) {
			totalCharges += user.getListOfRides().get(i).getPrice();
		}
		return totalCharges;
	}

	public static double cumulatedTimeCredit(User user) {
		double cumulatedTimeCredit = 0;
		for ( int i = 0; i < UserBalance.numberOfRides(user); i++) {
			cumulatedTimeCredit += user.getListOfRides().get(i).getTimeCreditOperation();
		}
		return cumulatedTimeCredit;
	}
}
