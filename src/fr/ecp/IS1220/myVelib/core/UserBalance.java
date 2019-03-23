package fr.ecp.IS1220.myVelib.core;

/**
 * This class provides a number of methods for computing user-related statistics.
 * @author Valentin
 *
 */
public class UserBalance {
	
	/**
	 * Returns the total number of rides ever performed by a given user.
	 * @param user the user to analyze
	 * @return their total number of rides
	 */
	public static int numberOfRides(User user) {
		if (user.isOnRide()) {
			return user.getListOfRides().size()-1;
		}
		else {
			return user.getListOfRides().size();
		}
	}
	
	/**
	 * Returns the total number of rides performed by a given user during
	 * a given time window.
	 * @param user the user to analyze
	 * @param begin the start of the time window
	 * @param end the end of the time window
	 * @return their total number of rides
	 */
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
	
	/**
	 * Returns the sum of the durations of all the rides ever performed by
	 * a given user.
	 * @param user the user to analyze
	 * @return their total time spent on a bicycle
	 */
	public static Duration timeSpentOnBicycle(User user) {
		Duration timeSpentOnBicycle = new Duration();
		for ( int i = 0; i < UserBalance.numberOfRides(user); i++) {
			timeSpentOnBicycle.add(user.getListOfRides().get(i).getDuration());
		}
		return timeSpentOnBicycle;
	}
	
	/**
	 * Returns the sum of the costs of all the rides ever performed by
	 * a given user.
	 * @param user the user to analyze
	 * @return the total cost of their rides
	 */
	public static double totalCharges(User user) {
		double totalCharges = 0;
		for ( int i = 0; i < UserBalance.numberOfRides(user); i++) {
			totalCharges += user.getListOfRides().get(i).getPrice();
		}
		return totalCharges;
	}

	public static void display(User user) {
		System.out.println("----------------------"+"\n"+"Statistics of user "+user+"\n"+"\n"
				+"Type of subscription : "+user.getCard().getType()+"\n"+"Time credit balance : "
				+user.getTimeCreditBalance()+"\n"+"Total number of rides : "+user.getListOfRides().size()
				+"\n"+"Total time spent on bicycle : "+UserBalance.timeSpentOnBicycle(user)
				+"\n"+"Total charges : "+UserBalance.totalCharges(user)+"€"
				+"\n"+"----------------------");
	}
//	public static double cumulatedTimeCredit(User user) {
//		double cumulatedTimeCredit = 0;
//		for ( int i = 0; i < UserBalance.numberOfRides(user); i++) {
//			cumulatedTimeCredit += user.getListOfRides().get(i).getTimeCreditOperation();
//		}
//		return cumulatedTimeCredit;
//	}
}
